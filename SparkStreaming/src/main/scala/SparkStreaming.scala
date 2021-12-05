import Configuration.{KafkaConfig, SparkConfig}
import Utils.AwsEmailService.{emailService, log}
import org.apache.spark.streaming.kafka010._

// Consumes messages from kafka in the form of topics and does spark operations
object SparkStreaming extends App with SparkConfig with KafkaConfig {

  def init(): Unit = {
    // subscribe to topic
    log.info("Reading spark stream data from kafka")
    val streamDF = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", config.getString("sparkStreaming.kafkaServer"))
      .option("subscribe", config.getString("kafkaTopicName"))
      .load()

    // Get the lines from the kafka and split them into words
    val output = kafkaConsumerStream.map(_.value)
    output.print()

    log.info("Doing Spark Operations")
    output.foreachRDD {
      rdd =>
        val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
        log.info(s"Ranges for batch: ${offsetRanges.mkString}")
        if (rdd.count() > 0) {
        val body: String = config.getString("subjectBody") + rdd.collect().mkString(" ")
          emailService(body)
        }
        var result: Array[String] = null
        kafkaConsumerStream.asInstanceOf[CanCommitOffsets].commitAsync(offsetRanges)
    }
  }

  override def main(args: Array[String]): Unit = {
    log.info("Initializing Spark Streaming")
    init()
    log.info("Starting streaming Context")
    streamingContext.start()
    streamingContext.awaitTermination()
    log.info("Streaming Context Terminated")
  }

}

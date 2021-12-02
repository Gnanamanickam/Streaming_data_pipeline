import Configuration.{KafkaConfig, SparkConfig}
import Utils.AwsEmailService.log
import org.apache.spark.streaming.kafka010._

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

    log.info("Doing Spark Operations")
    kafkaConsumerStream.foreachRDD {
      rdd =>
        val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
        println(s"Ranges for batch: ${offsetRanges.mkString}")
        rdd.foreach(println)
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

import Configuration.{KafkaConfig, SparkConfig}
import org.apache.spark.streaming.kafka010._
import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.SparkContext

object SparkStreaming extends App with SparkConfig with KafkaConfig {

  def init(): Unit = {
    // subscribe to topic
    val streamDF = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", config.getString("sparkStreaming.kafkaServer"))
      .option("subscribe", config.getString("kafkaTopicName"))
      .load()

    kafkaConsumerStream.foreachRDD {
      rdd =>
        val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
        println(s"Ranges for batch: ${offsetRanges.mkString}")
        rdd.foreach(println)

        kafkaConsumerStream.asInstanceOf[CanCommitOffsets].commitAsync(offsetRanges)
    }
  }

  override def main(args: Array[String]): Unit = {
    init()
    streamingContext.start()
    streamingContext.awaitTermination()
  }

}

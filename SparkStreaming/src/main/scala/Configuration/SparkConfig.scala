package Configuration

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

trait SparkConfig {

  // create a spark conf without spark session
//  val conf = new SparkConf().setMaster("local[*]").setAppName("SparkStreaming")

  val spark: SparkSession = SparkSession
    .builder()
    .appName("SparkStreaming") // appName parameter is a name for the application to show on the cluster UI
    .master("local[*]") // master can be Spark Mesos or YARN cluster URL or local for local mode . * represents no. of threads
    .getOrCreate() // get if available already or create a new one

  //  Only one SparkContext should be active per JVM. Stop the active SparkContext before creating a new one.
  val sparkContext: SparkContext = spark.sparkContext
  // Create a StreamingContext with working thread and batch interval of 10 seconds.
  val streamingContext: StreamingContext = new StreamingContext(sparkContext, Seconds(10))
}

import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.SparkContext

trait SparkConfig {
  val spark: SparkSession = SparkSession
    .builder()
    .appName("Spark Streaming")
    .master("local[*]")
    .getOrCreate()

  val sparkContext: SparkContext = spark.sparkContext
  val streamingContext: StreamingContext = new StreamingContext(sparkContext, Seconds(10))
}
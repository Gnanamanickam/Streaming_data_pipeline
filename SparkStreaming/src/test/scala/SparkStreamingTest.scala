
import Utils.WordCount
import com.typesafe.config.ConfigFactory
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, Time}
import org.scalatest.concurrent.Eventually
import org.scalatest.time.{Millis, Span}
import org.scalatest.GivenWhenThen

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class SparkStreamingTest extends AnyFlatSpec with BeforeAndAfter with GivenWhenThen with Matchers with Eventually {

  // Get the config values from application.conf in resources
  val config = ConfigFactory.load("Application.conf").getConfig("randomLogGenerator")

  val kafkaServer = config.getInt("kafkaServer")

  behavior of "Configuration Parameters Module"

  // check whether the kafka server address is right
  it should "Check for the kafka server" in {
    kafkaServer shouldBe >= ("127.0.0.1:9092")
  }

  "Sample set" should "be counted" in {
    Given("streaming context is initialized")
    val lines = mutable.Queue[RDD[String]]()
    var results = ListBuffer.empty[Array[WordCount]]

//    WordCount.count(s,lines) {
//      (wordsCount: RDD[WordCount], time: Time) =>
//      results += wordsCount.collect()
//  }

}

import com.typesafe.config.ConfigFactory
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SparkStreamingTest extends AnyFlatSpec with Matchers {

  // Get the config values from application.conf in resources
  val config = ConfigFactory.load("Application.conf").getConfig("randomLogGenerator")

  val kafkaServer = config.getInt("kafkaServer")

  behavior of "Configuration Parameters Module"

  // check whether a random generated string length is lesser than the minimum length
  it should "Check for the kafka server" in {
    kafkaServer shouldBe >= ("127.0.0.1:9092")
  }


}
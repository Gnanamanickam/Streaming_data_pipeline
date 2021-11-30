import com.typesafe.config.ConfigFactory
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ActorTest extends AnyFlatSpec with Matchers {
  val config = ConfigFactory.load("application" + ".conf")
  it should "RegEx should length > 0" in {
    val fileName = config.getString("config.FileName")
    print(fileName)
    assert(fileName.length > 0)
  }
}

import HelperUtils.CreateLogger
import com.typesafe.config.{Config, ConfigFactory}

class StartExecution()

object StartExecution {
  val logger = CreateLogger(classOf[StartExecution])

  def main(args: Array[String]): Unit = {
    logger.info(s"Execution Start")

    val config: Config = ConfigFactory.load("application" + ".conf")
  }
}
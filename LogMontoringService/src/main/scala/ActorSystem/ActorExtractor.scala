package ActorSystem

import akka.actor.Actor
import org.apache.kafka.clients.producer.KafkaProducer

import java.io.File
import java.nio.file.Files
import java.util.Properties
import scala.io.Source

class ActorExtractor extends Actor {
  var lastReadLines: scala.collection.mutable.Map[String, Int] = scala.collection.mutable.Map[String, Int]()
  var lineCounter = 0
  val props: Properties = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("acks", "all")
  val producer = new KafkaProducer[String, String](props)
  val topic = "test"

  override def receive: Receive = {
    case file: File =>
      if (!lastReadLines.contains(file.getName)) lastReadLines += (file.getName -> 0)
      val BufferedSource = Source.fromFile(file)
      val data = Files.lines(file.toPath)
      data.skip(lastReadLines(file.getName)).forEach(SendTokafka(_))
      lastReadLines(file.getName) = BufferedSource.getLines.size
  }

  def SendTokafka(data: String): Unit = {
    println(data)
  }

}

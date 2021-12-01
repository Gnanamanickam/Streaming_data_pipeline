package ActorSystem

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.{Config, ConfigFactory}
import driver.{FileAdapter, FileEvent, FileWatcher}

import java.io.File


object ActorWatcher {
  def props(extractor: ActorRef, path: String): Props = Props(new ActorWatcher(extractor, path))
}

class ActorWatcher(extractor: ActorRef, path: String) extends Actor {
  override def receive: Receive = {
    case "watch" =>
      watch(extractor)
    case _ => println("Invalid input. Please check")
  }

  def watch(extractor: ActorRef): Unit = {
    //Specify the path here
    val folder = new File(path)
    val watcher = new FileWatcher(folder)
    watcher.addListener(new FileAdapter() {
      override def onModified(event: FileEvent): Unit = {
        extractor ! event.getFile
      }
    }).watch()
  }
}

object Main extends App {
  val config: Config = ConfigFactory.load("application" + ".conf")
  val path = config.getString("config.FileName")
  val system = ActorSystem("Watchers")
  val extractor = system.actorOf(Props[ActorExtractor], name = "extractor")
  val watcher = system.actorOf(ActorWatcher.props(extractor, path), name = "watcher")
  watcher ! "watch"
}

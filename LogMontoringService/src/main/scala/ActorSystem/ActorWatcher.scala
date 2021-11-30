package ActorSystem

import akka.actor.{Actor, ActorRef, Props}
import driver.{FileAdapter, FileEvent, FileWatcher}

import java.io.File


object ActorWatcher {
  def props(extractor: ActorRef, path: String): Props = Props(new ActorWatcher(extractor, path))
}
class ActorWatcher (extractor: ActorRef, path: String) extends Actor {
  override def receive: Receive = {
    val folder = new File(path)
    val watcher = new FileWatcher(folder)
    watcher.addListener(new FileAdapter() {
      override def onModified(event: FileEvent): Unit = {
        extractor ! event.getFile
      }

      override def onCreated(event: FileEvent): Unit = ???

      override def onDeleted(event: FileEvent): Unit = ???
    }).watch()
  }
}

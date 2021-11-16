package org.FileMonitor

import org.FileMonitor.FileListener

abstract class FileAdapter extends FileListener {

  def onCreated(event: FileEvent): Unit

  def onModified(event: FileEvent): Unit

  def onDeleted(event: FileEvent): Unit
  
}


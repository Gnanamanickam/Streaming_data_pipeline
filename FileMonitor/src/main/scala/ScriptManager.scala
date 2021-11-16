package org.FileMonitor

import java.io.File
import java.net.URL
import groovy.util.GroovyScriptEngine


class ScriptManager(val folder: File) {

  val engine: GroovyScriptEngine = createScriptEngine;


  protected def createScriptEngine: GroovyScriptEngine = {
    val urls = Array(folder.toURI.toURL)
    new GroovyScriptEngine(urls, this.getClass.getClassLoader)
  }

  def loadScript(file: File): Object = {
    val name = file.getAbsolutePath.substring(folder.getAbsolutePath.length + 1)
    engine.loadScriptByName(name).getConstructor().newInstance()
  }
}
package org.FileMonitor

import java.io.File
import java.net.URL
import groovy.util.GroovyScriptEngine


class ScriptManager(val folder: File) {

  val engine: GroovyScriptEngine = createScriptEngine;


  // Create a script engine to reload modified scripts as well as dealing properly with dependent scripts.
  protected def createScriptEngine: GroovyScriptEngine = {
    val urls = Array(folder.toURI.toURL)
    new GroovyScriptEngine(urls, this.getClass.getClassLoader)
  }

  // Method to load the script
  def loadScript(file: File): Object = {
    // Get the name of the file
    val name = file.getAbsolutePath.substring(folder.getAbsolutePath.length + 1)
    // Load the script with file name and gets its instance
    engine.loadScriptByName(name).getConstructor().newInstance()
  }
}
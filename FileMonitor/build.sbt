name := "FileMonitor"

version := "0.1"

scalaVersion := "2.13.7"

val typesafeConfigVersion = "1.4.1"
val AkkaVersion = "2.6.17"
val kafkaVersion = "2.8.0"
val groovyVersion = "3.0.8"

idePackagePrefix := Some("org.FileMonitor")

resolvers += "Typesafe Repository" at "https://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq( "com.typesafe" % "config" % typesafeConfigVersion,
  "com.typesafe.akka" %% "akka-actor" % AkkaVersion,
  "org.apache.kafka" % "kafka-clients" % kafkaVersion,
  "org.codehaus.groovy" % "groovy-jsr223" % groovyVersion
)
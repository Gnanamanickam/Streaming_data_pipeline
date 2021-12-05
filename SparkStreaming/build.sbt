name := "SparkStreaming"

scalaVersion := "2.12.12"
version := "1.0"

val scalacticVersion = "3.2.9"
val sparkVersion = "2.4.4"
val awsJavaSdkVersion = "1.12.89"
val akkaVersion = "2.5.23"
val akkaKafkaStreamingVersion = "1.0.1"
val logbackVersion = "1.3.0-alpha10"
val sfl4sVersion = "2.0.0-alpha5"
val typesafeConfigVersion = "1.4.1"


libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-core" % logbackVersion,
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "org.slf4j" % "slf4j-api" % sfl4sVersion,
  "com.typesafe" % "config" % typesafeConfigVersion,
  "org.scalactic" %% "scalactic" % scalacticVersion,
  "org.scalatest" %% "scalatest" % scalacticVersion % Test,
  "org.scalatest" %% "scalatest-featurespec" % scalacticVersion % Test,
  "com.typesafe" % "config" % typesafeConfigVersion,
  "com.amazonaws" % "aws-java-sdk-s3" % awsJavaSdkVersion,
  "com.amazonaws" % "aws-java-sdk-s3" % awsJavaSdkVersion,
  "com.amazonaws" % "aws-java-sdk" % awsJavaSdkVersion,
  "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream-kafka" % akkaKafkaStreamingVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.6.7.1",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"
)
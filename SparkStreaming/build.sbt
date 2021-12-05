name := "SparkStreaming"

version := "0.1"

scalaVersion := "2.12.9"

val logbackVersion = "1.3.0-alpha10"
val sfl4sVersion = "2.0.0-alpha5"
val typesafeConfigVersion = "1.4.1"
val apacheCommonIOVersion = "2.11.0"
val scalacticVersion = "3.2.9"
val generexVersion = "1.0.2"
val sparkVersion = "3.1.2"
val kafkaVersion = "2.6.2"
val awsVersion = "1.12.89"
val jacksonVersion = "2.10.0"

resolvers += "Typesafe Repository" at "https://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.fasterxml.jackson.core:jackson-core" % jacksonVersion,
  "com.fasterxml.jackson.core:jackson-databind" % jacksonVersion,
  "ch.qos.logback" % "logback-core" % logbackVersion,
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "org.slf4j" % "slf4j-api" % sfl4sVersion,
  "com.typesafe" % "config" % typesafeConfigVersion,
  "javax.xml.bind" % "jaxb-api" % "2.3.0",
  "commons-io" % "commons-io" % apacheCommonIOVersion,
  "org.scalactic" %% "scalactic" % scalacticVersion,
  "org.apache.hadoop" % "hadoop-common" % "3.2.0",
  "org.scalatest" %% "scalatest" % scalacticVersion % Test,
  "org.scalatest" %% "scalatest-featurespec" % scalacticVersion % Test,
  "com.typesafe" % "config" % typesafeConfigVersion,
  "io.spray" %%  "spray-json" % "1.3.6",
  "com.github.mifmif" % "generex" % generexVersion,
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  "org.apache.spark" %% "spark-avro" % sparkVersion,
  "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion,
  "org.apache.spark" %% "spark-sql-kafka-0-10" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.kafka" %% "kafka" % kafkaVersion,
  "com.amazonaws" % "aws-java-sdk" % awsVersion,
  "com.amazonaws" % "aws-java-sdk-s3" % awsVersion
)
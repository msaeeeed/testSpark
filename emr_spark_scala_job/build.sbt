name := "emr_spark_scala_job"

version := "1.0"

scalaVersion := "2.10.7"

libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.2.0" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.2.0" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.2.0" % "provided"
// libraryDependencies += "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
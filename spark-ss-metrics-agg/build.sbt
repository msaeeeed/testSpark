name := "spark-ss-metrics-agg"

version := "0.1"

scalaVersion := "2.11.10"
name := "sparkTutorialScala"


libraryDependencies ++= {
  val sparkVer = "2.3.0"
  Seq(
    "org.apache.spark" %% "spark-core" % sparkVer % "provided" withSources()
  )
}
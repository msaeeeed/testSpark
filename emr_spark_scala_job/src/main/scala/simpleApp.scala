package com.burda

import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
import org.apache.spark.sql.streaming.{OutputMode, Trigger}


import java.sql.ResultSet
import java.time.OffsetDateTime



object simpleApp {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder
      .appName("spark pilot emr app")
      .getOrCreate()

    import spark.implicits._

    val schemaImp = spark.read
      .format("csv")
      .option("header", true)
      .option("inferSchema", true)
      .load("src/files/Book1.csv")
      .schema

    println(schemaImp)

    val in = spark.readStream
      .schema(schemaImp)
      .format("csv")
      .option("header", true)
      .option("maxFilesPerTrigger", 1)
      .load("src/files/*.csv")
      .groupBy("City")
      .count()
      .writeStream
      .format("console").
      option("truncate", false).
      option("numRows", 10).
      trigger(Trigger.ProcessingTime("60 seconds")).
      queryName("consoleStream").
      outputMode(OutputMode.Complete).
      start

    in.awaitTermination()


  }
}

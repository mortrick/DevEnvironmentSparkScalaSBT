package session

import org.apache.spark.sql.SparkSession




object SparkSessionWrapper {
  def createSparkSession(contextName: String): SparkSession = {
    val spark = SparkSession.builder()
      .appName(contextName)
      .master("local[*]") // Use all available cores
      .getOrCreate()
    spark
  }
}
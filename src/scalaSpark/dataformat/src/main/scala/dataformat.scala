import session.SparkSessionWrapper
import org.apache.spark.sql.DataFrame
import dataUtil.FileFormat

object dataformat {
  def main(args: Array[String]): Unit = {
    // Initialize SparkSession using the wrapper class
    val spark = SparkSessionWrapper.createSparkSession("Data formater")

    //Set path to CSV
    val csvPath = "data/customers-1000.csv"

    // Read the file from path and change it type to Parquet if the flag is up and the file have positive number of rows
    val (results,message) = FileFormat.formatCsv(csvPath,spark,1)

    if (results == 0){
      println("Please check the file it might be empty")
    }
    else {
    println(message)
    }
  spark.stop()
  }
}

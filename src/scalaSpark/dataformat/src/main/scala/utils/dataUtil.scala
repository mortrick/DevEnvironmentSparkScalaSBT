package dataUtil

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame

object FileFormat{

def formatCsv(filepath: String, sparkObj: SparkSession, convert: Int, convertType: String = "parquet"): (Int,String) = {
val df: DataFrame = sparkObj.read
      .format("csv")
      .option("header", "true") // Use first line of CSV file as header
      .option("inferSchema", "true") // Automatically infer data types
      .load(filepath)    
    df.printSchema()
    df.show()
    //Count the rows to validate the CSV isn't empty
val rowCount: Long = df.count()
if (rowCount>0 && convert!= 0){
    val parquetPath = filepath.replace(".csv", ".parquet")
    import java.io.File
    val file = new File(parquetPath)
    if (file.exists()){
        println("---------------- Do nothing the file already exists -----------------")
    }
    else {
    df.write.parquet(parquetPath)
}
    (1,s"-------------- The file path is $filepath ----------  ------------------------The new path for the parquet is $parquetPath------------------- ")
    
}
else if (rowCount>0){
    (1,s"There are $rowCount rows in the CSV")
} 

else if (rowCount ==0) {
    (0, s"The are no rows in the CSV")
}
else {(0,s"ok ok ok ")}

}
}
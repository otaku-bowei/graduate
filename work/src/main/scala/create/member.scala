package create

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object member {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("spark://192.168.0.101:7077").setAppName("crm")

  }
}

package test

import org.apache.spark.{SparkConf, SparkContext}

object test_1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("spark://172.16.29.237:7077").setAppName("WC")
    val sc = new SparkContext(conf)
    sc.textFile("hdfs://172.16.29.237:9000/user/spark/warehouse/input/words.txt")
      .flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_,1)
      .sortBy(_._2,false)
      .saveAsTextFile("hdfs://172.16.29.237:9000/user/spark/warehouse/output/WC4")
    sc.stop()
  }
}

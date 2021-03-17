package main;

import connection.SparkSingleton;
import org.apache.spark.sql.SparkSession;

public class StopSession {
    public static void main(String[] args) {
        SparkSession spark = SparkSingleton.getSparkSession() ;
        spark.close();
        spark.stop();
    }
}

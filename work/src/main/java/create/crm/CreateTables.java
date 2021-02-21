package create.crm;

import crm.entity.Member ;
import create.SparkSingleton;
import org.apache.flink.api.java.DataSet;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.DataFrameWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;


public class CreateTables {


    public static void createMembers(){

        SparkSession spark = SparkSingleton.getSparkSession() ;
        Dataset<Long> dataSet = spark.range(0,5) ;
        dataSet.write().format("delta").save("/delta/test");
    }
}

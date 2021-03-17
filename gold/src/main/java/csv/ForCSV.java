package csv;

import connection.SparkSingleton;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;


public class ForCSV {

    public static void deltaForCSV(String tableName,String sql,String path){
        SparkSession spark = SparkSingleton.getSparkSession() ;
        String s = "select * from "+ tableName ;
        Dataset<Row> ds = spark.sql(sql==null?s:sql) ;
        ds.write().csv(path);
    }
}

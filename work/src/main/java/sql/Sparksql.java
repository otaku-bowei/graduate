package sql;

import connection.SparkSingleton;
import org.apache.spark.sql.SparkSession;

public class Sparksql {
    public static void runSQL(String sql){
        SparkSession spark = SparkSingleton.getSparkSession();
        spark.sql(sql).show() ;
    }
}

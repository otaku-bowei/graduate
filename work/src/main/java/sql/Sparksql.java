package sql;

import connection.SparkSingleton;
import io.delta.tables.DeltaTable;
import org.apache.spark.sql.SparkSession;

public class Sparksql {
    public static void runSQL(String sql){
        SparkSession spark = SparkSingleton.getSparkSession();
        spark.sql(sql) ;
        spark.close();
        spark.stop();
    }
}

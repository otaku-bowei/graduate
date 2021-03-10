
import connection.SparkSingleton;
import create.crm.CreateCRM;
import create.pos.CreatePos;
import io.delta.tables.DeltaTable;
import org.apache.spark.sql.SparkSession;

import java.io.IOException;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) throws IOException {
        SparkSession spark = SparkSingleton.getSparkSession() ;
        DeltaTable dt = DeltaTable.forName(spark,"crm") ;

        spark.sql("select * from crm where CustomerID=187").show();
        spark.close();
        spark.stop();
    }
}






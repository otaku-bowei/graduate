package update.crm;

import connection.SparkSingleton;
import io.delta.tables.DeltaTable;
import org.apache.spark.sql.SparkSession;
import java.util.HashMap;

public class UpdateCRM {

    public static void UpdateCustomerId(String tableName,String result){
        SparkSession spark = SparkSingleton.getSparkSession() ;
        DeltaTable dt = DeltaTable.forPath(spark,"/delta/" + tableName + "/") ;
        dt.updateExpr("customerId = '201'",new HashMap<String,String>(){
            {
                put("customerId","'202'") ;
            }
        });
        spark.close();
        spark.stop();
    }
}

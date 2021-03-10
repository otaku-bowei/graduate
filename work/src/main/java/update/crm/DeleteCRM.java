package update.crm;

import connection.SparkSingleton;
import io.delta.tables.DeltaTable;
import org.apache.spark.sql.*;
import org.apache.spark.sql.streaming.StreamingQuery;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

public class DeleteCRM {
    /*
    * 全删
     */
    public static void delectAll(String tableName){
        SparkSession spark = SparkSingleton.getSparkSession() ;
        DeltaTable dt = DeltaTable.forPath(spark,"/delta/" + tableName + "/") ;
        dt.delete();
        spark.close();
        spark.stop();
    }

    /*
    *条件删除
     */
    public static void delectAny(String tableName, Map<String,String> conditions) throws TimeoutException {
        SparkSession spark = SparkSingleton.getSparkSession() ;
        DeltaTable dt = DeltaTable.forPath(spark,"/delta/" + tableName + "/") ;
        dt.delete(changeConditions(conditions));
        spark.close();
        spark.stop();
    }
    private static String changeConditions(Map<String,String> condition){
        StringBuffer sb = new StringBuffer("");
        int index = condition.size() ;
        //这里要修改=的条件，要看事实
        for (String s:condition.keySet()){
            sb.append(s + "=" + condition.get(s) );
            sb.append(index>1?",":"") ;
            -- index ;
        }
        return sb.toString() ;
    }


}

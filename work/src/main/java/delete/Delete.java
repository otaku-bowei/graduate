package delete;

import connection.SparkSingleton;
import io.delta.tables.DeltaTable;
import org.apache.spark.sql.SparkSession;

public class Delete {

    public static void deleteAll(String table){
        DeltaTable target = DeltaTable.forName(table) ;
        target.delete();
    }

    public static void deleteAny(String table, String condition){
        DeltaTable target = DeltaTable.forName(table) ;
        target.delete(condition);
    }
}

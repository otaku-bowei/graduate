package merge;

import connection.SparkSingleton;
import io.delta.tables.DeltaTable;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.HashMap;

public class MergeTickets {
    /*
     ** t1 和 t2 对应两个版本的数据
     * 匹配时修改时间
     * 不匹配时插入数据
     */
    public static void merge(String table1, String table2, String condition){
        SparkSession spark = SparkSingleton.getSparkSession() ;
        Dataset<Row> updateTickets = DeltaTable.forName(table1).df() ;
        DeltaTable.forName(table1).merge(updateTickets.as("tickets"), condition)
                .whenMatched().updateExpr(new HashMap<String, String>() {{ put("date", "tickets.date"); put("time", "tickets.time");}})
                .whenNotMatched().insertExpr(
                new HashMap<String, String>() {{
                    put("invoiceID", "tickets.invoiceID");
                    put("branch", "tickets.branch");
                    put("city", "tickets.city");
                    put("customer", "tickets.customer");
                    put("gender", "tickets.gender");
                    put("productLine", "tickets.productLine");
                    put("unitPrice", "tickets.unitPrice");
                    put("quantity", "tickets.quantity");
                    put("tax", "tickets.tax");
                    put("total", "tickets.total");
                    put("date", "tickets.date");
                    put("time", "tickets.time");
                    put("payment", "tickets.payment");
                    put("cogs", "tickets.cogs");
                    put("grossMargin", "tickets.grossMargin");
                    put("grossIncome", "tickets.grossIncome");
                    put("rating", "tickets.rating");
                }})
                .execute();

    }
}

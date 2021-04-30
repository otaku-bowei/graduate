package merge;

import connection.SparkSingleton;
import crm.entity.Member;
import io.delta.tables.DeltaTable;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.HashMap;

public class MergeMember {

    /*
    ** t1 和 t2 对应两个版本的数据
    * 匹配时不作操作，因为member表没有时间记录
    * 不匹配时插入
     */
    public static void merge(String table1, String table2, String condition){
        SparkSession spark = SparkSingleton.getSparkSession() ;
        Dataset<Row> updateMember = DeltaTable.forName(table1).df() ;
        DeltaTable.forName(table1).merge(updateMember.as("member"), condition)//.whenMatched().updateExpr(new HashMap<String, String>() {{ put("name", "member.name"); }})
                .whenNotMatched().insertExpr(
                        new HashMap<String, String>() {{
                            put("id", "member.date");
                            put("genre", "member.genre");
                            put("age", "member.age");
                            put("annualIncome", "member.annualIncome");
                            put("spendingScore", "member.spendingScore");
                            put("name", "member.name");
                        }})
                .execute();

    }
}

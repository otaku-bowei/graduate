package insert.crm;

import connection.SparkSingleton;
import create.crm.CreateCRM;
import crm.entity.Member;
import io.delta.tables.DeltaTable;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InsertCRM{
    /*
    **@Param members 增量的会员
    **（1.定时任务，以时间节点为准，每天添加新会员 2.会员id自增，读取最大值，把大于最大值的全部插入）
    * 插入实际上就是写入
     */
    public static boolean insertMoreMembers(List<Member> members){
        SparkSession spark = SparkSingleton.getSparkSession() ;
        Dataset<Row> read = spark.read().format("delta").parquet("/delta/crm") ;
        
        //写入
        Dataset<Row> df = spark.createDataFrame(members,Member.class) ;
        df.write().format("delta").mode("append").save("/delta/crm");
        spark.close();
        spark.stop();
        return true ;
    }

    /*
    **全量插入，overwrite
     */
    public static boolean insertFullMember(List<Member> members){
        SparkSession spark = SparkSingleton.getSparkSession() ;
        Dataset<Row> df = spark.createDataFrame(members,Member.class) ;
        df.write().format("delta").mode(SaveMode.Overwrite).saveAsTable("crm"); ;
        spark.close();
        spark.stop();
        return true ;
    }

    /**
     * 插入单个会员
     * @Param member 会员
     */
    public static boolean insertMember(Member member){
        SparkSession spark = SparkSingleton.getSparkSession() ;
        Dataset<Row> df = spark.createDataFrame(Collections.singletonList(new ArrayList<Member>().add(member)),Member.class) ;
        df.write().format("delta").mode(SaveMode.Append).saveAsTable("crm"); ;
        spark.close();
        spark.stop();
        return true ;
    }
}

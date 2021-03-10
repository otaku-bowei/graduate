package create.crm;

import connection.SparkSingleton;
import crm.DAO.Mapper;
import crm.entity.Member;
import io.delta.tables.DeltaTable;
import org.apache.ibatis.session.SqlSession;
import org.apache.spark.sql.*;
import org.apache.spark.sql.catalyst.analysis.CannotReplaceMissingTableException;
import org.apache.spark.sql.catalyst.analysis.TableAlreadyExistsException;
import scala.collection.Map;
import scala.collection.Seq;

import java.io.IOException;
import java.util.List;

public class CreateCRM{

    public static List<Member> getMember() throws IOException {
        SqlSession sqlSession = crm.factory.Factory.getSqlSession() ;
        Mapper mapping = sqlSession.getMapper(crm.DAO.Mapper.class);
        List<Member> members = mapping.getMembers() ;
        return members ;
    }

    public static void createMembers() throws IOException {

        SparkSession spark = SparkSingleton.getSparkSession() ;
        Dataset<Row> df = spark.createDataFrame(CreateCRM.getMember(),Member.class) ;
        df.write().format("delta").save("/delta/crm");
        spark.close();
        spark.stop();
    }


    public static void createMemberTable(String tableName) throws IOException {
        SparkSession spark = SparkSingleton.getSparkSession() ;
        Dataset<Row> df = spark.createDataFrame(CreateCRM.getMember(),Member.class) ;
        df.write().format("delta").saveAsTable(tableName);
        spark.close();
        spark.stop();
    }
}

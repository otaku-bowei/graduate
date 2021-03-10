package create.pos;

import connection.SparkSingleton;
import crm.entity.Member;
import org.apache.ibatis.session.SqlSession;
import org.apache.spark.sql.*;
import org.apache.spark.sql.catalyst.analysis.CannotReplaceMissingTableException;
import org.apache.spark.sql.catalyst.analysis.TableAlreadyExistsException;
import pos.DAO.Mapper;
import pos.entity.Ticket;
import scala.collection.Map;
import scala.collection.Seq;
import java.io.IOException;
import java.util.List;


public class CreatePos{

    public static List<Ticket> getTickets() throws IOException {
        SqlSession sqlSession = pos.factory.Factory.getSqlSession() ;
        Mapper mapping = sqlSession.getMapper(pos.DAO.Mapper.class) ;
        List<Ticket> members = mapping.getTickets() ;
        return members ;
    }

    public static void createTickets() throws IOException {

        SparkSession spark = SparkSingleton.getSparkSession() ;
        Dataset<Row> df = spark.createDataFrame(CreatePos.getTickets(),Ticket.class) ;
        df.write().format("delta").save("/delta/pos");
        spark.close();
        spark.stop();
    }

    public static void createTicketTable(String tableName) throws IOException {
        SparkSession spark = SparkSingleton.getSparkSession() ;
        Dataset<Row> df = spark.createDataFrame(CreatePos.getTickets(),Ticket.class) ;
        df.write().format("delta").saveAsTable(tableName);
        spark.close();
        spark.stop();
    }

}

package join;

import io.delta.tables.DeltaTable;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class JoinTables {

    public enum Join{
        LeftJoin ,
        Join ,
        RightJoin,
        OuterJoin ,
        InnerJoin
    }

    public static Dataset<Row> joinTables(String table1, String table2, String condition, Join joinType){
        DeltaTable t1 = DeltaTable.forName(table1) ;
        DeltaTable t2 = DeltaTable.forName(table2) ;
        Dataset<Row> ans = null ;
        switch (joinType){
            case Join:ans = t1.df().join(t2.df()) ;break;
            case LeftJoin:ans = t1.df().as("t1").join(t2.df().as("t2"),"t2.customer in t1.customerId") ;break;
            case InnerJoin:ans = t1.df().as("t1").join(t2.df().as("t2"),"t2.customer in t1.customerId and t1.customerId in t2.customer") ;break;
            case OuterJoin:ans = t1.df().join(t2.df()) ;break;
            case RightJoin:ans = t2.df().as("t2").join(t1.df().as("t1"),"t1.customerId in t2.customer") ;break;
        }
        return ans ;
    }
}

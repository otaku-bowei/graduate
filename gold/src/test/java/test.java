import binlog.BinlogListener;
import binlog.Canal;
import kafka.Consumer;
import org.junit.jupiter.api.Test;



public class test {
    @Test
    public void test(){
        Consumer c = new Consumer("crm") ;
        c.record();
        c.close();

    }

    @Test
    public void ttt(){
        new BinlogListener("192.168.145.41",3306,"root","root");
    }

    @Test
    public void tt(){
        new Canal("example").initConnector();
    }
}

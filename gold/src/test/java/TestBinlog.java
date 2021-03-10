import binlog.BinlogListener;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;

public class TestBinlog {
    @Test
    public void binlog() throws IOException {
        new BinlogListener("192.168.145.41",3306,"root","root") ;

    }


}
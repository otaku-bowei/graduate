package binlog;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.Message;
import sql.Sparksql;

import java.net.InetSocketAddress;
import java.util.List;

public class Canal implements Runnable{
    private String destination ;

    public Canal(String destination){
        this.destination = destination ;
    }

    public Canal(){

        this.destination = "example" ;
    }

    public void initConnector() {
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("192.168.145.41", 11111), this.destination, "", "");
        try {
            connector.connect();
            connector.subscribe(".*\\..*");
            connector.rollback();
            while (true) {
                Message message = connector.getWithoutAck(1000);
                if (message.getId() != -1 && message.getEntries().size() > 0) {
                    this.printEntry(message.getEntries());
                }
                connector.ack(message.getId());
            }
        } finally {
            connector.disconnect();
        }
    }

    public void printEntry(List<Entry> entries) {
        for (Entry entry : entries) {
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                continue;
            }
            try {
                RowChange rowChange = RowChange.parseFrom(entry.getStoreValue());
                String str = rowChange.getSql() ;
                str = str.substring(str.indexOf("*/")+2) ;
                System.out.println(str);
                Sparksql.runSQL(str);

            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser error, data:" + entry.toString(), e);
            }
        }
    }

    @Override
    public void run() {
        this.initConnector();
    }
}

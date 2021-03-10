import binlog.BinlogListener;
import binlog.Canal;
import create.crm.CreateCRM;
import create.pos.CreatePos;
import kafka.Consumer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        CreateCRM.createMemberTable("member");
        CreatePos.createTicketTable("tickets");
        new Thread(new Canal("example")).start();
        new Thread(new Consumer("member")).start();
        new Thread(new Consumer("tickets")).start();

    }
}

package binlog;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.otter.canal.client.impl.SimpleCanalConnector;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.BinaryLogFileReader;
import com.github.shyiko.mysql.binlog.event.*;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;


import javax.json.Json;
import java.io.*;

public class BinlogListener {
    private static final ParserConfig snakeCase;

    static {
        snakeCase = new ParserConfig();
        snakeCase.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
    }

    public BinlogListener(String hostname,int port,String username,String password){
        try {
            this.start(hostname, port, username, password);
        } catch (IOException e) {
            System.out.println("Connection binlog err");
        }
    }

    private void start(String hostname,int port,String username,String password) throws IOException {

        BinaryLogClient client = new BinaryLogClient(hostname, port, username, password);
        EventDeserializer eventDeserializer = new EventDeserializer();


        client.setEventDeserializer(eventDeserializer);
        client.registerEventListener(new BinaryLogClient.EventListener() {
            @Override
            public void onEvent(Event event) {

                EventHeader header = event.getHeader();
                EventType eventType = header.getEventType();
                EventData eventData = event.getData() ;


                System.out.println("监听的事件类型:" + eventType.name());
                if (EventType.isWrite(eventType)) {
                    WriteRowsEventData data = event.getData();
                    System.out.println("插入");
                    System.out.println(data);
                } else if (EventType.isUpdate(eventType)) {
                    UpdateRowsEventData data = event.getData();
                    System.out.println("更新");
                    System.out.println(data);
                } else if (EventType.isDelete(eventType)) {
                    DeleteRowsEventData data = event.getData();
                    System.out.println("删除");
                    System.out.println(data);
                }
            }
        });
        client.connect();
    }


}

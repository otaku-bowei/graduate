package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import sql.Sparksql;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class Consumer implements Runnable{
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final KafkaConsumer consumer;
    private String tableName ;

    public Consumer(KafkaConsumer consumer) {
        this.consumer = consumer;
    }

    public Consumer(String tableName){
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.145.41:9092");
        props.setProperty("group.id", tableName);
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.offset.reset", "earliest");//重要，重新消费问题
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        this.consumer = new KafkaConsumer<>(props);
        this.tableName = tableName ;
    }

    public void record(){
        this.consumer.subscribe(Arrays.asList(this.tableName));
        while (true) {
            ConsumerRecords<String, String> records = this.consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
    }

    @Override
    public void run() {
        try {
            this.consumer.subscribe(Arrays.asList(this.tableName));
            while (!this.closed.get()) {
                ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(10000));
                for (ConsumerRecord r:records){
                    System.out.println(r.key() + "\t" + r.value());
                    Sparksql.runSQL(r.value().toString());
                }
            }
        } catch (WakeupException e) {
            // Ignore exception if closing
            if (!this.closed.get()) throw e;
        } finally {
            this.consumer.close();
        }
    }

    // Shutdown hook which can be called from a separate thread
    public void shutdown() {
        this.closed.set(true);
        this.consumer.wakeup();
    }

    public void close(){
        this.consumer.close();
    }
}


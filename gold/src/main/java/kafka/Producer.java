package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Producer {

    private KafkaProducer<String ,String> producer ;
    private Properties props ;

    public Producer(){
        this.props = new Properties();
        this.props.put("bootstrap.servers", "192.168.145.41:9092");
        this.props.put("acks", "all");
        this.props.put("retries", 0);
        this.props.put("linger.ms", 1);
        this.props.setProperty("enable.auto.commit", "false");
        this.props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.producer = new KafkaProducer<String,String>(this.props);
    }

    public Producer(Properties p){
        this.producer = new KafkaProducer<String,String>(p);
    }

    public void send(String tableName,String sql){
        this.producer.send(new ProducerRecord<String, String>(tableName, tableName, sql));
    }

    public void close(){
        this.producer.close();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Producer(this.props) ;
    }
}

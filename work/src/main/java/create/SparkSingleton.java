package create;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;

public class SparkSingleton {

    private static SparkSession sparkSession ;

    private SparkSingleton(){
    }

    public static SparkSession getSparkSession(){
        if (sparkSession == null){
            SparkConf sparkConf = new SparkConf() ;
            //sparkConf.setMaster("spark://192.168.0.101:7077") ;

            sparkConf.set("spark.driver.allowMultipleContexts", "true");
            sparkConf.set("spark.eventLog.enabled", "true");
            sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
            sparkConf.set("spark.hadoop.validateOutputSpecs", "false");
            sparkConf.set("hive.mapred.supports.subdirectories", "true");
            sparkConf.set("mapreduce.input.fileinputformat.input.dir.recursive", "true");
            sparkSession = SparkSession.builder().appName("root").config(sparkConf).enableHiveSupport().getOrCreate();
        }
        return sparkSession ;
    }
}

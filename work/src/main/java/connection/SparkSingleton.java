package connection;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;

public class SparkSingleton {

    private static SparkSession sparkSession ;
    private SparkSingleton(){
    }

    public static SparkSession getSparkSession(){
        if (sparkSession == null){
            SparkConf sparkConf = new SparkConf() ;
            sparkConf.setMaster("spark://192.168.145.41:7077") ;
            sparkConf.set("spark.driver.allowMultipleContexts", "true");
            sparkConf.set("spark.eventLog.enabled", "true");
            sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
            sparkConf.set("spark.hadoop.validateOutputSpecs", "false");
            sparkConf.set("hive.mapred.supports.subdirectories", "true");
            sparkConf.set("hive.metastore.uris", "thrift://graduate:9083") ;
            sparkConf.set("spark.sql.warehouse.dir", "hdfs://192.168.145.41:9000/user/hive/warehouse") ;
            sparkConf.set("mapreduce.input.fileinputformat.input.dir.recursive", "true");
            sparkConf.set("spark.executor.cores","2") ;
            sparkConf.set("spark.executor.memory","4G") ;
            sparkConf.set("spark.dynamicAllocation.maxExecutors","4") ;
            sparkConf.set("spark.dynamicAllocation.initialExecutors","2") ;
            sparkConf.set("spark.sql.extensions", "io.delta.sql.DeltaSparkSessionExtension") ;
            sparkConf.set("spark.sql.catalog.spark_catalog", "org.apache.spark.sql.delta.catalog.DeltaCatalog") ;
            sparkSession = SparkSession.builder().appName("idea").config(sparkConf).enableHiveSupport().getOrCreate();
        }
        return sparkSession ;
    }

}

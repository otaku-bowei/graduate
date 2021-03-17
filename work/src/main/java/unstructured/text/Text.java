package unstructured.text;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.OutputBuffer;

import java.io.*;
import java.net.SocketException;
import java.net.URI;

public class Text {

    public static void readLocal(File file) throws IOException {
        readLocal(file.getAbsolutePath());
    }

    public static void readLocal(String path) throws IOException {

    }

    public static void readHDFS(String pathName,String savePath) throws IOException {
        String name = "hdfs://192.168.145.41:9000"+pathName ;
        // hdfs site
        FileSystem fileSystem = FileSystem.get(URI.create(name),new Configuration()) ;
        FSDataInputStream fis = fileSystem.open(new Path(name)) ;

        OutputStream fos = new BufferedOutputStream(new FileOutputStream(savePath)) ;
        try {
            IOUtils.copyBytes(fis, fos, 4096, false);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(fis);
            IOUtils.closeStream(fos);
        }
    }

    public static void saveHDFS(String pathName) throws IOException {

        //"hdfs://192.168.145.41:9000/user/spark/unstructured/"+pathName.substring(pathName.lastIndexOf("/")+1)
        String name = "hdfs://192.168.145.41:9000/user/spark/unstructured/"+pathName.substring(pathName.lastIndexOf("/")+1) ;
        // hdfs site
        InputStream fis = new BufferedInputStream(new FileInputStream(pathName)) ;
        FileSystem fileSystem = FileSystem.get(URI.create(name),new Configuration()) ;
        FSDataOutputStream fos = fileSystem.create(new Path(name)) ;

        try {
            IOUtils.copyBytes(fis, fos, 4096, false);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(fis);
            IOUtils.closeStream(fos);
        }
    }

    public static FTPClient FTP(String ip, int port, String user, String password, String path){
        FTPClient ftpClient = null ;
        try {
            /* ******连接服务器的两种方法****** */
            // 第一种方法
            // ftpClient = new FtpClient();
            // ftpClient.openServer(ip, port);
            // 第二种方法
            ftpClient = new FTPClient();
            ftpClient.connect(ip,port);
            ftpClient.login(user,password);

            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {

                ftpClient.disconnect();
            } else {
                System.out.println("connect success");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FTP的端口错误,请正确配置。");
        }
        return ftpClient;

    }

}

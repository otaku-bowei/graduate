package pretreatment;

import DAO.Mapper;
import entity.Member;
import com.csvreader.CsvReader;
import factory.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;


public class addInfo {

    public static void main(String[] args) throws Exception {

    }

    public static void readCsvAndInstallDB(String path) throws Exception {
        CsvReader csvReader = new CsvReader(path,',', Charset.forName("gbk")) ;
        while (csvReader.readRecord()){
            String s = csvReader.getRawRecord() ;
            System.out.println(s);
        }
    }
    /*
    测试获取，测试成功
     */
    public static void get() throws IOException {
        SqlSession sqlSession = MybatisUtil.getSqlSession() ;
        Mapper mapping = sqlSession.getMapper(Mapper.class) ;
        List<Member> list = mapping.getMembers() ;
        for (Member m:list){
            System.out.println(m);
        }
    }


    /*
    double插入有问题
    编码不对应
     */
    public static void insertMembers() throws IOException {
        SqlSession sqlSession = MybatisUtil.getSqlSession() ;
        Mapper mapping = sqlSession.getMapper(Mapper.class) ;
        String path = "C:/Users/defsoul/Desktop/StudyInfo/大四_上/毕设/数据集/有用/crm.csv" ;
        CsvReader csvReader = new CsvReader(path,',', Charset.forName("gbk")) ;
        while (csvReader.readRecord()){
            String s = csvReader.getRawRecord() ;
            String[] scentes = s.split(",") ;
            Member member = new Member(Integer.valueOf(scentes[0]),scentes[1],Integer.valueOf(scentes[2]),Double.valueOf(scentes[3]),Integer.valueOf(scentes[4]),scentes[5]) ;
            mapping.insertMembers(member);
        }
        sqlSession.commit();
        sqlSession.close();
    }
}
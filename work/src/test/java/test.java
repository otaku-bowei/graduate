import crm.DAO.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class test {
    @Test
    public void createMember() throws IOException {
        SqlSession sqlSession = crm.factory.Factory.getSqlSession() ;
        Mapper mapping = sqlSession.getMapper(crm.DAO.Mapper.class);
        List<Map> m = mapping.listTableColumn("member") ;
        for (Map mm:m){
            System.out.println(mm);
        }
        sqlSession.close();
    }
}

import crm.DAO.Mapper;
import crm.entity.Member;
import crm.factory.Factory;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class test {
    @Test
    public void ttt() throws IOException {
        SqlSession sqlSession = Factory.getSqlSession() ;
        Mapper mapper = sqlSession.getMapper(Mapper.class) ;
        List<Member> list  = mapper.getMembers() ;
        for (Member m :list){
            System.out.println(m);
        }
    }

}

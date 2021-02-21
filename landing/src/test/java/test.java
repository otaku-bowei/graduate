import crm.DAO.Mapper;
import crm.entity.Member;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import pos.entity.Ticket;

import java.io.IOException;
import java.util.List;

public class test {
    @Test
    public void getMember() throws IOException {
        SqlSession sqlSession = crm.factory.Factory.getSqlSession() ;
        Mapper mapping = sqlSession.getMapper(crm.DAO.Mapper.class);
        List<Member> members = mapping.getMembers() ;
        for (Member m:members){
            System.out.println(m);
        }
//        Member m = mapping.getMember(1) ;
//        System.out.println(m);
        sqlSession.close();
    }

    @Test
    public void getTickets() throws IOException {
        SqlSession sqlSession = pos.factory.Factory.getSqlSession() ;
        pos.DAO.Mapper mapping = sqlSession.getMapper(pos.DAO.Mapper.class);
        List<Ticket> members = mapping.getTickets() ;
        for (Ticket m:members){
            System.out.println(m);
        }
//        Member m = mapping.getMember(1) ;
//        System.out.println(m);
        sqlSession.close();
    }

}

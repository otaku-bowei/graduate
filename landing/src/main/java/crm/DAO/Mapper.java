package crm.DAO;

import crm.entity.Member;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface Mapper {

    @Select("select * from member")
    public List<Member> getMembers() ;

    @Select("select * from member where customerId=#{id}")
    public Member getMember(int id) ;
}

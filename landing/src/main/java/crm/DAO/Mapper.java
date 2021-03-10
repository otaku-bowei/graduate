package crm.DAO;

import crm.entity.Member;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

public interface Mapper {

    @Select("select * from member")
    public List<Member> getMembers() ;

    @Select("select * from member where customerId=#{id}")
    public Member getMember(int id) ;

    @Select("select * from information_schema.TABLES where TABLE_SCHEMA=(select database())")
    List<Map> listTable();

    @Select("select * from information_schema.COLUMNS where TABLE_SCHEMA = (select database()) and TABLE_NAME=#{tableName}")
    List<Map> listTableColumn(String tableName);


}

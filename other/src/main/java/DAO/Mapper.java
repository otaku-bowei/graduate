package DAO;

import entity.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Mapper {

    @Select("select * from member")
    public List<Member> getMembers() ;

    @Insert("insert into member(CustomerID, Genre, Age, AnnualIncome, SpendingScore, Name) VALUES(#{customerId},#{genre},#{age},#{annualIncome},#{spendingScore},#{name})")
    public void insertMembers(Member member) ;
}

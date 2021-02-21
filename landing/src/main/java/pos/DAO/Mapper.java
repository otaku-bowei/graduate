package pos.DAO;

import org.apache.ibatis.annotations.Select;
import pos.entity.Ticket;

import java.util.List;

public interface Mapper {
    @Select("select * from tickets")
    public List<Ticket> getTickets() ;
}

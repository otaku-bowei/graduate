
import create.crm.CreateCRM;
import create.pos.CreatePos;
import unstructured.text.Text;


public class Main {

    public static void main(String[] args) throws Exception {
        CreateCRM.createMemberTable("member");
        CreatePos.createTicketTable("tickets");
    }
}

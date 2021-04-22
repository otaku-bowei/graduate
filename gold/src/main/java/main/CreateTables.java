package main;

import create.crm.CreateCRM;
import create.pos.CreatePos;

public class CreateTables {
    public static void main(String[] args) throws Exception {
        CreateCRM.createMemberTable("member");
        CreatePos.createTicketTable("tickets");
    }
}

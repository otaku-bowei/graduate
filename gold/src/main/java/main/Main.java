package main;

import binlog.Canal;
import create.crm.CreateCRM;
import create.pos.CreatePos;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        new Canal("example").initConnector() ;
    }
}

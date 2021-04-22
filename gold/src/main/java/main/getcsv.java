package main;

import csv.ForCSV;
import org.codehaus.janino.IClass;

public class getcsv {
    public static void main(String[] args) {
        ForCSV.deltaForCSV("member","select * from member limit 10","/root/tmp/member.csv");
    }
}

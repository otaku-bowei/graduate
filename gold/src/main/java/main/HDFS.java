package main;

import unstructured.text.Text;

import java.io.IOException;

public class HDFS {

    public static void main(String[] args) throws IOException {
        switch (args[0]){
            case "read":Text.readHDFS(args[1],args[2]);break;
            case "write":Text.saveHDFS(args[1]);break;
        }

    }
}


import unstructured.text.Text;


public class Main {

    public static void main(String[] args) throws Exception {
        Text.readHDFS(args[0],args[1]);
    }
}

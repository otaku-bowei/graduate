public class Main {

    public static void main(String[] args) {
        System.out.println("running.........");

        try {
            System.out.println("going to sleep");
            Thread.sleep(100000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("stoped.......");
        }
    }
}

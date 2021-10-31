package Match.zzu;

public class test {


    public static void main(String[] args) {
        new Thread(() -> {
            try {
                System.out.println("nmsl");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

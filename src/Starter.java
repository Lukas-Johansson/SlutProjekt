

public class Starter {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() { ServerController.main(args); }
        }).start();
        new Thread(new Runnable() {
            public void run() { ClientController.main(null); }
        }).start();
    }
}
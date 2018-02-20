public class WaitNotify {

    static final Object mon = new Object();
    static boolean condition = false;

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (mon) {
                System.out.println("before wait");
                while (!condition) {
                    try {
                        mon.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("after wait");
            }
        }).start();

        new Thread(() -> {
            System.out.println("notifying");
            synchronized (mon) {
                condition = true;
                mon.notifyAll();
            }
        }).start();

    }

}

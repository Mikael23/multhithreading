public class InterruptExample {

    public static void main1(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            int i = 0;
            while (true) {
                if (Thread.interrupted()) { //clear flag
                    System.out.println("was interrupted");
                    return;
                }
                if (i++ % 1_000_000 == 0) {
                    System.out.println(i);
                }
            }
        });

        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {

        final Object mon = new Object();

        Thread t1 = new Thread(() -> {
            while (true) {
                synchronized (mon) {
                    try {
                        System.out.println("going to wait");
                        mon.wait();
                    } catch (InterruptedException e) {
                        System.out.println("interrupted");
                        return;
                    }
                }
            }
        });
        t1.start();
        synchronized (mon) {
            System.out.println("notify");
            mon.notifyAll();
        }
        Thread.sleep(1000);
        t1.interrupt();

    }

}

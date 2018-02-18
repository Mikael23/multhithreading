public class VolatileExample {

    static volatile int counter = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000_000; i++) {
                counter = i;
                System.out.println(i);
            }
        });
        Thread t2 = new Thread(() -> {
            System.out.println(counter);
        });

        t1.start();
        t2.start();
    }

}

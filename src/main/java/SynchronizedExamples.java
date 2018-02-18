public class SynchronizedExamples {

    static int counter = 0;
    static final Object monitor = new Object(); //wait set: set of threads trying to acquire monitor

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                synchronized (monitor) {
                    counter++;
                    //read(counter, value)
                    //count(value)
                    //write(counter, value)
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                synchronized (monitor) {
                    counter++;
                }
            }
        });
        t1.start();
        t2.start();


        t1.join(); //wait for t1 to finish
        t2.join();
        System.out.println(counter); //?


        /*

        t1:
        a = 1
        b = 2
        (a,b) = (1, 2)

        t1:
        b = 2
        a = 1
        (a,b) = (1, 2)

        * a = 1
        * b = countBoolean()
        * if (b)
        *   a = 2
        * (1, false), (2, true)
        *
        * a = 2
        * b = countBoolean()
        * if (!b)
        *   a = 1
        * (1, false), (2, true)
        * */

    }

    //t1: 0                  1 2 ... 1000 finish
    //t2:    0  1  2 .. 999  1             2 finish

    //t1: 0 1 .. 1000
    //t2:            1000 1001 .. 2000

    //t1: read
    //         read :t2
    //        count :t2
    //t1: count
    //t1: write
    //        write :t2


    //t1: read
    //t1: count
    //t1: write
    //         read :t2
    //        count :t2
    //        write :t2




    //t1, t2, t3, M
    //    t1->M .......................................   release
    //       t2 -> M : M(wait set = t2) sleep
    //         t3 -> M : M(wait set = t2, t3) sleep          t3 -> M : M(wait set = t2) ..........

}

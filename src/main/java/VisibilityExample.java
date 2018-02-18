import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VisibilityExample {
    public static void main(String[] args) {

        ExecutorService ex1 = Executors.newSingleThreadExecutor();
        ExecutorService ex2 = Executors.newSingleThreadExecutor();

        while (true) {
            Pair pair = new Pair();
            ex1.submit(() -> {
                pair.b = 1; //1
                pair.a = true; //2
            });
            ex2.submit(() -> {
                System.out.println(pair.a); //3
                System.out.println(pair.b); //4
            });
        }

    }
    //1 hb 2 (po), 3 hb 4 (po), 2 hb 3 (sa) -> 1 hb 2 hb 3 hb 4

    static class Pair {
        volatile boolean a = false;
        int b = 0;
    }

    /* synchronized actions:
            1. synchronized(mon1) { .. }; synchronized(mon1) { .. };
            2. volatile write; volatile read;
     */

}

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {

    private final int bound;
    private final Queue<T> queue = new LinkedList<>();

    public BlockingQueue(int bound) {
        this.bound = bound;
    }

    public synchronized void add(T e) throws InterruptedException { //synchronized(this)
        while (queue.size() >= bound) {
            wait();
        }
        queue.add(e);
        notifyAll();
    }

    public synchronized T get() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T result = queue.poll();
        notifyAll();
        return result;
    }

    public static void main(String[] args) {

        BlockingQueue<Integer> queue = new BlockingQueue<>(5);

        new Thread(() -> {
            while (true) {
                try {
                    queue.add(1);
                    System.out.println("added");
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    queue.add(2);
                    System.out.println("added");
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("getting");
                    System.out.println(queue.get());
                } catch (InterruptedException ignored) {}
            }
        }).start();
    }

}

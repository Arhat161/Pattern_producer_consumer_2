import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {

    private final Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 10; // limit tasks in queue
    private final Object lock = new Object(); // object for synchronization, must be constant (always add 'final') !!!

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            try {
                synchronized (lock) {
                    while (queue.size() == LIMIT) { // if in queue 10 elements
                        lock.wait(); // waiting for consume() will take away one element from queue
                    }
                    queue.offer(value++); // add value to queue and increment value
                    lock.notify(); // wake up consume() when we add value to queue
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            try {
                synchronized (lock) {
                    while (queue.size() == 0) { // if not elements in queue
                        lock.wait(); // waiting for produce() add element in queue
                    }
                    int value = queue.poll(); // get element from queue
                    System.out.println(value); // show value in console
                    System.out.println("Queue size is " + queue.size()); // show queue size in console
                    lock.notify(); // wake up produce() when we get element from queue
                }
                Thread.sleep(1000); // sleep so that produce() has time to offer(value) in queue
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

}

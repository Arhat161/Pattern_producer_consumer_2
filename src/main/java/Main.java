public class Main {

    // Example of pattern 'produce - consumer' with methods 'wait()' and 'notify()'
    // We use Queue<Integer> queue = new LinkedList<>(); in this example

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer pc = new ProducerConsumer();

        // use lambda expression for implements run() method of Runnable interface
        Thread thread1 = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // use lambda expression for implements run() method of Runnable interface
        Thread thread2 = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start(); // start thread1
        thread2.start(); // start thread2

        thread1.join(); // join thread1 to main thread
        thread2.join(); // join thread2 to main thread

    }
}

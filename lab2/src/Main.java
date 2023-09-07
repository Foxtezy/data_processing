public class Main {

    private static Runnable printer = () -> {
        for (int i = 0; i < 10; i++) {
            System.out.printf("String: %d, thread: %d\n", i, Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    };

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(printer);
        t.start();
        t.join();
        printer.run();
    }
}
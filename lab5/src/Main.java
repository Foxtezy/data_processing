public class Main {

    public static Runnable printer = () -> {
        for (int i = 0; !Thread.currentThread().isInterrupted(); i++) {
            System.out.println(i + ": ABOBA");
        }
        System.out.println("INTERRUPTED");
    };

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(printer);
        t.start();
        Thread.sleep(2000);
        t.interrupt();
        t.join();
    }
}
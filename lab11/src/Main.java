import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore semaphore1 = new Semaphore(0);
        Semaphore semaphore2 = new Semaphore(1);
        SyncPrinter printer1 = new SyncPrinter(semaphore1, semaphore2);
        printer1.start();
        SyncPrinter printer2 = new SyncPrinter(semaphore2, semaphore1);
        printer2.run();
    }
}
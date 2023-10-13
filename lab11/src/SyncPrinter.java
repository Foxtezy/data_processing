import java.util.concurrent.Semaphore;

public class SyncPrinter extends Thread {


    private final Semaphore semaphoreAcq;

    private final Semaphore semaphoreRel;

    public SyncPrinter(Semaphore semaphoreAcq, Semaphore semaphoreRel) {
        this.semaphoreAcq = semaphoreAcq;
        this.semaphoreRel = semaphoreRel;
    }


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            semaphoreAcq.acquireUninterruptibly();
            System.out.printf("String: %d, thread: %d\n", i, Thread.currentThread().getId());
            semaphoreRel.release();
        }
    }
}

import java.util.concurrent.Semaphore;

public class Widget {

    private final Semaphore semaphore = new Semaphore(0);

    public void produce(AB ab, C c) {
        ab.get();
        c.get();
        semaphore.release();
        System.out.println("+Widget");
    }

    public int getCount() {
        return semaphore.availablePermits();
    }
}

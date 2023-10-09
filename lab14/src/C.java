import java.util.concurrent.Semaphore;

public class C {

    private final Semaphore semaphore = new Semaphore(0);

    public void produce() {
        Util.sleep(3000);
        semaphore.release();
        System.out.println("+C");
    }

    public void get() {
        semaphore.acquireUninterruptibly();
    }
}

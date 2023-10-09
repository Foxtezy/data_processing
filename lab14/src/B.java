import java.util.concurrent.Semaphore;

public class B {

    private final Semaphore semaphore = new Semaphore(0);

    public void produce() {
        Util.sleep(2000);
        semaphore.release();
        System.out.println("+B");
    }

    public void get() {
        semaphore.acquireUninterruptibly();
    }
}

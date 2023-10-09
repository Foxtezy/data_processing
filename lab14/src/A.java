import java.util.concurrent.Semaphore;

public class A  {

    private final Semaphore semaphore = new Semaphore(0);

    public void produce() {
        Util.sleep(1000);
        semaphore.release();
        System.out.println("+A");
    }

    public void get() {
        semaphore.acquireUninterruptibly();
    }
}

import java.util.concurrent.Semaphore;

public class AB {

    private final Semaphore semaphore = new Semaphore(0);

    public void produce(A a, B b) {
        a.get();
        b.get();
        semaphore.release();
        System.out.println("+AB");
    }

    public void get() {
        semaphore.acquireUninterruptibly();
    }
}

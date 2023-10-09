import java.util.concurrent.atomic.LongAccumulator;

public class Philosopher extends Thread {

    private final Fork fork1;

    private final Fork fork2;

    private final LongAccumulator eaten = new LongAccumulator(Long::sum, 0);


    public Philosopher(Fork fork1, Fork fork2) {
        this.fork1 = fork1;
        this.fork2 = fork2;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            fork1.lock();
            fork2.lock();
            eat();
            fork2.unlock();
            fork1.unlock();
            think();
        }
    }

    public Long getEaten() {
        return eaten.get();
    }

    private void eat() {
        Util.sleep(1);
        eaten.accumulate(1);
    }

    private void think() {
        Util.sleep(1);
    }
}

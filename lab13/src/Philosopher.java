import java.util.concurrent.atomic.LongAccumulator;

public class Philosopher extends Thread {

    private final Forks forks;

    private final Fork fork1;

    private final Fork fork2;

    private final LongAccumulator eaten = new LongAccumulator(Long::sum, 0);


    public Philosopher(Forks forks, Fork fork1, Fork fork2) {
        this.forks = forks;
        this.fork1 = fork1;
        this.fork2 = fork2;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            forks.lockForks(fork1, fork2);
            eat();
            forks.unlockForks(fork2, fork1);
            think();
        }
    }

    public Long getEaten() {
        return eaten.get();
    }

    private void eat() {
        eaten.accumulate(1);
    }

    private void think() {

    }
}

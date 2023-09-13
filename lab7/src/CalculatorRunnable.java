import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CalculatorRunnable implements Runnable {

    private final int id;

    private final int countOfThreads;

    private final int countOfIteration;

    private final CyclicBarrier barrier;

    private double result = 0;

    public CalculatorRunnable(int id, int countOfThreads, int countOfIteration, CyclicBarrier barrier) {
        this.id = id;
        this.countOfIteration = countOfIteration;
        this.countOfThreads = countOfThreads;
        this.barrier = barrier;
    }

    public double getResult() {
        return result;
    }

    @Override
    public void run() {
        for (int i = id; i < countOfIteration; i += countOfThreads) {
            result += Math.pow(-1, (i % 2)) / (2 * i + 1);
        }
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new IllegalStateException(e);
        }
    }
}

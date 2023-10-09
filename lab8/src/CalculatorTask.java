import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.DoubleAccumulator;

public class CalculatorTask extends Thread {

    private final int id;

    private final int countOfThreads;

    private volatile long iteration = 0;

    private final CyclicBarrier barrier;

    private final DoubleAccumulator result = new DoubleAccumulator((Double::sum), 0);

    private volatile long countOfIterations = 0;

    public CalculatorTask(int id, int countOfThreads, CyclicBarrier barrier) {
        this.id = id;
        this.countOfThreads = countOfThreads;
        this.barrier = barrier;
    }

    public Double getResult() {
        return result.get();
    }

    public long getIteration() {
        return iteration / countOfThreads;
    }

    public void setCountOfIterations(long countOfIterations) {
        this.countOfIterations = countOfIterations;
    }

    @Override
    public void run() {
        for (iteration = id; !Thread.interrupted(); iteration += countOfThreads) {
            result.accumulate(Math.pow(-1, (iteration % 2)) / (2 * iteration + 1));
        }

        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }


        for ( ; (iteration / countOfThreads) < countOfIterations; iteration += countOfThreads) {
            result.accumulate(Math.pow(-1, (iteration % 2)) / (2 * iteration + 1));
        }
    }
}

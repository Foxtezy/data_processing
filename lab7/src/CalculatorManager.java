import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class CalculatorManager {

    private final List<CalculatorRunnable> calculatorRunnables = new ArrayList<>();

    public CalculatorManager(int countOfThreads, int countOfIteration, Object monitor) {
        CyclicBarrier barrier = new CyclicBarrier(countOfThreads, () -> {
            synchronized (monitor) {
                monitor.notifyAll();
            }
        });
        for (int i = 0; i < countOfThreads; i++) {
            calculatorRunnables.add(new CalculatorRunnable(i, countOfThreads, countOfIteration, barrier));
        }
    }

    public void start() {
        for (CalculatorRunnable runnable : calculatorRunnables) {
            new Thread(runnable).start();
        }
    }

    public double getResult() {
        double result = 0;
        for (CalculatorRunnable runnable : calculatorRunnables) {
            result += runnable.getResult();
        }
        return result;
    }
}

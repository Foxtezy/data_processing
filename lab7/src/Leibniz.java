import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class Leibniz {

    private final List<CalculatorRunnable> calculatorRunnables = new ArrayList<>();

    private final Object monitor = new Object();

    public double calculate(int countOfThreads, int countOfIteration) {
        CalculatorManager calculatorManager = new CalculatorManager(countOfThreads, countOfIteration, monitor);
        calculatorManager.start();
        try {
            synchronized (monitor) {
                monitor.wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return calculatorManager.getResult();
    }
}

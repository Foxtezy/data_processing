import java.util.concurrent.Callable;

public class CalculatorTask implements Callable<Double> {

    private final int id;

    private final int countOfThreads;

    private final int countOfIteration;

    public CalculatorTask(int id, int countOfThreads, int countOfIteration) {
        this.id = id;
        this.countOfIteration = countOfIteration;
        this.countOfThreads = countOfThreads;
    }

    @Override
    public Double call() {
        double result = 0;
        for (int i = id; i < countOfIteration; i += countOfThreads) {
            result += Math.pow(-1, (i % 2)) / (2 * i + 1);
        }
        return result;
    }
}

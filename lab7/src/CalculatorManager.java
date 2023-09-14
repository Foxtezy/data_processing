import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CalculatorManager {

    private final List<CalculatorTask> calculatorTasks = new ArrayList<>();

    public CalculatorManager(int countOfThreads, int countOfIteration) {
        for (int i = 0; i < countOfThreads; i++) {
            calculatorTasks.add(new CalculatorTask(i, countOfThreads, countOfIteration));
        }
    }

    public double calculate() {
        ExecutorService executor = Executors.newFixedThreadPool(calculatorTasks.size());
        List<Future<Double>> futures;
        try {
            futures = executor.invokeAll(calculatorTasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<Double> answers =  futures.stream().map(f -> {
            try {
                return f.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).toList();
        executor.shutdown();
        return answers.stream().reduce(0.0, Double::sum);
    }

}

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CalculatorManager {

    private final List<CalculatorTask> calculatorTasks = new ArrayList<>();

    private final CyclicBarrier barrier;

    public CalculatorManager(int countOfThreads) {
        barrier = new CyclicBarrier(countOfThreads, () -> {
            Long maxIteration = calculatorTasks.stream().map(CalculatorTask::getIteration).max(Long::compareTo).orElseThrow();
            calculatorTasks.forEach(t -> t.setCountOfIterations(maxIteration));
        });
        for (int i = 0; i < countOfThreads; i++) {
            calculatorTasks.add(new CalculatorTask(i, countOfThreads, barrier));
        }
    }

    public void calculate() {

        calculatorTasks.forEach(Thread::start);

        Thread printingHook = new Thread(() -> {
            calculatorTasks.forEach(Thread::interrupt);
            for (CalculatorTask task : calculatorTasks) {
                try {
                    task.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(4 * calculatorTasks.stream().map(CalculatorTask::getResult).reduce(0.0, Double::sum));
        });
        Runtime.getRuntime().addShutdownHook(printingHook);
    }
}

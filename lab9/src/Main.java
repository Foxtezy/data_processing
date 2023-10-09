import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Fork> forks = List.of(
                new Fork(), new Fork(), new Fork(), new Fork(), new Fork()
        );
        List<Philosopher> philosophers = List.of(
                new Philosopher(forks.get(0), forks.get(1)),
                new Philosopher(forks.get(1), forks.get(2)),
                new Philosopher(forks.get(2), forks.get(3)),
                new Philosopher(forks.get(3), forks.get(4)),
                new Philosopher(forks.get(0), forks.get(4))
        );
        philosophers.forEach(Thread::start);

        Runnable printResult = () -> {
            for (int i = 0; i < philosophers.size(); i++) {
                System.out.printf("Ph:%d > %d\n", i, philosophers.get(i).getEaten());
            }
        };
        Runtime.getRuntime().addShutdownHook(new Thread(printResult));
    }
}
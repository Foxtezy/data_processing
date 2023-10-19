import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Fork> forksList = List.of(
                new Fork(), new Fork(), new Fork(), new Fork(), new Fork()
        );
        Forks forks = new Forks();
        List<Philosopher> philosophers = List.of(
                new Philosopher(forks, forksList.get(0), forksList.get(1)),
                new Philosopher(forks, forksList.get(1), forksList.get(2)),
                new Philosopher(forks, forksList.get(2), forksList.get(3)),
                new Philosopher(forks, forksList.get(3), forksList.get(4)),
                new Philosopher(forks, forksList.get(4), forksList.get(0))
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
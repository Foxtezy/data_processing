import java.util.Arrays;
import java.util.List;

public class Printer implements Runnable {

    private final String[] string;

    public Printer(String... string) {
        this.string = string;
    }

    @Override
    public void run() {
        Arrays.stream(string).forEach(s -> {
            synchronized (this) {
                System.out.println(s + " thread: " + Thread.currentThread().getName());
            }
        });
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        Thread thread = new GetContent("https://en.wikipedia.org/wiki/Main_Page", queue);
        thread.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (thread.isAlive() || !queue.isEmpty()) {
                for (int i = 0; i < 25; i++) {
                    System.out.println(queue.take());
                }
                System.out.println("Press space to scroll down");
                reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
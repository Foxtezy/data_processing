import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.BlockingQueue;

public class GetContent extends Thread {

    private final String path;

    private final BlockingQueue<String> queue;

    public GetContent(String path, BlockingQueue<String> queue) {
        this.path = path;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            URL obj = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                queue.put(inputLine);
            }
            in.close();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

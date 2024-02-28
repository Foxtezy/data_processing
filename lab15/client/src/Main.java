import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel client = SocketChannel.open(new InetSocketAddress("localhost", 1111));
        for (int i = 0; i < 5; i++) {
            String str = "C++ memory model sucks " + i;
            ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
            client.write(buffer);
            buffer.clear();
            client.read(buffer);
            System.out.println(new String(buffer.array()));
        }
        client.close();
    }
}
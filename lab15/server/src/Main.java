import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Main {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChanel = ServerSocketChannel.open();
        serverSocketChanel.bind(new InetSocketAddress("localhost", 2222));
        serverSocketChanel.configureBlocking(false);
        serverSocketChanel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            var selectedKeys = selector.selectedKeys();
            var iterator = selectedKeys.iterator();
            while (iterator.hasNext()) {
                var key = iterator.next();
                if (key.isAcceptable()) {
                    SocketChannel connection = serverSocketChanel.accept();
                    connection.configureBlocking(false);
                    connection.register(selector, SelectionKey.OP_READ);
                    System.out.println("new connection established");
                }
                if (key.isReadable()) {
                    var buffer = ByteBuffer.allocate(1024);
                    var client = (SocketChannel) key.channel();
                    try {
                        var n = client.read(buffer);
                        if (n != -1) {
                            buffer.flip();
                            client.write(buffer);
                            System.out.println("send");
                        } else {
                            client.close();
                        }
                    } catch (Exception e){
                        client.close();
                    }
                }
                iterator.remove();
            }
        }
    }
}
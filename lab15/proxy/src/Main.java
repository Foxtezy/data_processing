import java.io.IOException;
import java.nio.channels.Selector;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public class Main {
    public static void main(String[] args) throws IOException {
        int destinationPort = 2222;
        String destinationHost = "localhost";

        int proxyPort = 1111;
        String proxyHost = "localhost";

        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(proxyHost, proxyPort));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();

            var selectedKeys = selector.selectedKeys();
            var iterator = selectedKeys.iterator();
            var buffer = ByteBuffer.allocate(1024);

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {

                    SocketChannel fromClient = serverSocketChannel.accept();
                    fromClient.configureBlocking(false);
                    SelectionKey fromClientKey = fromClient.register(selector, SelectionKey.OP_READ);

                    SocketChannel toServer = SocketChannel.open(new InetSocketAddress(destinationHost, destinationPort));
                    toServer.configureBlocking(false);
                    SelectionKey toServerKey = toServer.register(selector, SelectionKey.OP_READ);

                    fromClientKey.attach(toServerKey);
                    toServerKey.attach(fromClientKey);

                } else if (key.isReadable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    SocketChannel pairClient = (SocketChannel) ((SelectionKey) key.attachment()).channel();

                    if (client.isOpen() && pairClient.isOpen()) {
                        try {
                            int n = client.read(buffer);
                            if (n != -1) {
                                buffer.flip();
                                pairClient.write(buffer);
                                System.out.println("send");
                            } else {
                                client.close();
                                pairClient.close();
                            }
                        } catch (Exception e) {
                            client.close();
                            pairClient.close();
                        }
                    }

                }
                iterator.remove();
            }
        }
    }
}
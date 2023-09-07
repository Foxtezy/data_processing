import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Runnable printer = new Printer("aboba1", "aboba2", "aboba3");
        Thread t1 = new Thread(printer, "t1");
        Thread t2 = new Thread(printer, "t2");
        Thread t3 = new Thread(printer, "t3");
        Thread t4 = new Thread(printer, "t4");
        List<Thread> threads = List.of(t1, t2, t3, t4);
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }
}
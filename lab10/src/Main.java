public class Main {

    public static void main(String[] args) {
        SyncPrinter printer = new SyncPrinter();
        printer.start();
        printer.run();
    }
}
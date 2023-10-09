public class SyncPrinter extends Thread {



    @Override
    public void run() {
        for (int i = 1; ; i++) {
            System.out.printf("String: %d, thread: %d\n", i, Thread.currentThread().getId());
            synchronized (this) {
                try {
                    notifyAll();
                    if (i == 10) {
                        return;
                    }
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

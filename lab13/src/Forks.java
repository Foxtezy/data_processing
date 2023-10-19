import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Forks {

    private final Lock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    public void lockForks(Fork fork1, Fork fork2) {
        lock.lock();
        while (true) {
            if (!fork1.tryLock()) {
                condition.awaitUninterruptibly();
            } else if (!fork2.tryLock()) {
                fork1.unlock();
                condition.signalAll();
                condition.awaitUninterruptibly();
            } else {
                break;
            }
        }
        lock.unlock();
    }

    public void unlockForks(Fork fork1, Fork fork2) {
        lock.lock();
        fork1.unlock();
        fork2.unlock();
        condition.signalAll();
        lock.unlock();
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingList<T> {

    private final Lock lock = new ReentrantLock();

    private final List<T> items = new ArrayList<>();

    public T get(int index) {
        lock.lock();
        T item = items.get(index);
        lock.unlock();
        return item;
    }

    public void set(int index, T item) {
        lock.lock();
        items.set(index, item);
        lock.unlock();
    }

    public int size() {
        lock.lock();
        int size = items.size();
        lock.unlock();
        return size;
    }

    public void add(T item) {
        lock.lock();
        items.add(item);
        lock.unlock();
    }

    @Override
    public String toString() {
        lock.lock();
        String s = items.toString();
        lock.unlock();
        return s;
    }
}

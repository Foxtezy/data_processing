import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public final class Founder {
    private final List<Runnable> workers;

    private final CyclicBarrier barrier;

    public Founder(final Company company) {
        this.workers = new ArrayList<>(company.getDepartmentsCount());
        this.barrier = new CyclicBarrier(company.getDepartmentsCount(), company::showCollaborativeResult);
        for (int i = 0; i < company.getDepartmentsCount(); i++) {
            workers.add(new Worker(company.getFreeDepartment(i), barrier));
        }
    }
    public void start() {
        for (final Runnable worker : workers) {
            new Thread(worker).start();
        }
    }
}
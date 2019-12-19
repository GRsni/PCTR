import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class filoApiAN {
    private ReentrantLock lock = new ReentrantLock();
    private int[] forks;
    private Condition[] c;

    /**
     * @param tam
     * @return
     */
    public filoApiAN(int tam) {
        forks = new int[tam];
        c = new Condition[tam];
        for (int i = 0; i < tam; i++) {
            forks[i] = 2;
            c[i] = lock.newCondition();
        }
    }

    /**
     * @param i
     */
    public void take_forks(int i) {
        lock.lock();
        while (forks[i] != 2) {
            try {
                c[i].await();
            } catch (InterruptedException e) {
            }
        }
        forks[(i + 1) % forks.length]--;
        forks[(i - 1 + forks.length) % forks.length]--;
        lock.unlock();
    }

    /**
     * @param i
     */
    public void release_forks(int i) {
        lock.lock();
        forks[(i + 1) % forks.length]++;
        forks[(i - 1 + forks.length) % forks.length]++;
        if (forks[(i + 1) % 5] == 2) {
            c[(i + 1) % 5].signal();
        }
        if (forks[(i - 1 + forks.length) % forks.length] == 2) {
            c[(i - 1 + forks.length) % forks.length].signal();
        }
        lock.unlock();
    }

}
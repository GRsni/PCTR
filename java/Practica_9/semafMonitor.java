import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class semafMonitor {
    int S;
    ReentrantLock lock = new ReentrantLock();
    Condition espera;

    /**
     * @param init
     * @return
     */
    public semafMonitor(int init) {
        espera = lock.newCondition();
        S = init;
    }

    /**
     * @throws InterruptedException
     */
    public void adquirir() throws InterruptedException {
        lock.lock();
        while (S == 0) {
            espera.await();
        }
        S--;
        lock.unlock();

    }

    /**
     * @throws InterruptedException
     */
    public void soltar() throws InterruptedException {
        lock.lock();
        S++;
        espera.signalAll();
        lock.unlock();
    }
}
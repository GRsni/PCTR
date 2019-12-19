import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RWMonitorAN {
    int lectores;
    boolean escribiendo;
    private ReentrantLock lock = new ReentrantLock();
    Condition esperaL, esperaE;

    /**
     * @return
     */
    public RWMonitorAN() {
        esperaL = lock.newCondition();
        esperaE = lock.newCondition();
        lectores = 0;
        escribiendo = false;
    }

    public void startRead() {
        lock.lock();
        while (escribiendo) {
            try {
                esperaL.await();
            } catch (InterruptedException e) {
            }
        }
        lectores++;
        System.out.println("lector empieza");
        lock.unlock();
    }

    public void endRead() {
        lock.lock();
        lectores--;
        if (lectores == 0) {
            esperaE.signalAll();
        }
        System.out.println("lector acaba");
        lock.unlock();
    }

    public void startWrite() {
        lock.lock();
        while (lectores > 0 || escribiendo) {
            try {
                esperaE.await();
            } catch (InterruptedException e) {
            }
        }
        escribiendo = true;
        System.out.println("escritor empieza a escribir");
        lock.unlock();
    }

    public void endWrite() {
        lock.lock();
        escribiendo = false;
        esperaL.signalAll();
        esperaE.signalAll();
        System.out.println("escritor acaba");
        lock.unlock();
    }
}
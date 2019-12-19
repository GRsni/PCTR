import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ImpMonitor {
    private boolean[] impresoras = new boolean[3];
    int libres;
    ReentrantLock lock = new ReentrantLock();
    Condition ocupadas;

    /**
     * @return
     */
    public ImpMonitor() {
        for (int i = 0; i < 3; i++) {
            impresoras[i] = false;
        }
        libres = 3;
        ocupadas = lock.newCondition();
    }

    /**
     * @return int
     * @throws InterruptedException
     */
    public int cogerImpresora() throws InterruptedException {
        lock.lock();
        while (libres == 0) {
            ocupadas.await();
        }
        int indice = buscarImpresoraLibre();
        libres--;
        impresoras[indice] = true;
        lock.unlock();
        return indice;
    }

    /**
     * @param i
     * @throws InterruptedException
     */
    public void soltarImpresora(int i) throws InterruptedException {
        lock.lock();
        impresoras[i] = false;
        libres++;
        ocupadas.signalAll();
        lock.unlock();
    }

    /**
     * @return int
     */
    private int buscarImpresoraLibre() {
        int i = 0;
        while (impresoras[i] && i < 3) {
            i++;
        }
        return i;
    }
}
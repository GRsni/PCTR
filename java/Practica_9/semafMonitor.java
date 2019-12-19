import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase semafMonitor. Modela un semaforo Djikstra mediante ReentrantLock y
 * Condition
 * 
 * @author Santiago Jesús Mas Peña
 * @version 19/12/19
 */
public class semafMonitor {
    int S;
    ReentrantLock lock = new ReentrantLock();
    Condition espera;

    /**
     * Constructor de clase, incializa el semaforo y la condicion de bloqueo
     * 
     * @param init Valor inicial del semaforo
     * @return Devuelve una instancia de clase semafMonitor
     */
    public semafMonitor(int init) {
        espera = lock.newCondition();
        S = init;
    }

    /**
     * Metodo de adquisición del semaforo, decrementa en 1 el contador si es mayor
     * que 0, y bloquea el proceso en otro caso
     * 
     * @throws InterruptedException El metodo bloqueante puede lanzar Excepciones de
     *                              Interrupcion
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
     * Metodo de señalización del semaforo, incrementa el contador y despierta a los
     * procesos bloqueados
     * 
     * @throws InterruptedException
     */
    public void soltar() {
        lock.lock();
        S++;
        espera.signalAll();
        lock.unlock();
    }
}
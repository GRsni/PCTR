import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase RWMonitorAN. Modela un monitor de tipo lector-escritor mediante
 * ReentrantLock y Condition
 * 
 * @author Santiago Jesús Mas Peña
 * @version 19/12/19
 */
public class RWMonitorAN {
    int lectores;
    boolean escribiendo;
    private ReentrantLock lock = new ReentrantLock();
    Condition esperaL, esperaE;

    /**
     * Constructor de clase que inicializa las variables de condición y valores
     * iniciales
     * 
     * @return Instancia de la clase RWMonitorAN
     */
    public RWMonitorAN() {
        esperaL = lock.newCondition();
        esperaE = lock.newCondition();
        lectores = 0;
        escribiendo = false;
    }

    /**
     * Protocolo de entrada para los procesos lectores
     */
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

    /**
     * Protocolo de salida para los procesos lectores
     */
    public void endRead() {
        lock.lock();
        lectores--;
        if (lectores == 0) {
            esperaE.signalAll();
        }
        System.out.println("lector acaba");
        lock.unlock();
    }

    /**
     * Protocolo de entrada para los procesos escritores
     */
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

    /**
     * Protocolo de salida para los procesos escritores
     */
    public void endWrite() {
        lock.lock();
        escribiendo = false;
        esperaL.signalAll();
        esperaE.signalAll();
        System.out.println("escritor acaba");
        lock.unlock();
    }
}
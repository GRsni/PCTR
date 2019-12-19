import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase ImpMonitor. Modela el monitor para las tres impresoras empleando
 * ReentrantLock y Condition
 * 
 * @author Santiago Jesús Mas Peña
 * @version 19/12/19
 */
public class ImpMonitor {
    private boolean[] impresoras = new boolean[3];
    int libres;
    ReentrantLock lock = new ReentrantLock();
    Condition ocupadas;

    /**
     * Constructor de clase, inicializa las variables de control y las condiciones
     * 
     * @return Instancia de ImpMonitor
     */
    public ImpMonitor() {
        for (int i = 0; i < 3; i++) {
            impresoras[i] = false;
        }
        libres = 3;
        ocupadas = lock.newCondition();
    }

    /**
     * Metodo para solicitar una impresora libre
     * 
     * @return int Indice de la impresora libre para ser utilizada
     * @throws InterruptedException Los procesos que llaman al metodo pueden
     *                              bloquearse
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
     * Metodo para liberar una impresora ocupada
     * 
     * @param i Indice de la impresora a liberar
     */
    public void soltarImpresora(int i) {
        lock.lock();
        impresoras[i] = false;
        libres++;
        ocupadas.signalAll();
        lock.unlock();
    }

    /**
     * Metodo auxiliar, recorre el vector de impresoras hasta que encuentra una
     * libre
     * 
     * @return int Indice de la primera impresora libre
     */
    private int buscarImpresoraLibre() {
        int i = 0;
        while (impresoras[i] && i < 3) {
            i++;
        }
        return i;
    }
}
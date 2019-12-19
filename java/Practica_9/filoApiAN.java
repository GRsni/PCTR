import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase filoApiAN. Modela un monitor de problema de folisofos comensales
 * mediante ReentrantLock y Condition
 * 
 * @author Santiago Jesús Mas Peña
 * @version 19/12/19
 */
public class filoApiAN {
    private ReentrantLock lock = new ReentrantLock();
    private int[] forks;
    private Condition[] c;

    /**
     * Constructor de clase, incializa el tamaño del vector de tenedores y las
     * condiciones de control
     * 
     * @param tam Tamaño del vector de tenedores
     * @return Instancia de filoApiAN
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
     * Metodo de adquisición de tenedores. Si no estan libres ambos tenedores,
     * bloquea al proceso
     * 
     * @param i Indice del filosofo que toma los tenedores
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
     * Metodo de liberación de tenedores. El filosofo i suelta los dos tenedores que
     * tiene adquiridos
     * 
     * @param i Indice del filosofo que suelta los tenedores
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
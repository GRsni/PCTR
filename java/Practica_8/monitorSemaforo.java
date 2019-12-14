
/**
 * Clase Semaforo
 * 
 * Modela un semaforo Djikstra con operaciones wait() y signal()
 * 
 * @author Santiago Jesús Mas Peña
 * @version 12/12/19
 */
public class monitorSemaforo {
    private int S;

    /**
     * Constructor de clase, inicializa el semaforo
     * 
     * @param valor_inicial
     */
    public monitorSemaforo(int valor_inicial) {
        S = valor_inicial;
    }

    /**
     * Metodo wait() del semaforo teorico, si el semaforo esta a 0, bloquea, si no,
     * decrementa la variable
     */
    public synchronized void esperar() {
        while (S == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        S--;
    }

    /**
     * Metodo signal() del semaforo teorico, notifica a los procesos bloqueados e
     * incrementa el semaforo
     */
    public synchronized void senalar() {
        notifyAll();
        S++;
    }

}
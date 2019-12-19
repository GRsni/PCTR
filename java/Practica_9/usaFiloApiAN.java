import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Clase usaFiloApiAN. Modela los filosofos que piensan y comen de manera
 * concurrente, haciendo uso del monitor filoApiAN
 * 
 * @author Santiago Jesús Mas Peña
 * @version 19/12/19
 */
public class usaFiloApiAN implements Runnable {
    filoApiAN monitor;
    int indice;

    /**
     * Constructor de clase, recibe el indice del filosofo y la referencia al
     * monitor
     * 
     * @param indice  Indice del filosofo en la mesa
     * @param monitor Referencia al monitor filoApiAn
     * @return Instancia de la clase usaFiloApiAn
     */
    public usaFiloApiAN(int indice, filoApiAN monitor) {
        this.indice = indice;
        this.monitor = monitor;
    }

    /**
     * Metodo concurrente. Los filosofos piensan, y despues piden los tenedores al
     * monitor para poder comer
     */
    public void run() {
        for (;;) {
            pensar();
            monitor.take_forks(indice);
            comer();
            monitor.release_forks(indice);
        }
    }

    /**
     * Metodo principal, recibe por consola el numero de filosofos que piensan y
     * comen de manera concurrente
     * 
     * @param args Numero de filosofos a la mesa
     */
    public static void main(String[] args) {
        int numFilosofos = Integer.parseInt(args[0]);
        filoApiAN m = new filoApiAN(numFilosofos);
        ExecutorService ejecutor = Executors.newFixedThreadPool(numFilosofos);
        for (int i = 0; i < numFilosofos; i++) {
            new Thread(new usaFiloApiAN(i, m)).start();
        }
    }

    /**
     * Funcion auxiliar. Representa la accion de pensar de los filosofos
     */
    public void pensar() {
        System.out.println("Filosofo " + indice + " pensando.");
    }

    /**
     * Funcion auxiliar. Representa la accion de comer de los filosofos
     */
    public void comer() {
        System.out.println("Filosofo " + indice + " comiendo. ");
    }
}
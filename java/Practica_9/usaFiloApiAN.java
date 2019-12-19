import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class usaFiloApiAN implements Runnable {
    filoApiAN monitor;
    int indice;

    /**
     * @param indice
     * @param monitor
     * @return
     */
    public usaFiloApiAN(int indice, filoApiAN monitor) {
        this.indice = indice;
        this.monitor = monitor;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int numFilosofos = Integer.parseInt(args[0]);
        filoApiAN m = new filoApiAN(numFilosofos);
        ExecutorService ejecutor = Executors.newFixedThreadPool(numFilosofos);
        for (int i = 0; i < numFilosofos; i++) {
            new Thread(new usaFiloApiAN(i, m)).start();
        }
    }

    public void run() {
        for (;;) {
            pensar();
            monitor.take_forks(indice);
            comer();
            monitor.release_forks(indice);
        }
    }

    public void pensar() {
        System.out.println("Filosofo " + indice + " pensando.");
    }

    public void comer() {
        System.out.println("Filosofo " + indice + " comiendo. ");
    }
}
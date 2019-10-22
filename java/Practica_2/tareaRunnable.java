/**
 * Clase tareaRunnable
 * 
 * @author Santiago Jesus Mas Peña
 * @version 18/10/19
 */

public class tareaRunnable implements Runnable {
    Critica critica;
    boolean incremento;

    public tareaRunnable(Critica crit, boolean incremento) {
        this.critica = crit;
        this.incremento = incremento;
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            if (incremento) {
                critica.inc();
            } else {
                critica.dec();
            }
        }
    }

}
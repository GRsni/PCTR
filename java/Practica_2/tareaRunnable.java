/**
 * Clase tareaRunnable
 * 
 * @author Santiago Jesus Mas Peña
 * @version 18/10/19
 */

public class tareaRunnable implements Runnable {
    Critica critica;

    public tareaRunnable(Critica crit) {
        this.critica=crit;
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            critica.inc();
        }
    }

}
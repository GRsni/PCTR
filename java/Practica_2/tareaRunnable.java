/**
 * Clase tareaRunnable
 * 
 * @author Santiago Jesus Mas Peña
 * @version 18/10/19
 */

public class tareaRunnable implements Runnable {
    static int n = 0;
    boolean incremento;
    int iter;
    
    public tareaRunnable(boolean incremento, int iter) {
        this.incremento = incremento;
        this.iter = iter;
    }

    public void run() {
        for (int i = 0; i < iter; i++) {
            if (incremento) {
                n++;
            } else {
                n--;
            }
        }
    }

    static int getN() {
        return n;
    }

}
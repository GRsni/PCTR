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
    
    /**
     * Constructor de clase tareaRunnable
     * @param incremento Selector de incremento/decremento
     * @param iter Numero de iteraciones
     */
    public tareaRunnable(boolean incremento, int iter) {
        this.incremento = incremento;
        this.iter = iter;
    }

    /**
     * Metodo concurrente, incrementa o decrementa la variable de clase n
     */
    public void run() {
        for (int i = 0; i < iter; i++) {
            if (incremento) {
                n++;
            } else {
                n--;
            }
        }
    }

    /**
     * Metodo observador, devuelve la variable de clase n
     * @return Devuelve la variable de clase n
     */
    static int getN() {
        return n;
    }

}
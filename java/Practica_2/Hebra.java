
/**
 * Clase Hebra
 * 
 * @author Santiago Jesus Mas Peña
 * @version 17/10/19
 */

public class Hebra extends Thread {
    private static int n = 0;
    private int cant = 10000;
    private boolean increment;

    public Hebra(boolean inc, int cont) {
        this.increment = inc;
        this.cant=cont;
    }

    public void run() {
        for (int i = 0; i < cant; i++) {
            n = n + (increment ? 1 : -1);
            // System.err.println("n= " + n);
        }
    }

    public static int getN() {
        return n;
    }
}
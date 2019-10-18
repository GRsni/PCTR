
/**
 * Clase Hebra
 * 
 * @author Santiago Jesus Mas Peña
 * @version 17/10/19
 */

public class Hebra extends Thread {
    private static int n = 0;
    private int cant;
    private boolean increment;

    public Hebra(boolean inc, int cant) {
        this.increment = inc;
        this.cant=cant;
    }

    public Hebra(boolean inc){
        this(inc, 10000);
    }

    public void run() {
       for(int i=0; i<cant; i++){
           if(increment) n++;
           else n--;
       }
    }

    public static int getN() {
        return n;
    }
}

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

    /**
     * Constructor de clase Hebra
     * @param inc Selector de incremento/decremento
     * @param cant Numero de iteraciones a realizar
     */
    public Hebra(boolean inc, int cant) {
        this.increment = inc;
        this.cant=cant;
    }

    /**
     * Metodo concurrente, incrementa o decrementa la variable de clase n cant numero de veces
     */
    public void run() {
       for(int i=0; i<cant; i++){
           if(increment) n++;
           else n--;
       }
    }

    /**
     * Metodo observador de la variable n
     * @return Devuelve la variable de clase n
     */
    public static int getN() {
        return n;
    }
}
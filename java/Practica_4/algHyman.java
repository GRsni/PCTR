/**
 * Clase algHyman
 * 
 * @author Santiago Jesus Mas Peña
 * @version 13/11/19
 */

public class algHyman {
    static boolean[] in = new boolean[2];
    static int turno = 0;
    static int iter = 200000;
    static int n = 0;

    /**
     * Funcion principal, realiza la llamada a los procesos concurrente siguiendo el
     * algoritmo de Hyman empleando funciones Lambda
     * 
     * @param args Parametros de entrada por consola del metodo principal
     * @throws InterruptedException Todo proceso concurrente puede lanzar una
     *                              excepción que debe ser tratada.
     */
    public static void main(String[] args) throws InterruptedException {
        Runnable task0 = () -> {
            for (int i = 0; i < iter; i++) {
                in[0] = true;
                while (turno != 0) {
                    while (in[1]) {
                        Thread.yield();
                    }
                    turno = 0;
                }
                n++;
                in[0] = false;
            }
        };
        Runnable task1 = () -> {
            for (int i = 0; i < iter; i++) {
                in[1] = true;
                while (turno != 1) {
                    while (in[0]) {
                        Thread.yield();
                    }
                    turno = 1;
                }
                n--;
                in[1] = false;
            }
        };

        Thread t1 = new Thread(task0);
        t1.start();
        Thread t2 = new Thread(task1);
        t2.start();

        t1.join();
        t2.join();

        System.out.println(n);
    }
}
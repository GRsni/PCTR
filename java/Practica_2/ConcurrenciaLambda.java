/**
 * Clase ConcurrenciaLambda
 * 
 * @author Santiago Jesus Mas Peña
 * @version 22/10/19
 */
public class ConcurrenciaLambda {
    static int n = 0;

    public ConcurrenciaLambda() {
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r1 = () -> {
            for (int i = 0; i < 10000; i++)
                n++;
        };
        Runnable r2 = () -> {
            for (int i = 0; i < 10000; i++)
                n--;
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("El valor de la variable critica es " + n);
    }

}
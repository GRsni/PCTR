/**
 * Clase Usa_tareaRunnable
 * 
 * @author Santiago Jesus Mas Peña
 * @version 18/10/19
 */

public class Usa_tareaRunnable extends Thread {

    public static void main(String[] args) throws InterruptedException {
        if(args.length<1){
            System.out.println("Debe introducir el numero de iteraciones.");
            System.exit(-1);
        }
        int iteraciones=Integer.parseInt(args[0]);
        tareaRunnable tarea1 = new tareaRunnable(true, iteraciones);
        tareaRunnable tarea2 = new tareaRunnable(false, iteraciones);

        Thread t1 = new Thread(tarea1);
        Thread t2 = new Thread(tarea2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("El valor de la N es: " + tareaRunnable.getN());
    }

}
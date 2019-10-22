/**
 * Clase Usa_tareaRunnable
 * 
 * @author Santiago Jesus Mas Peña
 * @version 18/10/19
 */

public class Usa_tareaRunnable extends Thread {

    public Usa_tareaRunnable() {
    }

    public static void main(String[] args) throws InterruptedException {
        Critica critica = new Critica();
        tareaRunnable tarea1 = new tareaRunnable(critica, true);
        tareaRunnable tarea2 = new tareaRunnable(critica, false);

        Thread t1 = new Thread(tarea1);
        Thread t2 = new Thread(tarea2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("El valor de la N es: " + critica.vDato());
    }

}
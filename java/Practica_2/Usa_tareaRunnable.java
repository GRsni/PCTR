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
        tareaRunnable tarea1 = new tareaRunnable(critica);
        tareaRunnable tarea2 = new tareaRunnable(critica);

        new Thread(tarea1).start();
        new Thread(tarea2).start();

        System.out.println("El valor de la N es: " + critica.vDato());
    }

}
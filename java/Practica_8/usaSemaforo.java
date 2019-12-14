
/**
 * Clase usaSemaforo. Modela dos procesos concurrentes que acceden a una
 * variable compartida a través de un monitor de tipo semáforo
 * 
 * @author Santiago Jesús Mas Peña
 * @version 13/12/19
 */
public class usaSemaforo extends Thread {
    private static monitorSemaforo em;
    private static int n = 0;

    /**
     * Constructor de clase por defecto
     */
    public usaSemaforo() {
    }

    /**
     * Metodo principal, instancia un objeto de clase monitorSemaforo. y crea dos
     * hilos concurrentes acceden a dicho semaforo para acceder a la varable
     * compartida
     */
    public static void main(String[] args) throws InterruptedException {
        em = new monitorSemaforo(1);
        usaSemaforo usa1 = new usaSemaforo();
        usaSemaforo usa2 = new usaSemaforo();
        usa1.start();
        usa2.start();
        usa1.join();
        usa2.join();

        System.out.println(n);
    }

    /**
     * Metodo concurrente. Pide acceso al semaforo, incrementa la variable, y
     * finalmente señala la salida del semaforo al otro proceso.
     */
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            em.esperar();
            n++;
            em.senalar();
        }
    }

}
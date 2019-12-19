import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Clase triatlonBarreras. Modela el comportamiento de 100 corredores de una
 * carrera en tres fases, con salida simultánea. Al final de la carrera, gana el
 * proceso que haya obtenido el menor tiempo.
 * 
 * @author Santiago Jesús Mas Peña
 * @version 19/12/19
 */
public class triatlonBarreras implements Runnable {
    int tiempoTotal, dorsal;
    static int[] tiempos = new int[100];
    CyclicBarrier barrera;

    /**
     * Constructor de clase de tarea Runnable que modela un corredor
     * 
     * @param dorsal  Dorsal del corredor
     * @param barrera Referencia a la barrera
     * @return Instancia de clase triatlonBarrera
     */
    public triatlonBarreras(int dorsal, CyclicBarrier barrera) {
        this.dorsal = dorsal;
        this.barrera = barrera;
    }

    /**
     * Metodo concurrente. Los procesos se bloquean en la barrera, hasta su
     * activación. Esperan un tiempo aleatorio, y lo registran al finalizar su
     * ejecución
     * 
     */
    public void run() {
        try {
            int i = barrera.await();
        } catch (BrokenBarrierException brokenBarrierException) {
            System.err.println("barrera rota");
        } catch (InterruptedException e) {
        }
        // System.out.println("pasando la barrera.");
        int tiempoEspera = (int) (Math.random() * 1000 + 500);
        try {
            Thread.sleep(tiempoEspera);
        } catch (InterruptedException e) {
        }
        tiempos[dorsal] += tiempoEspera;

    }

    /**
     * Función auxiliar que busca el corredor con menor tiempo en el vector de
     * resultados, y devuelve su indice
     */
    public static void buscaGanador() {
        int min = 10000;
        int index = -1;
        for (int i = 0; i < 100; i++) {
            if (tiempos[i] < min) {
                index = i;
                min = tiempos[i];
            }
        }
        System.err.println(index + " con tiempo " + min);
    }

    /**
     * Metodo principal, crea las 100 tareas, e inicializa la barrera tres veces,
     * para simular las tres fases de la carrera
     * 
     * @param args Argumentos de consola, no se utilizan
     * @throws InterruptedException El metodo join de la clase Thread puede devolver
     *                              Interrupciones de ejecución
     */
    public static void main(String[] args) throws InterruptedException {
        int numCorredores = 100;
        CyclicBarrier barrera = new CyclicBarrier(numCorredores);
        Thread[] hilos = new Thread[numCorredores];
        for (int fases = 0; fases < 3; fases++) {
            for (int i = 0; i < 100; i++) {
                hilos[i] = new Thread(new triatlonBarreras(i, barrera));
                hilos[i].start();
            }
            for (int i = 0; i < numCorredores; i++) {
                hilos[i].join();
            }
            barrera.reset();
        }
        buscaGanador();

    }

}
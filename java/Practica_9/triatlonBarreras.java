import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class triatlonBarreras implements Runnable {
    int tiempoTotal, dorsal;
    static int[] tiempos = new int[100];
    CyclicBarrier barrera;

    public triatlonBarreras(int dorsal, CyclicBarrier barrera) {
        this.dorsal = dorsal;
        this.barrera = barrera;
    }

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
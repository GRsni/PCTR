import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class primosRunnable implements Runnable {
    static long rango;
    int id;
    long misPrimos;
    static long primosTotales;

    public primosRunnable(int id) {
        this.id = id;
        misPrimos = 0;
    }

    public void run() {
        for (long i = rango / 4 * id + 1; i < rango / 4 * (id + 1); i++) {
            if (esPrimo(i)) {
                misPrimos++;
            }
        }
        addToTotal();
    }

    public synchronized void addToTotal() {
        primosTotales += misPrimos;
    }

    private boolean esPrimo(long n) {
        boolean esPrimo = true;
        for (int i = 2; i <= Math.sqrt(n) && esPrimo; i++) {
            if (n % i == 0) {
                esPrimo = false;
            }
        }
        return esPrimo;
    }

    public static void main(String[] args) throws InterruptedException {
        long max = Long.parseLong(args[0]);
        int numHilos = 4;
        rango = max / numHilos;

        ExecutorService ejecutor = Executors.newFixedThreadPool(100);

        long initTime = System.nanoTime();
        for (int i = 0; i < numHilos; i++) {
            ejecutor.execute(new primosRunnable(i));
        }

        ejecutor.shutdown();
        ejecutor.awaitTermination(20, TimeUnit.DAYS);
        System.out.println(
                "Acabado en " + (System.nanoTime() - initTime) / 1.0e9 + ", encontrado " + (primosTotales - 1));
    }
}
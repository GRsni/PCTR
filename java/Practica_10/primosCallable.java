import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class primosCallable implements Callable<Long> {
    int id;
    static long rango;
    static long primosTotales = 0;
    Long misPrimos = new Long(0);

    public primosCallable(int id) {
        this.id = id;
    }

    public Long call() {
        for (long i = rango * id + 1; i < rango * (id + 1); i++) {
            if (esPrimo(i)) {
                misPrimos++;
            }
        }
        return misPrimos;
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

    public static void main(String[] args) {
        long max = Long.parseLong(args[0]);
        int numHilos = 4;
        rango = max / numHilos;
        ArrayList<Future<Long>> resultados = new ArrayList<Future<Long>>();
        long initTime = System.nanoTime();
        ExecutorService ejecutor = Executors.newFixedThreadPool(100);
        for (int i = 0; i < numHilos; i++) {
            resultados.add(ejecutor.submit(new primosCallable(i)));
        }

        for (Future<Long> res : resultados) {
            try {
                primosTotales += res.get();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        ejecutor.shutdown();
        System.out.println(
                "Acabado en " + (System.nanoTime() - initTime) / 1.0e9 + ", encontrado " + (primosTotales - 1));
    }
}
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.*;

/**
 * Clase numPerfectos
 * 
 * @author Santiago Jesus Mas Peña
 * @version 22/11/19
 */
public class numPerfectos implements Callable<Integer> {
    int start, end;

    /**
     * Constructor de clase, recibe el inicio y final del intervalo de calculo
     * 
     * @param start Primer elemento a calcular
     * @param end   Ultimo elemento a calcular
     */
    public numPerfectos(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Metodo callable, ejecutado de manera concurrente y almacenado en un objeto de
     * clase Future<Integer>
     */
    public Integer call() {
        int perfectos = 0;
        for (int i = start; i < end; i++) {
            int sumDivisores = 1;
            for (int j = 2; j < i; i++) {
                if (i % j == 0) {
                    sumDivisores++;
                }
            }
            if (i == sumDivisores) {
                perfectos++;
            }
        }
        return perfectos;
    }

    public static void main(String[] args) throws Exception {
        int espacio = 1000;
        int numCores = Runtime.getRuntime().availableProcessors();
        float coef = -2;
        int numHilos = (int) (numCores / (1 - coef));
        numPerfectos[] nums = new numPerfectos[numHilos];
        List<Future<Integer>> futures = new ArrayList<Future<Integer>>();

        ExecutorService ejecutor = Executors.newFixedThreadPool(3000);

        for (int i = 0; i < numHilos; i++) {
            nums[i] = new numPerfectos(espacio / numHilos * i, espacio / numHilos * (i + 1));
            futures.add(ejecutor.submit(nums[i]));
        }
        ejecutor.shutdown();
        int total = 0;
        for (int i = 0; i < numHilos; i++) {
            total += futures.get(i).get().intValue();
        }
        System.out.println(total);
    }

}
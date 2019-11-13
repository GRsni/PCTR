import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.*;

/**
 * Clase algLamport
 * 
 * @author Santiago Jesús Mas Peña
 * @version 12/11/19
 */
public class algLamport implements Runnable {
    static int n = 0;
    static int num_procesos = 10;
    static int iter = 10000000;
    private int id;
    private boolean incrementa;
    static List<Boolean> entrando = new ArrayList<>(num_procesos);
    static List<Integer> cola = new ArrayList<>(num_procesos);

    public void run() {
        for (int i = 0; i < iter; i++) {
            bloquear(id);
            soltar(id);
        }
    }

    private void bloquear(int id) {
        entrando.set(id, true);
        int max = 0;
        for (int val : cola) {
            max = Math.max(max, val);
        }
        cola.set(id, 1 + max);
        entrando.set(id, false);

        for (int i = 0; i < cola.size(); i++) {
            if (i != id) {
                while (entrando.get(i)) {
                    Thread.yield();
                }
                while (cola.get(i) != 0 && (cola.get(id) > cola.get(i) || (cola.get(id) == cola.get(i) && id > i))) {
                    Thread.yield();
                }
            }
        }

        /* Comienzo de sección critica */
        if (incrementa) {
            n++;
        } else {
            n--;
        }
        /* Fin de seccion critica */
    }

    private void soltar(int id) {
        cola.set(id, 0);
    }

    public algLamport(int id, boolean incrementa) {
        this.id = id;
        this.incrementa = incrementa;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService ejecutor = Executors.newFixedThreadPool(num_procesos);

        for(int i=0; i<num_procesos; i++){
            cola.add(0);
            entrando.add(false);
        }

        for (int i = 0; i < num_procesos; i++) {
            boolean inc = i % 2 == 0;
            ejecutor.execute(new algLamport(i, inc));
        }

        ejecutor.shutdown();
        ejecutor.awaitTermination(1, TimeUnit.DAYS);

        System.out.println(n);
    }

}
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Clase algEisenbergMcGuire
 * 
 * @author Santiago Jesus Mas Peña
 * @version 12/10/19
 */
public class algEisenbergMcGuire implements Runnable {
    static int turno = 0;
    static int iter = 1000000;
    static int n = 0;
    static pstate[] flags = new pstate[2];
    private int id;
    private boolean incrementa;

    public enum pstate {
        IDLE, WAITING, ACTIVE;
    }

    public void run() {
        for (int i = 0; i < iter; i++) {
            int index;
            do {
                flags[id] = pstate.WAITING;
                index = turno;
                while (index != id) {
                    if (flags[index] != pstate.IDLE) {
                        index = turno;
                    } else {
                        index = (index + 1) % 2;
                    }
                    Thread.yield();
                }

                flags[id] = pstate.ACTIVE;

                index = 0;
                while (index < 2 && (index == id || flags[index] != pstate.ACTIVE)) {
                    index++;
                }

            } while (!(index >= 2 && (turno == id || flags[turno] == pstate.IDLE)));

            /* Comienza seccion crítica */
            if (incrementa) {
                n++;
            } else {
                n--;
            }
            /* Fin de sección crítica */

            index = (turno + 1) % 2;
            while (flags[index] == pstate.IDLE) {
                index = (index + 1) % 2;
            }

            turno = index;

            flags[id] = pstate.IDLE;

        }
    }

    public algEisenbergMcGuire(int id, boolean incrementa) {
        this.id = id;
        this.incrementa = incrementa;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService ejecutor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 2; i++) {
            boolean inc = (i % 2 == 0);
            ejecutor.execute(new algEisenbergMcGuire(i, inc));
        }

        ejecutor.shutdown();
        ejecutor.awaitTermination(1, TimeUnit.DAYS);

        System.out.println(n);
    }
}
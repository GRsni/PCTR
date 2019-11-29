import java.util.ArrayList;

/* Adaptado de M. Ben-Ari por Manuel Francisco */

/* Algoritmo de Dekker */
class DekkerCola {
    /* Iteraciones que dara cada hilo */
    static final int iteraciones = 2000000;
    /* Recurso compartido */
    static volatile int enteroCompartido = 0;
    /* Representa el deseo del hilo P de entrar en la seccion critica */
    static volatile boolean wantp = false;
    /* Representa el deseo del hilo Q de entrar en la seccion critica */
    static volatile boolean wantq = false;
    static volatile boolean wantr = false;
    /* Representa de quien es el turno */
    static volatile ArrayList<Integer> queue = new ArrayList<>();

    class P extends Thread {
        public void run() {
            for (int i = 0; i < iteraciones; ++i) {
                /* Seccion no critica */
                wantp = true;
                queue.add(1);
                while (wantq || wantr) {
                    int turn = queue.get(0);
                    if (turn != 1) {
                        wantp = false;
                        while (turn != 1)
                            Thread.yield();
                        wantp = true;
                    }
                }

                /* Seccion critica */
                ++enteroCompartido;
                /* Fin Seccion critica */
                queue.remove(0);
                wantp = false;
            }
        }
    }

    class Q extends Thread {
        public void run() {
            for (int i = 0; i < iteraciones; ++i) {
                /* Seccion no critica */
                wantq = true;
                queue.add(2);
                while (wantp || wantr) {
                    int turn = queue.get(0);
                    if (turn != 2) {
                        wantq = false;
                        while (turn != 2)
                            Thread.yield();
                        wantq = true;
                    }
                }

                /* Seccion critica */
                --enteroCompartido;
                /* Fin Seccion critica */

                queue.remove(0);
                wantq = false;
            }
        }
    }

    class R extends Thread {
        public void run() {
            for (int i = 0; i < iteraciones; ++i) {
                /* Seccion no critica */
                wantr = true;
                queue.add(3);
                while (wantp || wantq) {
                    int turn = queue.get(0);
                    if (turn != 3) {
                        wantr = false;
                        while (turn != 3)
                            Thread.yield();
                        wantr = true;
                    }
                }

                /* Seccion critica */
                ++enteroCompartido;
                /* Fin Seccion critica */

                queue.remove(0);
                wantr = false;
            }
        }
    }

    DekkerCola() {
        Thread p = new P();
        Thread q = new Q();
        Thread r = new R();
        p.start();
        q.start();
        r.start();
        try {
            p.join();
            q.join();
            r.join();
            System.out.println("El valor del recurso compartido es " + enteroCompartido);
            System.out.println("Deberia ser " + iteraciones);
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {
        new DekkerCola();
    }

    public void showQueue() {
        // if (queue.size()>0) {
        // for (int i : queue) {
        // System.out.print(i + ", ");
        // }
        // System.out.println();
        // }
        System.out.println(queue.size());
    }
}

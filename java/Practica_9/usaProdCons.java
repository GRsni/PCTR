public class usaProdCons extends Thread {
    prodCons monitor;
    int tipo;

    /**
     * @param tipo
     * @param monitor
     * @return
     */
    public usaProdCons(int tipo, prodCons monitor) {
        this.tipo = tipo;
        this.monitor = monitor;
    }

    public void run() {
        try {
            for (int i = 0; i < 10000; i++) {
                if (tipo == 0) {
                    monitor.insertar(System.nanoTime());
                } else if (tipo == 1) {
                    System.out.println(monitor.extraer());
                }
            }
        } catch (InterruptedException e) {
        }
    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        prodCons monitor = new prodCons(100);
        int numHilos = Integer.parseInt(args[0]);
        usaProdCons[] hilos = new usaProdCons[numHilos];

        for (int i = 0; i < numHilos; i++) {
            hilos[i] = new usaProdCons(i % 2, monitor);
            hilos[i].start();
        }
        for (int i = 0; i < numHilos; i++) {
            hilos[i].join();
        }
    }
}
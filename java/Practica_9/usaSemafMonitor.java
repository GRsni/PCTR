public class usaSemafMonitor extends Thread {
    semafMonitor monitor;
    static int cuenta = 0;

    /**
     * @param monitor
     * @return
     */
    public usaSemafMonitor(semafMonitor monitor) {
        this.monitor = monitor;
    }

    public void run() {
        for (int i = 0; i < 100000; i++) {
            try {
                monitor.adquirir();
                cuenta++;
                monitor.soltar();
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        semafMonitor monitor = new semafMonitor(1);
        int numHilos = Integer.parseInt(args[0]);
        usaSemafMonitor[] hilos = new usaSemafMonitor[numHilos];
        for (int i = 0; i < numHilos; i++) {
            hilos[i] = new usaSemafMonitor(monitor);
            hilos[i].start();
        }

        for (int i = 0; i < numHilos; i++) {
            hilos[i].join();
        }

        System.out.println(cuenta);
    }
}
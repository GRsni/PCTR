public class usaImpMonitor extends Thread {
    ImpMonitor monitor;
    int id;

    /**
     * @param id
     * @param monitor
     * @return
     */
    public usaImpMonitor(int id, ImpMonitor monitor) {
        this.id = id;
        this.monitor = monitor;
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            try {
                int imp = monitor.cogerImpresora();
                System.out.println("imprimiendo " + id + " en impresora " + imp);
                monitor.soltarImpresora(imp);
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ImpMonitor monitor = new ImpMonitor();
        int numHilos = Integer.parseInt(args[0]);
        usaImpMonitor[] hilos = new usaImpMonitor[numHilos];

        for (int i = 0; i < numHilos; i++) {
            hilos[i] = new usaImpMonitor(i, monitor);
            hilos[i].start();
        }
        for (int i = 0; i < numHilos; i++) {
            hilos[i].join();
        }
    }

}
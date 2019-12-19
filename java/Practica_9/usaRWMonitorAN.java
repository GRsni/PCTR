public class usaRWMonitorAN extends Thread {
    static String texto = "";
    RWMonitorAN monitor;
    int tipo;

    public usaRWMonitorAN(int tipo, RWMonitorAN monitor) {
        this.tipo = tipo;
        this.monitor = monitor;
        System.out.println(tipo);
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            if (tipo == 0) {
                monitor.startRead();
                System.out.println(texto);
                monitor.endRead();
            } else if (tipo == 1) {
                monitor.startWrite();
                texto += "He dicho. ";
                monitor.endWrite();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RWMonitorAN monitor = new RWMonitorAN();
        int numHilos = Integer.parseInt(args[0]);
        usaRWMonitorAN[] hilos = new usaRWMonitorAN[numHilos];
        for (int i = 0; i < numHilos; i++) {
            hilos[i] = new usaRWMonitorAN(i % 2, monitor);
            hilos[i].start();
        }

        for (int i = 0; i < numHilos; i++) {
            hilos[i].join();
        }
        System.out.println(texto);
    }
}
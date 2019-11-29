import java.util.Random;

public class intParalelauniCont implements Runnable {
    static int puntos = 250000;
    static int cont = 0;
    static Object cerrojo = new Object();
    Random generador;

    public intParalelauniCont() {
        generador = new Random(System.nanoTime());
    }

    public void run() {
        for (int i = 0; i < puntos; i++) {
            float x = generador.nextFloat();
            float y = generador.nextFloat();
            synchronized (cerrojo) {
                if (inArea(x, y))
                    cont++;
            }
        }
    }

    private boolean inArea(float x, float y) {
        return (Math.sin(x) >= y);
    }

    public static void main(String[] args) throws InterruptedException {
        int numCores = Runtime.getRuntime().availableProcessors();
        float coef = 0;
        int numHilos = (int) (numCores / (1 - coef));

        Thread hilos[] = new Thread[numHilos];
        long timeStart = System.nanoTime();
        for (int i = 0; i < numHilos; i++) {
            hilos[i] = new Thread(new intParalelauniCont());
            hilos[i].start();
        }

        for (int i = 0; i < numHilos; i++) {
            hilos[i].join();
        }
        System.out.println("Estimacion: " + cont / 1000000.0 + " en " + (System.nanoTime() - timeStart) / 1000000000.0
                + " segundos");
    }
}
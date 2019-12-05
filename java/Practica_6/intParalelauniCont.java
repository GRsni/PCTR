import java.util.Random;

public class intParalelauniCont implements Runnable {
    int puntos;
    static int cont = 0;
    static Object cerrojo = new Object();
    Random generador;

    public intParalelauniCont(int puntos) {
        this.puntos = puntos;
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

        int puntosTotales = Integer.parseInt(args[0]);

        Thread hilos[] = new Thread[numHilos];
        long timeStart = System.nanoTime();
        for (int i = 0; i < numHilos; i++) {
            hilos[i] = new Thread(new intParalelauniCont(puntosTotales / numHilos));
            hilos[i].start();
        }

        for (int i = 0; i < numHilos; i++) {
            hilos[i].join();
        }
        System.out.println("Estimacion: " + cont / (float) puntosTotales + " en "
                + (System.nanoTime() - timeStart) / 1000000000.0 + " segundos");
    }
}
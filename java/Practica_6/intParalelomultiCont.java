import java.util.Random;

public class intParalelomultiCont implements Runnable {
    Random generador;
    static int puntos = 250000;
    int sum = 0;
    static int cont = 0;

    public intParalelomultiCont() {
        generador = new Random(System.nanoTime());
    }

    public void run() {
        for (int i = 0; i < puntos; i++) {
            float x = generador.nextFloat();
            float y = generador.nextFloat();
            if (inArea(x, y))
                sum++;
        }
    }

    private boolean inArea(float x, float y) {
        return (Math.sin(x) >= y);
    }

    public int getSum() {
        return sum;
    }

    public static void main(String[] args) throws InterruptedException {
        int numCores = Runtime.getRuntime().availableProcessors();
        float coef = 0;
        int numHilos = (int) (numCores / (1 - coef));

        Thread hilos[] = new Thread[numHilos];
        long timeStart = System.nanoTime();
        for (int i = 0; i < numHilos; i++) {
            hilos[i] = new Thread(new intParalelomultiCont());
            hilos[i].start();
        }

        for (int i = 0; i < numHilos; i++) {
            hilos[i].join();
        }
        for (int i = 0; i < numHilos; i++) {
            cont += hilos[i].getSum();
        }
        System.out.println("Estimacion: " + cont / 1000000.0 + " en " + (System.nanoTime() - timeStart) / 1000000000.0
                + " segundos");
    }
}
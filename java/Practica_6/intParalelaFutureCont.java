import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntToDoubleFunction;
import java.util.concurrent.*;
import java.util.Random;

public class intParalelaFutureCont implements Callable<Integer> {
    Random generador;
    int puntos;
    static int cont = 0;

    public intParalelaFutureCont(int puntos) {
        this.puntos = puntos;
        generador = new Random(System.nanoTime());
    }

    public Integer call() {
        Integer sum = 0;
        for (int i = 0; i < puntos; i++) {
            float x = generador.nextFloat();
            float y = generador.nextFloat();
            if (inArea(x, y))
                sum++;
        }
        return new Integer(sum);
    }

    private boolean inArea(float x, float y) {
        return (Math.sin(x) >= y);
    }

    public static void main(String[] args) throws InterruptedException {
        int contadorValidos = 0;
        int numCores = Runtime.getRuntime().availableProcessors();
        float coef = 0;
        int numHilos = (int) (numCores / (1 - coef));

        int puntosTotales = Integer.parseInt(args[0]);

        ExecutorService ejecutor = Executors.newFixedThreadPool(numHilos);
        ArrayList<Future<Integer>> resultados = new ArrayList<Future<Integer>>();
        long timeStart = System.nanoTime();
        for (int i = 0; i < numHilos; i++) {
            resultados.add(ejecutor.submit(new intParalelaFutureCont(puntosTotales / numHilos)));
        }

        for (Future<Integer> tarea : resultados) {
            try {
                contadorValidos += tarea.get().intValue();
            } catch (Exception e) {
                System.err.println("Excepción de tipo: " + e.toString());
            }
        }
        ejecutor.shutdown();

        System.out.println("Estimacion: " + contadorValidos / (float) puntosTotales + " en "
                + (System.nanoTime() - timeStart) / 1000000000.0 + " segundos");
    }

}
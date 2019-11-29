import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Clase resImagenPar
 * 
 * @author Santiago Jesús Mas Peña
 * @version 22/11/19
 */
public class resImagenPar implements Runnable {
    static int[][] imagen;
    int startRow, endRow;
    static int tamImagen = 10000;
    static Object cerrojo = new Object();

    /**
     * Constructor de clase, recibe la primera y ultima fila de la matrix de pixeles a calcular
     * @param start Primera fila a calcular
     * @param end Ultima fila a calcular
     */
    public resImagenPar(int start, int end) {
        startRow = start;
        endRow = end;
    }

    /**
     * Metodo run de interfaz Runnable a ejecutar concurrentemente
     * 
     * Recorre la matriz desde la fila start hasta end, y realiza el calculo del valor del pixel
     */
    public void run() {
        for (int i = startRow; i < endRow; i++) {
            for (int j = 1; j < tamImagen; j++) {
                synchronized (cerrojo) {
                    imagen[i][j] = (int) ((imagen[i][j] * 4 - imagen[i + 1][j] - imagen[i - 1][j] - imagen[i][j + 1]
                            - imagen[i][j + 1]) / 8);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        imagen = new int[tamImagen][tamImagen];

        for (int i = 0; i < tamImagen; i++) {
            for (int j = 0; j < tamImagen; j++) {
                imagen[i][j] = (int) (Math.random() * 256);
            }
        }

        ExecutorService ejecutor = Executors.newFixedThreadPool(3000);

        int numCores = Runtime.getRuntime().availableProcessors();
        float coef = .25f;
        int numHilos = (int) (numCores / (1 - coef));

        long timeStart = System.nanoTime();
        for (int i = 0; i < numHilos; i++) {
            int size = tamImagen - 2;
            ejecutor.execute(new resImagenPar(numHilos / size * i + 1, 1 + (numHilos / size * (i + 1))));
        }

        ejecutor.shutdown();
        ejecutor.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Acabado en " + (System.nanoTime() - timeStart) / 1000000000.0);

    }

}
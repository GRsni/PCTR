import java.util.concurrent.*;

/**
 * Clase prodMatricesParalelo
 * 
 * @author Santiago Jesús Mas Peña
 * @version 21/11/19
 */
public class prodMatricesParalelo implements Runnable {
    static int[][] m1, m2, mRes;
    static int tamMatrix = 2000;
    int rowStart, rowEnd, idHebra;

    /**
     * Constructor de clase. Calcula el producto vectorial de un numero especifico
     * de filas de la matriz
     * 
     * @param rowStart Fila inicial
     * @param rowEnd   Fila final
     * @param idHebra  Numero identificador de hebra
     */
    public prodMatricesParalelo(int rowStart, int rowEnd, int idHebra) {
        this.rowStart = rowStart;
        this.rowEnd = rowEnd;
        this.idHebra = idHebra;
    }

    /**
     * Metodo paralelo. Realiza el producto vectorial de las filas correspondientes
     * Emplea el metodo estático de clase prodEscalar
     */
    public void run() {
        // System.out.println("start: " + rowStart + " end: " + rowEnd);
        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = 0; j < tamMatrix; j++) {
                int sum = 0;
                for (int k = 0; k < tamMatrix; k++) {
                    sum += m1[i][k] + m2[k][j];
                }
                mRes[i][j] = sum;
            }
        }
        // System.out.println("hebra " + idHebra + " acabada.");
    }

    public static void main(String[] args) throws InterruptedException {

        m1 = new int[tamMatrix][tamMatrix];
        m2 = new int[tamMatrix][tamMatrix];
        mRes = new int[tamMatrix][tamMatrix];

        for (int i = 0; i < tamMatrix; i++) {
            for (int j = 0; j < tamMatrix; j++) {
                m1[i][j] = (int) (Math.random() * 10);
                m2[i][j] = (int) (Math.random() * 10);
                mRes[i][j] = 0;
            }
        }

        int numNucleos = Runtime.getRuntime().availableProcessors();
        float coefBloqueo = 0.25f;
        int numHilos = (int) (numNucleos / (1 - coefBloqueo));

        ExecutorService ejecutor = Executors.newFixedThreadPool(12);
        System.out.println("Numero de hilos: " + numHilos);
        long timeStart = System.nanoTime();
        for (int i = 0; i < numHilos; i++) {
            // System.out.println("start " + tamMatrix / numHilos * i + " end " + (tamMatrix
            // / numHilos * (i + 1)));
            ejecutor.execute(new prodMatricesParalelo(tamMatrix / numHilos * i, (tamMatrix / numHilos * (i + 1)), i));
        }

        ejecutor.shutdown();
        ejecutor.awaitTermination(1, TimeUnit.DAYS);
        long elapsedTime = (System.nanoTime() - timeStart);
        System.out.println("Tiempo: " + elapsedTime / 1000000000.0 + " segundos.");
    }
}
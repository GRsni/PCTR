import java.util.Arrays;

/**
 * Clase matVectorConcurrente
 * @author Santiago Jesús Mas Peña
 * @version 30/10/19
 */
public class matVectorConcurrente implements Runnable {
    static int[][] matrix;
    static int[] vRes;
    int rowStart, rowEnd, idHebra;
    int[] vectorProd;
     
    /**
     * Constructor de clase. Calcula el producto vectorial de un numero especifico de filas de la matriz
     * @param rowStart Fila inicial
     * @param rowEnd Fila final
     * @param idHebra Numero identificador de hebra
     * @param vectorProd Referencia al vector a multiplicar
     */
    public matVectorConcurrente(int rowStart, int rowEnd, int idHebra, int[] vectorProd) {
        this.rowStart = rowStart;
        this.rowEnd = rowEnd;
        this.idHebra = idHebra;
        this.vectorProd = vectorProd;
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 1) {
            System.out.println("Debe introducir el numero de hilos.");
            System.exit(-1);
        }
        int numHilos = Integer.parseInt(args[0]);
        int tamMatrix = 10000;
        matrix = new int[tamMatrix][tamMatrix];
        vRes = new int[tamMatrix];
        int[] vectorProd = new int[tamMatrix];

        for (int i = 0; i < tamMatrix; i++) {
            for (int j = 0; j < tamMatrix; j++) {
                matrix[i][j] = (int) (Math.random() * 10);
            }
            vectorProd[i] = 1;
        }
        matVectorConcurrente[] calculadores = new matVectorConcurrente[numHilos];
        for (int i = 0; i < numHilos; i++) {
            calculadores[i] = new matVectorConcurrente(tamMatrix / numHilos * i, tamMatrix / numHilos * (i + 1), i,
                    vectorProd);
        }
        long timeStart = System.nanoTime();
        Thread[] hilos = new Thread[numHilos];
        for (int i = 0; i < numHilos; i++) {
            hilos[i] = new Thread(calculadores[i]);
            hilos[i].start();
        }
        for (int i = 0; i < numHilos; i++) {
            hilos[i].join();
        }

        long elapsedTime = (System.nanoTime() - timeStart);
        System.out.println(Arrays.toString(vRes));
        System.out.println("Tiempo: " + elapsedTime / 1000000000.0 + " segundos.");
    }

    /**
     * Metodo paralelo. Realiza el producto vectorial de las filas correspondientes
     * Emplea el metodo estático de clase prodEscalar
     */
    public void run() {
        // System.out.println("start: " + rowStart + " end: " + rowEnd);
        for (int i = rowStart; i < rowEnd; i++) {
            int res = prodEscalar.productoEsc(matrix[rowStart], vectorProd);
            vRes[i] = res;
        }
    }
}
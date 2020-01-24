import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Clase conVolParalera. Realiza la convolucion de una matriz de enteros
 * mediante el uso de tareas concurrentes.
 * 
 * @author Santiago Jesús Mas Peña
 * @version 21/01/20
 */
public class conVolParalela implements Runnable {
    static int tamA;
    static private int[][] matA, res, mascara;
    private static int[][] ENFOCAR_MASK = { { 0, -1, 0 }, { -1, 5, -1 }, { 0, -1, 0 } };
    private static int[][] REALZAR_BORDES_MASK = { { 0, 0, 0 }, { -1, 1, 0 }, { 0, 0, 0 } };
    private static int[][] DETECTAR_BORDES_MASK = { { 0, 1, 0 }, { 1, 4, 1 }, { 0, 1, 0 } };
    private static int[][] SOBEL_MASK = { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
    private static int[][] SHARPEN_MASK = { { 1, -2, 1 }, { -2, 5, 2 }, { 1, -2, 1 } };
    int startRow, endRow, id;

    /**
     * Constructor de clase conVolParalela. Indica a cada hilo la fias de inicio y
     * fin que deben procesar.
     * 
     * @param startRow Fila inicial a procesar
     * @param endRow   Ultima fila a procesar
     * @param id       Identificador de tarea
     */
    public conVolParalela(int startRow, int endRow, int id) {
        this.endRow = endRow;
        this.startRow = startRow;
        this.id = id;
        System.out.println("start: " + startRow + " end: " + endRow);
    }

    /**
     * Metodo concurrente. Realiza el proceso de convolucion de la matriz matA,
     * desde la fila startRow hasta la fila endRow.
     */
    public void run() {
        for (int i = startRow; i < endRow; i++) {
            for (int j = 0; j < tamA; j++) {
                int sum = 0;

                for (int k = -1; k <= 1; k++) {
                    for (int m = -1; m <= 1; m++) {
                        // System.out.print((i + k) + " : " + (j + m) + "=" + mascara[k + 1][m + 1] + "
                        // || ");
                        if (i + k >= 0 && i + k < tamA && j + m >= 0 && j + m < tamA) {
                            sum += matA[i + k][j + m] * mascara[k + 1][m + 1];
                        }
                    }
                }
                res[i][j] = sum;
            }
        }
    }

    /**
     * Inicializa la matriz aleatoria de enteros.
     * 
     * @param tam Tamaño de la matriz a generar.
     * @return int[][] Devuelve una matriz aleatoria de tamaño tam x tam.
     */
    private static int[][] inicializarMatriz(int tam) {
        int[][] out = new int[tam][tam];
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                out[i][j] = (int) (Math.random() * 255);
            }
        }
        return out;
    }

    /**
     * Menu de selección de mascara de convolucion.
     * 
     * @return int[][] Devuelve la mascara de convolucion seleccionada.
     */
    public static int[][] elegirMatrixConvolucion() {
        Scanner in = new Scanner(System.in);
        int[][] mascara;
        System.out.println(
                "Elige la mascara de convolución\n1:Enfocar\n2:Realzar bordes\n3:Detectar bordes\n4:Filtro Sobel\n5:Filtro Sharpen");
        int opcion = in.nextInt();
        switch (opcion) {
        case 1:
            mascara = ENFOCAR_MASK;
            break;
        case 2:
            mascara = REALZAR_BORDES_MASK;
            break;
        case 3:
            mascara = DETECTAR_BORDES_MASK;
            break;
        case 4:
            mascara = SOBEL_MASK;
            break;
        case 5:
            mascara = SHARPEN_MASK;
            break;
        default:
            mascara = new int[3][3];
            System.out.println("Opcion fuera de rango.");
            System.exit(-1);
            break;
        }
        in.close();
        return mascara;
    }

    /**
     * Metodo principal, inicializa la matriz original, pide al usuario la matriz de
     * convolución, y lanza las tareas concurrentes.
     * 
     * @param args Recibe por consola el tamaño de la matriz original y el numero de
     *             hilos concurrentes a emplear.
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        if (args[0] == null) {
            System.out.println("Introduce el tamaño de la matriz inicial.");
            System.exit(-1);
        }
        tamA = Integer.parseInt(args[0]);
        if (args[1] == null) {
            System.out.println("Introduce el numero de hilos a emplear.");
            System.exit(-1);
        }
        int numHilos = Integer.parseInt(args[1]);
        matA = conVolParalela.inicializarMatriz(tamA);
        mascara = conVolParalela.elegirMatrixConvolucion();

        res = new int[tamA][tamA];
        Thread[] hilos = new Thread[numHilos];
        long startTime = System.nanoTime();
        for (int i = 0; i < numHilos; i++) {
            hilos[i] = new Thread(new conVolParalela(tamA / numHilos * i, tamA / numHilos * (i + 1), i));
            hilos[i].start();
        }

        for (int i = 0; i < numHilos; i++) {
            hilos[i].join();
        }
        System.out.println("Convolucion acabada en " + (System.nanoTime() - startTime) / 1000000000.0 + " segundos.");
    }
}
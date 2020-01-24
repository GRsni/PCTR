import java.util.Arrays;
import java.util.Scanner;

/**
 * Clase conVolSecuencial. Realiza la convolucion de una matriz de enteros,
 * permitiendo la eleccion de la mascara de convolucion.
 * 
 * @author Santiago Jesús Mas Peña
 * @version 21/01/20
 */
public class conVolSecuencial {
    private int[][] A;
    private static int[][] ENFOCAR_MASK = { { 0, -1, 0 }, { -1, 5, -1 }, { 0, -1, 0 } };
    private static int[][] REALZAR_BORDES_MASK = { { 0, 0, 0 }, { -1, 1, 0 }, { 0, 0, 0 } };
    private static int[][] DETECTAR_BORDES_MASK = { { 0, 1, 0 }, { 1, 4, 1 }, { 0, 1, 0 } };
    private static int[][] SOBEL_MASK = { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
    private static int[][] SHARPEN_MASK = { { 1, -2, 1 }, { -2, 5, 2 }, { 1, -2, 1 } };

    /**
     * Constructor de clase, inicializa la matriz original con valores aleatorios
     * entre 0 y 255.
     * 
     * @param tam Tamaño de la matriz original
     */
    public conVolSecuencial(int tam) {
        A = inicializarMatriz(tam);
    }

    /**
     * Metodo que crea e inicializa la matriz de enteros aleatoria.
     * 
     * @param tam Tamaño de la matriz a generar.
     * @return int[][] Devuelve la matriz aleatoria de tamaño tam x tam.
     */
    private int[][] inicializarMatriz(int tam) {
        int[][] out = new int[tam][tam];
        for (int i = 0; i < tam * tam; i++) {
            out[i / tam][i % tam] = (int) (Math.random() * 255);
        }
        return out;
    }

    /**
     * Metodo principal. Muestra un menu al usuario para que elija la mascara de
     * convolucion, genera la matriz aleatoria y realiza la convolucion.
     * 
     * @param args Recibe por consola el tamaño de la matriz a generar.
     */
    public static void main(String[] args) {
        if (args[0] == null) {
            System.out.println("Introduce el tamaño de la matriz a calcular.");
            System.exit(-1);
        }
        int tamA = Integer.parseInt(args[0]);
        conVolSecuencial conv = new conVolSecuencial(tamA);
        int[][] mascaraConv = conv.elegirMatrixConvolucion();
        int[][] res = new int[tamA][tamA];

        long startTime = System.nanoTime();
        for (int i = 0; i < tamA; i++) {
            for (int j = 0; j < tamA; j++) {
                int sum = 0;
                for (int k = -1; k <= 1; k++) {
                    for (int m = -1; m <= 1; m++) {
                        if (i + k >= 0 && i + k < tamA && j + m >= 0 && j + m < tamA) {
                            sum += conv.A[i + k][j + m] * mascaraConv[k + 1][m + 1];
                        }
                    }
                }
                res[i][j] = sum;
            }
        }
        System.out.println("Convolucion acabada en " + (System.nanoTime() - startTime) / 1000000000.0 + " segundos.");
    }

    /**
     * Metodo que muestra el menu de selección de matriz de convolución.
     * 
     * @return int[][] Devuelve la matriz de convolución seleccionada.
     */
    public int[][] elegirMatrixConvolucion() {
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
}
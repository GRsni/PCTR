import java.util.Arrays;

public class conVolSecuencial {
    private int[][] A;
    private static int[][] ENFOCAR_MASK = { { 0, -1, 0 }, { -1, 5, -1 }, { 0, -1, } };
    private static int[][] REALZAR_BORDES_MASK = { { 0, 0, 0 }, { -1, 1, 0 }, { 0, 0, 0 } };
    private static int[][] DETECTAR_BORDES_MASK = { { 0, 1, 0 }, { 1, 4, 1 }, { 0, 1, 0 } };
    private static int[][] SOBEL_MASK = { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
    private static int[][] SHARPEN_MASK = { { 1, -2, 1 }, { -2, 5, 2 }, { 1, -2, 1 } };

    private enum Mascaras {
        Enfocar, Realzar, Detectar, Shobel, Sharpen;
    }

    public conVolSecuencial(int tam) {
        A = inicializarMatriz(tam);
    }

    private int[][] inicializarMatriz(int tam) {
        int[][] out = new int[tam][tam];
        for (int i = 0; i < tam * tam; i++) {
            out[i / tam][i % tam] = (int) (Math.random() * 255);
        }
        return out;
    }

    public static void main(String[] args) {
        int tamA = Integer.parseInt(args[0]);
        conVolSecuencial conv = new conVolSecuencial(tamA);
        int[][] res = elegirMatrixConvolucion(Mascaras.Detectar);

        for (int i = 0; i < tamA; i++) {
            for (int j = 0; j < tamA; j++) {
                int sum = 0;
                // System.out.println("row: " + row + " col: " + col + " => " + A[row][col]);
                for (int k = -1; k <= 1; k++) {
                    for (int m = -1; m <= 1; m++) {
                        // System.out.print((i + k) + " : " + (j + m) + "=" + C[k + 1][m + 1] + " || ");
                        if (i + k >= 0 && i + k < tamA && j + m >= 0 && j + m < tamA) {
                            sum += conv.A[i + k][j + m] * C[k + 1][m + 1];
                        }
                    }
                }
                res[i][j] = sum;
            }
        }
        for (int i = 0; i < tamA; i++) {
            System.out.println(Arrays.toString(res[i]));
        }
    }

    int[][] elegirMatrixConvolucion(int opcion) {
        switch (opcion) {
        case Mascaras.Enfocar:
            return ENFOCAR_MASK;
            break;
        case Mascaras.Detectar:
            return DETECTAR_BORDES_MASK;
            break;
        case Mascaras.Detectar:
            return DETECTAR_BORDES_MASK;
            break;
        case Mascaras.Shobel:
            return SOBEL_MASK;
            break;
        case Mascaras.Sharpen:
            return SHARPEN_MASK;
            break;
        }
    }
}
/**
 * Clase matVector
 * 
 * @author Santiago Jesus Mas Peña
 * @version 30/10/19
 */
public class matVector {

    public static void main(String[] args) {
        int tam = 10000;
        int[][] matrix = new int[tam][tam];
        int[] vector = new int[tam];

        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                matrix[i][j] = (int) (Math.random() * 10);
            }
            vector[i] = (int) (Math.random() * 10);
        }
        long timeStart = System.nanoTime();
        int[] res = matVector.multiply(matrix, vector);`
        System.out.println(
                "El tiempo tardado en realizar el producto es:" + (System.nanoTime() - timeStart) / 1000000000.0);
    }

    public static int[] multiply(int[][] matrix, int[] vector) {
        int[] res = new int[vector.length];

        for (int i = 0; i < vector.length; i++) {
            res[i] = prodEscalar.productoEsc(matrix[i], vector);
        }
        return res;
    }

}
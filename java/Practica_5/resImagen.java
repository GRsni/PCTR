/**
 * Clase resImagen
 * 
 * Realiza el calculo de los pixeles de la matrix de manera estatica y
 * secuencial, sin instanciar un objeto de clase resImagen.
 * 
 * @author Santiago Jesús Mas Peña
 * @version 22/11/19
 */

public class resImagen {
    static int[][] imagen;

    public static void main(String[] args) {
        int tamImagen = 10000;
        imagen = new int[tamImagen][tamImagen];

        for (int i = 0; i < tamImagen; i++) {
            for (int j = 0; j < tamImagen; j++) {
                imagen[i][j] = (int) (Math.random() * 256);
            }
        }
        long timeStart = System.nanoTime();
        for (int i = 0; i < tamImagen; i++) {
            for (int j = 0; j < tamImagen; j++) {
                if (i > 0 && i < tamImagen - 1 && j > 0 && j < tamImagen - 1) {
                    imagen[i][j] = (int) ((imagen[i][j] * 4 - imagen[i + 1][j] - imagen[i - 1][j] - imagen[i][j + 1]
                            - imagen[i][j + 1]) / 8);
                }
            }
        }
        System.out.println("Acabado en " + (System.nanoTime() - timeStart) / 1000000000.0);
    }

}
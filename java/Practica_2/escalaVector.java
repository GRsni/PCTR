/**
 * Clase escalaVector
 * 
 * @author Santiago Jesus Mas Peña
 * @version 18/10/19
 */

public class escalaVector {
    public int tam;
    public double[] vector;

    public escalaVector(int tam) {
        this.tam = tam;
        vector = rellenaVector(tam);
    }

    private double[] rellenaVector(int tam) {
        double[] vector = new double[tam];
        for (int i = 0; i < tam; i++) {
            vector[i] = Math.random() * 10;
        }
        return vector;
    }

    public static void main(String[] args) {
        escalaVector esc = new escalaVector(10000000);
        long time = System.currentTimeMillis();
        for (int i = 0; i < esc.tam; i++) {
            esc.vector[i] *= 5;
            // System.out.println("Vector before: " + esc.vector[i]);
        }
        System.out.println("Tiempo: " + (System.currentTimeMillis() - time) / 1000d);
    }

}
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
        if (args.length < 1) {
            System.out.println("Debe introducir el tamaño del vector.");
            System.exit(-1);
        }
        int tamVector = Integer.parseInt(args[0]);
        escalaVector esc = new escalaVector(tamVector);

        long time = System.currentTimeMillis();
        for (int i = 0; i < esc.tam; i++) {
            esc.vector[i] *= 5;
            // System.out.println("Vector before: " + esc.vector[i]);
        }
        System.out.println("Tiempo: " + (System.currentTimeMillis() - time) / 1000d);
    }

}
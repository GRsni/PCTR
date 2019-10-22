/**
 * Clase escalaVPar
 * 
 * @author Santiago Jesus Mas Peña
 * @version 18/10/19
 */
public class escalaVPar extends Thread {
    private int start, end;
    float escalado;
    float[] vector;

    /**
     * 
     * @param start    indice inicio del vector
     * @param end      indice final del vector
     * @param escalado factor por el que multiplicar el vector
     */
    public escalaVPar(float[] vector, int start, int end, float escalado) {
        this.vector = vector;
        this.start = start;
        this.end = end;
        this.escalado = escalado;
    }

    public static void main(String[] args) throws InterruptedException {
        float[] vector = new float[10000000];
        escalaVPar.rellenaVector(vector);

        escalaVPar escala1 = new escalaVPar(vector, 0, (int) Math.floor(vector.length / 2), 5);
        escalaVPar escala2 = new escalaVPar(vector, (int) Math.floor(vector.length / 2), vector.length, 5);

        escala1.start();
        escala2.start();

        escala1.join();
        escala2.join();

        escala1.imprimeVector();
        escala2.imprimeVector();

    }

    public void run() {
        for (int i = start; i < end; i++) {
            vector[i] *= escalado;
        }
    }

    public static void rellenaVector(float[] vector) {
        for (int i = 0; i < vector.length; i++) {
            vector[i] = (float) Math.random();
        }
    }

    public void imprimeVector() {
        for (int i = start; i < end; i++) {
            System.out.print(vector[i] + ", ");
        }
        System.out.println("");
    }
}
import java.util.Arrays;

/**
 * Clase escalaVPar
 * 
 * @author Santiago Jesus Mas Peña
 * @version 18/10/19
 */
public class escalaVPar extends Thread {
    private int start, end;
    float escalado;
    static int[] vector;

    /**
     * @param vector   vector de enteros a
     * @param start    indice inicio del vector
     * @param end      indice final del vector
     * @param escalado factor por el que multiplicar el vector
     */
    public escalaVPar(int start, int end, float escalado) {
        this.start = start;
        this.end = end;
        this.escalado = escalado;
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 1) {
            System.out.println("Debe introducir el tamaño del vector.");
            System.exit(-1);
        }
        int tamVector = Integer.parseInt(args[0]);
        vector = new int[tamVector];
        vector = escalaVPar.rellenaVector(tamVector);

        escalaVPar[] hilosVPars = new escalaVPar[4];
        for (int i = 0; i < 4; i++) {
            hilosVPars[i] = new escalaVPar((int) (tamVector / 4.0 * i), (int) (tamVector / 4.0 * (i + 1)), 5);
        }

        for (int i = 0; i < 4; i++) {
            hilosVPars[i].start();
        }
        for (int i = 0; i < 4; i++) {
            hilosVPars[i].join();
        }
        for (int i = 0; i < 4; i++) {
            hilosVPars[i].imprimeVector();
        }
    }

    public void run() {
        for (int i = start; i < end; i++) {
            vector[i] *= escalado;
        }
    }

    public static int[] rellenaVector(int tam) {
        int[] vector = new int[tam];
        for (int i = 0; i < tam; i++) {
            vector[i] = (int) (Math.random() * 10);
        }
        return vector;
    }

    public void imprimeVector() {
        for (int i = start; i < end; i++) {
            System.out.print(vector[i] + ", ");
        }
        System.out.println("");
    }
}

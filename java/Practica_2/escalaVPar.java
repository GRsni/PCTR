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
        int numHilos = 2;
        vector = new int[tamVector];
        vector = escalaVPar.rellenaVector(tamVector);

        escalaVPar hilo1 = new escalaVPar(0, tamVector / 4, 5);
        escalaVPar hilo2 = new escalaVPar(tamVector / 4, tamVector / 2, 5);
        escalaVPar hilo3 = new escalaVPar(tamVector / 2, tamVector / 4 * 3, 5);
        escalaVPar hilo4 = new escalaVPar(tamVector / 4 * 3, tamVector, 5);

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();

        hilo1.join();
        hilo2.join();
        hilo3.join();
        hilo4.join();

        // for (int i = 0; i < numHilos; i++) {
        // hilosVPars[0].imprimeVector();
        // }
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

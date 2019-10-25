import java.util.Arrays;

public class prodEscalarParalelo extends Thread {
    int[] prodParcial, u, v;
    int idHebra, inicio, fin;

    public prodEscalarParalelo(int idHebra, int inicio, int fin, int[] prodParcial, int[] u, int[] v) {
        this.idHebra = idHebra;
        this.inicio = inicio;
        this.fin = fin;
        this.prodParcial = prodParcial;
        this.u = u;
        this.v = v;
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 1) {
            System.out.println("Debe introducir el numero de hebras.");
            System.exit(-1);
        }
        int numHebras = Integer.parseInt(args[0]);
        int tamV = 3000000;
        int[] prodParcial = new int[numHebras];
        int[] v1 = new int[tamV], v2 = new int[tamV];

        for (int i = 0; i < tamV; i++) {
            v1[i] = 1;
            v2[i] = 1;
        }
        long timeStart = System.nanoTime();
        // prodEscalarParalelo hilo1 = new prodEscalarParalelo(0, 0, tamV / 2,
        // prodParcial, v1, v2);
        // prodEscalarParalelo hilo2 = new prodEscalarParalelo(1, tamV / 2, tamV,
        // prodParcial, v1, v2);

        // hilo1.start();
        // hilo2.start();

        // hilo1.join();
        // hilo2.join();
        prodEscalarParalelo[] hilos = new prodEscalarParalelo[numHebras];
        for (int i = 0; i < numHebras; i++) {
            hilos[i] = new prodEscalarParalelo(i, (tamV / numHebras) * i, (tamV / numHebras) * (i + 1), prodParcial, v1,
                    v2);
            System.out.println("Inicio: " + hilos[i].getInicio() + " fin: " + hilos[i].getFin());
            hilos[i].start();
        }

        for (int i = 0; i < numHebras; i++) {
            hilos[i].join();
        }

        System.out.println(Arrays.toString(prodParcial));
        int sumFinal = 0;
        for (int i = 0; i < numHebras; i++) {
            sumFinal += prodParcial[i];
        }
        long processTime = (System.nanoTime() - timeStart);
        System.out.println("El producto escalar es: " + sumFinal + " calculado en " + processTime + " nanosegundos.");
    }

    public void run() {
        for (int i = inicio; i < fin; i++) {
            prodParcial[idHebra] += u[i] * v[i];
        }
    }

    public int getInicio() {
        return inicio;
    }

    public int getFin() {
        return fin;
    }
}
public class matVectorConcurrente implements Runnable {
    static int[][] matrix;
    static int[] vRes;
    int rowStart, rowEnd, idHebra;
    int[] vectorProd;

    public matVectorConcurrente(int rowStart, int rowEnd, int idHebra, int[] vectorProd) {
        this.rowStart = rowStart;
        this.rowEnd = rowEnd;
        this.idHebra = idHebra;
        this.vectorProd = vectorProd;
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 1) {
            System.out.println("Debe introducir el numero de hilos.");
            System.exit(-1);
        }
        int numHilos = Integer.parseInt(args[0]);
        int tamMatrix = 4;
        matrix = new int[tamMatrix][tamMatrix];
        vRes = new int[tamMatrix];
        int[] vectorProd = new int[tamMatrix];

        for (int i = 0; i < tamMatrix; i++) {
            for (int j = 0; j < tamMatrix; j++) {
                matrix[i][j] = (int) (Math.random() * 10);
            }
            vectorProd[i] = 1;
        }
        matVectorConcurrente[] hilos = new matVectorConcurrente[numHilos];
        // for (int i = 0; i < numHilos; i++) {
        // hilos[i]=new matVectorConcurrente(0, rowEnd, idHebra, vectorProd)
        // }
        hilos[0] = new matVectorConcurrente(0, tamMatrix / 2, 0, vectorProd);
        hilos[1] = new matVectorConcurrente(tamMatrix / 2, tamMatrix, 1, vectorProd);
        long timeStart = System.nanoTime();

        Thread t1 = new Thread(hilos[0]);
        Thread t2 = new Thread(hilos[1]);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        long elapsedTime = System.nanoTime() - timeStart;
        System.out.println("Tiempo: " + elapsedTime);
    }

    public void run() {
        for (int i = rowStart; i < rowEnd; i++) {
            int res = prodEscalar.productoEsc(matrix[rowStart], vectorProd);
            System.out.println(res);
            vRes[i] = res;
        }
    }
}
// COMPILACION:javac -cp .;%MPJ_HOME%/lib/mpj.jar distribInteg.java
// EJECUCION: mpjrun.bat -np 4 distribInteg

import mpi.*;

public class primosMPJ {

    public static void main(String[] args) {
        int id, numProcs;
        long[] n = new long[1];
        long[] misPrimos = { 0 };
        long[] primosTotales = new long[1];

        long rango = Long.parseLong(args[3]);
        // long rango = 10000;
        n[0] = (long) rango / 4;

        MPI.Init(args);
        double initTime = MPI.Wtime();
        id = MPI.COMM_WORLD.Rank();
        numProcs = MPI.COMM_WORLD.Size();

        MPI.COMM_WORLD.Bcast(n, 0, 1, MPI.LONG, 0);

        for (long i = n[0] * id + 1; i < n[0] * (id + 1); i++) {
            if (primosMPJ.esPrimo(i)) {
                misPrimos[0]++;
            }
        }

        MPI.COMM_WORLD.Reduce(misPrimos, 0, primosTotales, 0, 1, MPI.LONG, MPI.SUM, 0);
        double endTime = MPI.Wtime() - initTime;
        if (id == 0) {
            System.out.println(
                    "El numero de primos en " + rango + " es " + (primosTotales[0] - 1) + ", calculado en " + endTime);
        }
        MPI.finalize();
    }

    private static boolean esPrimo(long n) {
        boolean esPrimo = true;
        for (int i = 2; i <= Math.sqrt(n) && esPrimo; i++) {
            if (n % i == 0) {
                esPrimo = false;
            }
        }
        return esPrimo;
    }
}
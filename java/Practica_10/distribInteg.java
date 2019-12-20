//calcula de la integral de f(x)=x en [0,1] con un metodo de //Monte-Carlo distribuido
//COMPILACION:javac -cp .;%MPJ_HOME%/lib/mpj.jar distribInteg.java
//EJECUCION: mpjrun.bat -np 4 distribInteg 
//NOTA: CONFIGURAR de manera previa MPJ-Express adecuadamente

import mpi.*;
import java.lang.Math;
import java.util.Random;

/*
VARIABLES UTILIZADAS:
myid, es el rank de cada proceso
numprocs, es el numero total de procesos
n, array donde master envia numero de puntos a slaves
myhits, array donde cada slave envia puntos validos a master
myRand, generador que cada slave utilizar para tirar puntos
cx, cy, coordenadas generadas por cada slave
i, un iterador local a cada slave
hits, cuenta de puntos validos local a cada slave
points, total de puntos, a dividir entre tareas
*/

public class distribInteg {
  public static void main(String[] args) {
    int myid, numprocs;
    long[] n = new long[1];
    long[] myhits = new long[1];
    long[] totalhits = new long[1];
    Random myRand = new Random();
    double cx, cy;
    long i;
    long hits;
    long points = 800000000;
    n[0] = (long) points / 4;

    MPI.Init(args);
    double iniT = MPI.Wtime();
    myid = MPI.COMM_WORLD.Rank();
    numprocs = MPI.COMM_WORLD.Size();

    // master envia a slaves numero de puntos en broadcast
    MPI.COMM_WORLD.Bcast(n, 0, 1, MPI.LONG, 0);

    // cada slave lanza sus puntos y cuenta exitos

    hits = 0;
    for (i = 0; i < n[0]; i++) {
      cx = myRand.nextDouble();
      cy = myRand.nextDouble();
      if (cy <= cx)
        hits++;
    }
    myhits[0] = hits;

    // slaves reducen al master datos parciales, y se suman: //MPI.SUM
    MPI.COMM_WORLD.Reduce(myhits, 0, totalhits, 0, 1, MPI.LONG, MPI.SUM, 0);

    // master hace y presenta los calculos finales
    double time = MPI.Wtime() - iniT;
    if (myid == 0) {
      long totalpuntos = n[0] * numprocs;
      double aprox = (double) totalhits[0] / (double) totalpuntos;
      System.out.println("Aproximacion a integral: " + aprox);
      System.out.println("En: " + time + " segundos");
    }
    MPI.Finalize();
  }

}
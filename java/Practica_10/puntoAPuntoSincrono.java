import mpi.*;

public class puntoAPuntoSincrono {

  public static void main(String args[]) throws Exception {
    MPI.Init(args);
    int rank = MPI.COMM_WORLD.Rank();
    int size = MPI.COMM_WORLD.Size();
    int emisor = 0;
    int receptor = 1;
    int tag = 100;
    int unitSize = 1;

    if (rank == emisor) { // codigo del emsior
      int bufer[] = new int[1];
      bufer[0] = 1200;
      // emisor envia al receptor...
      MPI.COMM_WORLD.Bsend(bufer, 0, unitSize, MPI.INT, receptor, tag);
      // emisor recibe del receptor...
      MPI.COMM_WORLD.Recv(bufer, 0, unitSize, MPI.INT, receptor, tag);
      System.out.println("Emisor ha recibido el dato: 	" + bufer[0]);
    } else { // codigo del receptor
      int revbufer[] = new int[1];
      // receptor recibe del emisor...
      MPI.COMM_WORLD.Recv(revbufer, 0, unitSize, MPI.INT, emisor, tag);
      System.out.println("Receptor ha recibido el dato: 	" + revbufer[0]);
      revbufer[0] = revbufer[0] + revbufer[0];
      // receptor envia a emisor...
      MPI.COMM_WORLD.Ssend(revbufer, 0, unitSize, MPI.INT, emisor, tag);
    }
    MPI.Finalize();

  }
}
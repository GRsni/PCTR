
import java.net.*;
import java.io.*;

public class ClienteMultiple {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Introduce el numero de peticiones.");
            System.exit(-1);
        }
        int peticiones = Integer.parseInt(args[0]);
        // int puerto = 2001;
        try {
            for (int i = 0; i < peticiones; i++) {
                int n = (int) (Math.random() * 10);
                System.out.println("Realizando conexion...");
                Socket cable = new Socket("localhost", 2001);
                System.out.println("Realizada conexion a " + cable);

                PrintWriter salida = new PrintWriter(
                        new BufferedWriter(new OutputStreamWriter(cable.getOutputStream())));
                salida.println(n);
                salida.flush();
                System.out.println("Cerrando conexion...");
                cable.close();
            }

        } // try
        catch (Exception e) {
            System.out.println("Error en sockets...");
        }
    }// main
}// Cliente_Hilos

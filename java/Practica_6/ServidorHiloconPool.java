
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

public class ServidorHiloconPool implements Runnable {
    Socket enchufe;
    int id;
    static int contador = 0;

    public ServidorHiloconPool(Socket s, int id) {
        enchufe = s;
        this.id = id;
    }

    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(enchufe.getInputStream()));
            String datos = entrada.readLine();
            int j;
            int i = Integer.valueOf(datos).intValue();
            for (j = 1; j <= 20; j++) {
                System.out.println("El hilo " + this.id + " escribiendo el dato " + i);
            }
            enchufe.close();
            System.out.println("El hilo " + this.id + "cierra su conexion...");
            contador++;
        } catch (Exception e) {
            System.out.println("Error...");
        }
    }// run

    public static void main(String[] args) {
        int puerto = 2001;
        ExecutorService ejecutor = Executors.newFixedThreadPool(1000);
        try {
            ServerSocket chuff = new ServerSocket(puerto, 3000);

            while (true) {
                System.out.println("Esperando solicitud de conexion...");
                Socket cable = chuff.accept();
                System.out.println("Recibida solicitud de conexion...");
                ejecutor.execute(new ServidorHiloconPool(cable, contador));
                contador++;
            } // while

        } catch (Exception e) {
            System.out.println("Error en sockets...");
        }
    }// main

}


import java.util.concurrent.atomic.AtomicInteger;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

/**
 * Clase atomicServer. Modela un servidor concurrente que cuenta el numero de
 * conexiones con clientes mediante una variable atomicInteger
 * 
 * @author Santiago Jesús Mas Peña
 * @version 19/12/19
 */
public class atomicServer implements Runnable {
    Socket enchufe;
    int id;
    AtomicInteger contador = new AtomicInteger(0);

    /**
     * Constructor de clase
     * 
     * @param enchufe Referencia al socket abierto
     * @param id      Identificador del hilo servidor
     * @return Devuelve una instancia de atomicServer
     */
    public atomicServer(Socket enchufe, int id) {
        this.enchufe = enchufe;
        this.id = id;
    }

    /**
     * Metodo concurrente, lee el valor escrito en el InputStream, recibido del
     * cliente
     */
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(enchufe.getInputStream()));
            String datos = entrada.readLine();
            System.out.println(id + " recibe el valor " + datos);
            PrintWriter salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(enchufe.getOutputStream())));
            salida.println(contador.incrementAndGet());
            enchufe.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Metodo principal
     * 
     * @param args Argumentos de consola, no se utilizan
     */
    public static void main(String[] args) {
        int puerto = 2001;
        int contadorHilos = 0;
        ExecutorService ejecutor = Executors.newFixedThreadPool(1000);
        try {
            ServerSocket chuff = new ServerSocket(puerto, 3000);

            while (true) {
                System.out.println("Esperando solicitud de conexion...");
                Socket cable = chuff.accept();
                System.out.println("Recibida solicitud de conexion...");
                ejecutor.execute(new atomicServer(cable, contadorHilos));
                contadorHilos++;
            } // while

        } catch (Exception e) {
            System.out.println("Error en sockets...");
        }
    }
}
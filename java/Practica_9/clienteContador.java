import java.net.*;
import java.io.*;

/**
 * Clase clienteContador, modela un cliente que envia un dato al servidor
 * 
 * @author Santiago Jesús Mas Peña
 * @version 19/12/19
 */
public class clienteContador {

    /**
     * Metodo principal, se conecta a traves del Socket con el servidor, y envia por
     * el OutputStream un dato
     * 
     * @param args Arugmentos de consola, no se utilizan
     */
    public static void main(String[] args) {
        int n = (int) (Math.random() * 10);
        int puerto = 2001;
        try {
            System.out.println("Realizando conexion...");
            Socket cable = new Socket("localhost", puerto);
            System.out.println("Realizada conexion a " + cable);

            PrintWriter salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(cable.getOutputStream())));
            salida.println(n);
            salida.flush();

            System.out.println("Cerrando conexion...");
            cable.close();

        } catch (Exception e) {
            System.out.println("Error en sockets...");
        }
    }
}
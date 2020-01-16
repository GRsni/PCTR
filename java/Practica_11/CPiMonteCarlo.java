import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * Clase CPiMonteCarlo. Modela un proceso cliente que envia puntos al servidor,
 * o reinicia el calculo, mediante RMI en el proceso servidor a traves de la API
 * 
 * 
 * @author Santiago Jesús Mas Peña
 * @version 16/01/20
 */
public class CPiMonteCarlo {

    /**
     * Metodo principal. Muestra un menu por consola desde donde se realizan las
     * invocaciones remotas a los metodos del servidor.
     * 
     * @param args Parametros de consola, no se utilizan
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        boolean continuar = true;
        iPiMonteCarlo RefORemoto = (iPiMonteCarlo) Naming.lookup("montecarlo");

        while (continuar) {
            System.out.println("Elige la opcion: 1-Enviar puntos\n2-Reiniciar el calculo\n3-Salir del programa:");
            int opcion = in.nextInt();
            switch (opcion) {
            case 1:
                System.out.println("Indica el numero de puntos a añadir al servidor [Escribe -1 para salir]:");
                int nPuntos = in.nextInt();
                RefORemoto.masPuntos(nPuntos);
                break;
            case 2:
                RefORemoto.reset();
                break;
            case 3:
                continuar = false;
                break;
            default:
                System.err.println("Opcion incorrecta.");
                break;
            }
        }
        System.out.println("Saliendo del programa. Hasta luego!");
    }
}
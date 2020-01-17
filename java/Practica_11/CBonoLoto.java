import java.rmi.*;
import java.rmi.registry.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Clase CBonoLoto. Modela un cliente de apuestas BonoLoto, que se conecta con
 * el servidor SBonoLoto mediante RMI.
 * 
 * @author Santiago Jesús Mas Peña
 * @version 13/01/20
 */
public class CBonoLoto {

    /**
     * Metodo principal, muestra un menu por consola donde el cliente puede realizar
     * apuestas en el servidor.
     * 
     * @param args Parametros de entrada por consola, no se utilizan.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        boolean continuar = true;
        Scanner in = new Scanner(System.in);
        IBonoLoto RefORemoto = (IBonoLoto) Naming.lookup("//localhost/bonoloto");

        System.out.println(
                "Bienvenido al sistema de apuestas Bonoloto! Realiza tu apuesta \nde 6 numeros del 1 al 49 y gana miles de premios.");
        while (continuar) {
            int[] apuesta = Arrays.sort(CBonoLoto.introducirApuesta(in));
            System.out.println("Comprobando la apuesta: " + Arrays.toString(apuesta));

            boolean averiguado = RefORemoto.compApuesta(apuesta);
            if (averiguado) {
                System.out.println("Apuesta acertada.");
            } else {
                System.out.println("Has fallado. Intentalo de nuevo.");
            }

            System.out.print("Deseas continuar apostando?[s/n]:");
            String opcion = in.next();
            continuar = CBonoLoto.comprobarEleccionContinuar(opcion);
        }
        System.out.println("Saliendo del programa. Hasta luego!");
    }

    /**
     * Metodo que permite al usuario introducir su apuesta.
     * 
     * @param in Scanner para leer los 6 valores.
     * @return int[] Devuelve el vector de 6 numeros introducidos.
     */
    public static int[] introducirApuesta(Scanner in) {
        int[] apuesta = new int[6];

        System.out.println("\nIntroduce tu apuesta: ");

        for (int i = 0; i < 6; i++) {
            System.out.print("Numero " + (i + 1) + ": ");
            apuesta[i] = in.nextInt();
        }
        return apuesta;
    }

    /**
     * Metodo que comprueba si el usuario ha introducido la opcion de seguir
     * apostando.
     * 
     * @param opcion Opcion introducida por el usuario.
     * @return boolean Devuelve true si es 's' o 'S', false en otro caso.
     */
    public static boolean comprobarEleccionContinuar(String opcion) {
        return opcion.toLowerCase().equals("s");
    }
}
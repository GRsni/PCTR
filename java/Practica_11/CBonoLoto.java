import java.rmi.*;
import java.rmi.registry.*;
import java.util.Arrays;
import java.util.Scanner;

public class CBonoLoto {

    public static void main(String[] args) throws Exception {
        boolean continuar = true;
        Scanner in = new Scanner(System.in);
        IBonoLoto RefORemoto = (IBonoLoto) Naming.lookup("//localhost/bonoloto");

        System.out.println(
                "Bienvenido al sistema de apuestas Bonoloto! Realiza tu apuesta \nde 6 numeros del 1 al 49 y gana miles de premios.");
        while (continuar) {
            int[] apuesta = CBonoLoto.introducirApuesta(in);
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

    public static int[] introducirApuesta(Scanner in) {
        int[] apuesta = new int[6];

        System.out.println("\nIntroduce tu apuesta: ");

        for (int i = 0; i < 6; i++) {
            System.out.print("Numero " + (i + 1) + ": ");
            apuesta[i] = in.nextInt();
        }
        return apuesta;
    }

    public static boolean comprobarEleccionContinuar(String opcion) {
        return opcion.toLowerCase().equals("s");
    }
}
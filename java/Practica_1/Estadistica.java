import java.util.Scanner;

import org.w3c.dom.CDATASection;

/**********************************
 * Clase Estadistica
 * 
 * Autor: Santiago Jesus Mas Peña Fecha: 12/10/2019
 ***********************************/

public class Estadistica {
    float[] numeros;

    public Estadistica(float[] numeros) {
        this.numeros = numeros;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Debe introducir la longitud de la secuencia.");
            System.exit(-1);
        }

        int longitud = Integer.parseInt(args[0]);
        float[] numeros = Estadistica.leeNumeros(longitud);
        Estadistica est = new Estadistica(numeros);
        est.menu();

    }

    public static float[] leeNumeros(int cantidad) {
        float[] numeros = new float[cantidad];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < cantidad; i++) {
            System.out.print("Escribe un numero: ");
            numeros[i] = in.nextFloat();
        }
        in.close();
        return numeros;
    }

    public void menu() {
        Scanner in = new Scanner(System.in);
        boolean continua = true;
        while (continua) {
            System.out.println("Elige una opcion:\n[1]Media [2]Moda [3]Varianza [4]Desviación tipica [5]Salir");
            int opcion = in.nextInt();
            if (opcion > 0 && opcion < 6) {
                switch (opcion) {
                case 1:
                    calculaMedia();
                    break;
                case 2:
                    calculaModa();
                    break;
                case 3:
                    calculaVarianza();
                    break;
                case 4:
                    calculaDesvTipica();
                    break;
                case 5:
                    continua = false;
                    break;

                }
            } else {
                System.out.println("El numero introducido no es una opcion valida.");
            }
        }
        in.close();
    }

    private void calculaMedia() {
        float media = 0;
        for (int i = 0; i < numeros.length; i++) {
            media += numeros[i] / numeros.length;
        }
        System.out.println("La media es " + media);

    }

    private void calculaModa() {

    }

    private void calculaVarianza() {

    }

    private void calculaDesvTipica() {

    }

}
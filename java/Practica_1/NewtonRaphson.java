
/**********************************
 * Clase NewtonRaphson
 * 
 * Autor: Santiago Jesus Mas Peña Fecha: 12/10/2019
 ***********************************/

import java.util.Scanner;

public class NewtonRaphson {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Escribe el numero de iteraciones: ");
        int steps = in.nextInt();
        System.out.print("Escribe la primera aproximacion: ");
        double start = in.nextDouble();
        NewtonRaphson.aproximacion(steps, start);

        in.close();
    }

    public static void aproximacion(int steps, double x0) {
        double x = x0;
        // for (int i = 0; i < steps; i++) {
        //     if (Math.abs(functionCos(x)) > 0.00001) {
        //         double nextX = x - (functionCos(x) / functionCosDerivative(x));
        //         System.out.println("Iteracion " + i + " aprox: " + nextX);
        //         x = nextX;
        //     }
        // }
        for (int i = 0; i < steps; i++) {
            if (Math.abs(functionSquared(x)) > 0.00001) {
                double nextX = x - (functionSquared(x) / functionSquaredDerivative(x));
                System.out.println("Iteracion " + i + " aprox: " + nextX);
                x = nextX;
            }
        }
        System.out.println("Resultado: " + x);
    }

    private static double functionCos(double x) {
        return Math.cos(x) - Math.pow(x, 3);
    }

    private static double functionCosDerivative(double x) {
        return -Math.sin(x) - 3 * Math.pow(x, 2);
    }

    public static double functionSquared(double x){
        return x*x-5;
    }

    public static double functionSquaredDerivative(double x){
        return 2*x;
    }
}
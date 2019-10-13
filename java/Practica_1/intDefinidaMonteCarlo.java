
/**********************************
 * Clase intDefinidaMonteCarlo
 * 
 * Autor: Santiago Jesus Mas Peña Fecha: 11/10/2019
 ***********************************/

import java.util.Scanner;

class intDefinidaMonteCarlo {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Escribe en numero de iteraciones: ");
        int steps = in.nextInt();
        intDefinidaMonteCarlo.calculaMonteCarlo(steps);
        in.close();
    }

    public static void calculaMonteCarlo(int steps) {
        int exitos = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < steps; i++) {
            double randX = Math.random();
            double randY = Math.random();
            if (randY <= functionX(randX)) {
                exitos++;
            }
        }
        double timeLapse = (System.currentTimeMillis() - startTime) / 1000.0;
        System.out.println(String.format("Integral aproximada: %f in %f seconds ", (double) exitos / steps, timeLapse));
    }

    private static double functionX(double x) {
        return x;
    }

}
import java.util.Scanner;

public class NewtonRaphson {
    final int steps;
    final double x0;

    public NewtonRaphson(int steps, double x0) {
        this.steps = steps;
        this.x0 = x0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Escribe el numero de iteraciones: ");
        int steps = in.nextInt();
        System.out.print("Escribe la primera aproximacion: ");
        double start = in.nextDouble();
        NewtonRaphson entidad = new NewtonRaphson(steps, start);
        entidad.aproximacion();

        in.close();
    }

    void aproximacion() {
        double x = x0;
        for (int i = 0; i < steps; i++) {
            if (Math.abs(functionCos(x)) > 0.00001) {
                double nextX = x - (functionCos(x) / functionCosDerivative(x));
                System.out.println("Iteracion " + i + " aprox: " + nextX);
                x = nextX;
            }
        }
        System.out.println("Resultado: " + x);
    }

    double functionCos(double x) {
        return Math.cos(x) - Math.pow(x, 3);
    }

    double functionCosDerivative(double x) {
        return -Math.sin(x) - 3 * Math.pow(x, 2);
    }

}
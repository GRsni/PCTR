/**********************************
 * Clase Complejos
 * 
 * Autor: Santiago Jesus Mas Peña Fecha: 15/10/2019
 ***********************************/

public class Complejos {
    double[] pair = new double[2];

    public Complejos(double real, double imaginary) {
        pair[0] = real;
        pair[1] = imaginary;
    }

    public double[] getPair() {
        return pair;
    }

    public void setPair(double[] pair) {
        if (pair.length == 2) {
            this.pair = pair;
        }
    }

    @Override
    public String toString() {
        return (pair[0] + " + " + pair[1] + "i");
    }

    public static Complejos suma(Complejos a, Complejos b) {
        return new Complejos(a.pair[0] + b.pair[0], a.pair[1] + b.pair[1]);
    }

    public static Complejos resta(Complejos a, Complejos b) {
        return new Complejos(a.pair[0] - b.pair[0], a.pair[1] - b.pair[1]);
    }

    public static double modulo(Complejos a) {
        return Math.sqrt(Math.pow(a.pair[0], 2) + Math.pow(a.pair[1], 2));
    }

    public static Complejos producto(Complejos a, Complejos b) {
        return new Complejos(a.pair[0] * b.pair[0] - a.pair[1] * b.pair[1],
                a.pair[0] * b.pair[1] + a.pair[1] * b.pair[0]);
    }

    public static Complejos cociente(Complejos a, Complejos b) {
        Complejos conjugado=new Complejos(b.pair[0], -b.pair[1]);
        double denominador=Complejos.conjugado(b);
        Complejos numerador=Complejos.producto(a, conjugado);
        return new Complejos(numerador.pair[0]/denominador, numerador.pair[1]/denominador);
    }

    public static double conjugado(Complejos a){
        Complejos c=Complejos.producto(a, new Complejos(a.pair[0], -a.pair[1]));
        return c.pair[0];
    }
}
/**
 * Clase Usa_Hebra
 * 
 * @author Santiago Jesus Mas Peña
 * @version 17/10/19
 */

public class Usa_Hebra {
    public static void main(String[] args) throws InterruptedException {
        if(args.length<1){
            System.out.println("Debe introducir el numero de iteraciones a realizar.");
            System.exit(-1);
        }
        int cant = Integer.parseInt(args[0]);
        Hebra hebra1 = new Hebra(true, cant);
        Hebra hebra2 = new Hebra(false, cant);

        hebra1.start();
        hebra2.start();

        hebra1.join();
        hebra2.join();

        System.out.println("El valor de la N es: " + Hebra.getN());
    }

}
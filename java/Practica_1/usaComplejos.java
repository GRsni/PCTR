
/**********************************
 * Clase usaComplejos
 * 
 * Autor: Santiago Jesus Mas Peña Fecha: 15/10/2019
 ***********************************/

public class usaComplejos {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean continua = true;
        while (continua) {
            System.out.println(
                    "Elige la opcion a calcular:\n[1]Suma [2]Resta [3]Modulo [4]Producto [5]Cociente [6]Salir");
            int opcion = in.nextInt();
            if (opcion == 6) {
                continua = false;
                System.out.println("Saliendo de la aplicacion.");
            } else {
                usaComplejos.menu(opcion, in);
            }
        }
        in.close();

    }

    public static void menu(int opcion, Scanner in) {
        switch (opcion) {
        case 1:
            suma(in);
            break;
        case 2:
            resta(in);
            break;
        case 3:
            modulo(in);
            break;
        case 4:
            producto(in);
            break;
        case 5:
            cociente(in);
            break;
        default:
            System.out.println("Opcion no correcta. Elige otra opcion.");
            break;
        }
    }

    public static Complejos leeNumeroComplejo(Scanner in) {
        double real, imag;
        System.out.print("Introduce la parte real:");
        real = in.nextDouble();
        System.out.print("Introduce la parte imaginaria: ");
        imag = in.nextDouble();
        return new Complejos(real, imag);
    }

    public static void suma(Scanner in) {
        Complejos a = leeNumeroComplejo(in);
        System.out.println("Numero : " + a.toString());
        Complejos b = leeNumeroComplejo(in);
        System.out.println("Numero : " + b.toString());

        System.out.println("El resultado es " + Complejos.suma(a, b).toString());
    }

    public static void resta(Scanner in) {
        Complejos a = leeNumeroComplejo(in);
        System.out.println("Numero : " + a.toString());
        Complejos b = leeNumeroComplejo(in);
        System.out.println("Numero : " + b.toString());

        System.out.println("El resultado es " + Complejos.resta(a, b).toString());
    }

    public static void modulo(Scanner in) {
        Complejos a = leeNumeroComplejo(in);
        System.out.println("Numero : " + a.toString());

        System.out.println("El resultado es " + Complejos.modulo(a));
    }

    public static void producto(Scanner in) {
        Complejos a = leeNumeroComplejo(in);
        System.out.println("Numero : " + a.toString());
        Complejos b = leeNumeroComplejo(in);
        System.out.println("Numero : " + b.toString());

        System.out.println("El resultado es " + Complejos.producto(a, b).toString());
    }

    public static void cociente(Scanner in) {
        Complejos a = leeNumeroComplejo(in);
        System.out.println("Numero : " + a.toString());
        Complejos b = leeNumeroComplejo(in);
        System.out.println("Numero : " + b.toString());

        System.out.println("El resultado es " + Complejos.cociente(a, b).toString());
    }
}
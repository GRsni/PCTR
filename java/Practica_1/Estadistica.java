
/**********************************
 * Clase Estadistica
 * 
 * Autor: Santiago Jesus Mas Peña Fecha: 12/10/2019
 ***********************************/

public class Estadistica {
    int[] numeros;

    public Estadistica(int[] numeros) {
        this.numeros = numeros;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Debe introducir la longitud de la secuencia.");
            System.exit(-1);
        }
        Scanner in = new Scanner(System.in);
        int longitud = Integer.parseInt(args[0]);
        int[] numeros = Estadistica.leeNumeros(longitud, in);
        Arrays.sort(numeros);
        Estadistica est = new Estadistica(numeros);
        boolean continua = true;
        while (continua) {
            System.out.println("Elige una opcion:\n[1]Media [2]Moda [3]Varianza [4]Desviacion tipica [5]Salir");
            int opcion = in.nextInt();
            continua = est.menu(opcion);
        }
        in.close();
    }

    public static int[] leeNumeros(int cantidad, Scanner in) {
        int[] numeros = new int[cantidad];
        for (int i = 0; i < cantidad; i++) {
            System.out.print("Escribe un numero: ");
            numeros[i] = in.nextInt();
        }
        return numeros;
    }

    public boolean menu(int opcion) {
        boolean valido = true;
        if (opcion > 0 && opcion < 6) {
            switch (opcion) {
            case 1:
                System.out.println("La media es " + calculaMedia());
                break;
            case 2:
                System.out.println("La moda es " + calculaModa());
                break;
            case 3:
                System.out.println("La varianza es " + calculaVarianza());
                break;
            case 4:
                System.out.println("La desviacion tipica es " + calculaDesvTipica());
                break;
            case 5:
                System.out.println("Saliendo de la aplicacion.");
                valido = false;
                break;

            }
        } else {
            System.out.println("El numero introducido no es una opcion valida.");
        }
        return valido;
    }

    private float calculaMedia() {
        float media = 0;
        for (int i = 0; i < numeros.length; i++) {
            media += numeros[i] / numeros.length;
        }
        return media;

    }

    private float calculaModa() {
        int maxNumRep = 0, moda = 0;
        for (int i = 0; i < numeros.length; i++) {
            int numRep = 0;
            for (int j = 0; j < numeros.length; j++) {
                if (numeros[i] == numeros[j]) {
                    numRep++;
                }
                if (numRep > maxNumRep) {
                    maxNumRep = numRep;
                    moda = numeros[i];
                }
            }
        }
        return moda;
    }

    private float calculaVarianza() {
        float media=calculaMedia();
        float acum=0;
        for(int i=0; i<numeros.length;i++){
            acum+=Math.pow(numeros[i]-media, 2);
        }
        return acum/numeros.length-1;

    }

    private float calculaDesvTipica() {
        return (float)Math.sqrt(calculaVarianza());
    }

}
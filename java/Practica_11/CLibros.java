import java.rmi.*;
import java.rmi.registry.*;
import java.util.Scanner;

/**
 * Clase CLibros. Modela el cliente del servidor bibliografico. Puede acceder a
 * los libros almacenados en el servidor mediante RMI
 * 
 * @author Santiago Jesús Mas Peña
 * @version 13/01/20
 */
public class CLibros {

    /**
     * Metodo principal. Muestra un menu, desde donde se elige la accion a realizar
     * en el servidor, obteniendo o insertando informacion en la base de datos del
     * servidor
     * 
     * @param args Parametros de entrada por consola, no se emplean
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        boolean continuar = true;
        Scanner in = new Scanner(System.in);
        ILibros RefORemoto = (ILibros) Naming.lookup("//localhost/bibliografia");

        while (continuar) {
            System.out.println("Elige la accion a realizar:\n1: Añadir libro\n2: Eliminar libro\n3: Buscar un libro");
            System.out.println("4: Leer libro\n5: Obtener titulo de libro\n6: Obtener autor de libro\n7: Salir");
            int opcion = in.nextInt();
            System.out.println();
            switch (opcion) {
            case 1:
                CLibros.introducirLibroEnBase(RefORemoto, in);
                break;
            case 2:
                CLibros.eliminarLibroDeBase(RefORemoto, in);
                break;
            case 3:
                CLibros.buscarLibro(RefORemoto, in);
                break;
            case 4:
                String contenido = CLibros.leerLibro(RefORemoto, in);
                System.out.println("Contenido: \n" + contenido);
                break;
            case 5:
                String titulo = CLibros.obtenerTitulo(RefORemoto, in);
                System.out.println("El titulo del libro es: " + titulo);
                break;
            case 6:
                String autor = CLibros.obtenerAutor(RefORemoto, in);
                System.out.println("El autor del libro es: " + autor);
                break;
            case 7:
                continuar = false;
                break;
            default:
                System.out.println("Opción incorrecta.");
                break;

            }
        }

        System.out.println("Saliendo del programa. Hasta luego!");
    }

    /**
     * Metodo que permite al cliente annadir un libro a la lista del servidor.
     * 
     * @param ref Referencia al servidor
     * @param in  Scanner para leer los datos del libro
     * @throws RemoteException
     */
    private static void introducirLibroEnBase(ILibros ref, Scanner in) throws RemoteException {
        System.out.println("Introduce el titulo del libro:");
        String titulo = in.next();
        System.out.println("Introduce el autor del libro: ");
        String autor = in.next();

        ref.addNewLibro(titulo, autor);
    }

    /**
     * Metodo que permite al cliente eliminar un libro de la base de datos
     * 
     * @param ref Referencia al servidor
     * @param in  Scanner para introducir el indice del libro a eliminar
     * @throws RemoteException
     */
    private static void eliminarLibroDeBase(ILibros ref, Scanner in) throws RemoteException {
        System.out.println("Introduce el indice del libro:");
        int index = in.nextInt();
        if (!ref.removeLibro(index)) {
            System.out.println("Error al eliminar el libro. Indice incorrecto.");
        }
    }

    /**
     * Metodo que permite buscar el indice de un libro dentro de la base de datos
     * 
     * @param ref Referencia al servidor
     * @param in  Scanner para leer el nombre del libro
     * @throws RemoteException
     */
    private static void buscarLibro(ILibros ref, Scanner in) throws RemoteException {
        System.out.println("Introduce el titulo del libro:");
        String titulo = in.next();
        int index = ref.getIndexOfLibroByTitle(titulo);
        if (index != -1) {
            System.out.println("El indice del libro es " + index);
        } else {
            System.out.println("Libro no encontrado en la bibliografia.");
        }
    }

    /**
     * Metodo que permite obtener el contenido del libro almacenado en la base de
     * datos accediendo mediante su indice
     * 
     * @param ref Referencia al servidor
     * @param in  Scanner para leer el indice del libro
     * @return String Devuelve el contenido del libro
     * @throws RemoteException
     */
    private static String leerLibro(ILibros ref, Scanner in) throws RemoteException {
        System.out.println("Introduce el indice del libro: ");
        int index = in.nextInt();
        return ref.getContenido(index);
    }

    /**
     * Metodo que permite obtener el titulo de un libro accediendo a partir de su
     * indice en la base de datos
     * 
     * @param ref Referencia al servidor
     * @param in  Scanner para leer el indice
     * @return String Devuelve el titulo del libro asociado
     * @throws RemoteException
     */
    private static String obtenerTitulo(ILibros ref, Scanner in) throws RemoteException {
        System.out.println("Introduce el indice del libro: ");
        int index = in.nextInt();
        return ref.getTitle(index);
    }

    /**
     * Metodo que permite obtener el autor de un libro a partir de indice en la base
     * de datos del servidor
     * 
     * @param ref Referencia al servidor
     * @param in  Scanner para leer el indice
     * @return String Devuelve el autor del libro
     * @throws RemoteException
     */
    private static String obtenerAutor(ILibros ref, Scanner in) throws RemoteException {
        System.out.println("Introduce el indice del libro: ");
        int index = in.nextInt();
        return ref.getAuthor(index);
    }
}
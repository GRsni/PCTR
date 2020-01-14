import java.rmi.*;
import java.rmi.registry.*;
import java.util.Scanner;

public class CLibros {

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

    private static void introducirLibroEnBase(ILibros ref, Scanner in) throws RemoteException {
        System.out.println("Introduce el titulo del libro:");
        String titulo = in.next();
        System.out.println("Introduce el autor del libro: ");
        String autor = in.next();

        ref.addNewLibro(titulo, autor);
    }

    private static void eliminarLibroDeBase(ILibros ref, Scanner in) throws RemoteException {
        System.out.println("Introduce el indice del libro:");
        int index = in.nextInt();
        if (!ref.removeLibro(index)) {
            System.out.println("Error al eliminar el libro. Indice incorrecto.");
        }
    }

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

    private static String leerLibro(ILibros ref, Scanner in) throws RemoteException {
        System.out.println("Introduce el indice del libro: ");
        int index = in.nextInt();
        return ref.getContenido(index);
    }

    private static String obtenerTitulo(ILibros ref, Scanner in) throws RemoteException {
        System.out.println("Introduce el indice del libro: ");
        int index = in.nextInt();
        return ref.getTitle(index);
    }

    private static String obtenerAutor(ILibros ref, Scanner in) throws RemoteException {
        System.out.println("Introduce el indice del libro: ");
        int index = in.nextInt();
        return ref.getAuthor(index);
    }
}
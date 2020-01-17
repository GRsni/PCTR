import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase SLibros. Modela un servidor bibliografico que almacena una lista de
 * libros, modelados por la clase Libro, y que se conecta con los clientes
 * mediante RMI.
 * 
 * @author Santiago Jesús Mas Peña
 * @version 14/01/20
 */
public class SLibros extends UnicastRemoteObject implements ILibros {
    static ArrayList<Libro> bibliografia = new ArrayList<Libro>();
    static ReentrantLock lock = new ReentrantLock();

    /**
     * Constructor de clase.
     * 
     * @throws RemoteException
     */
    public SLibros() throws RemoteException {
        System.out.println("Servidor bibliografico listo.");
    }

    /**
     * Metodo principal. Crea una instancia de servidor, registrando su referencia
     * bajo el nombre 'bibliografia'.
     * 
     * @param args Parametros de entrada por consola, no se emplean
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ILibros ORemoto = new SLibros();
        Naming.rebind("bibliografia", ORemoto);
    }

    /**
     * Metodo que añade un nuevo libro a la base de datos.
     * 
     * @param title  Titulo del libro a introducir
     * @param author Autor del libro a introducir
     * @throws RemoteException
     */
    public void addNewLibro(String title, String author) throws RemoteException {
        lock.lock();
        bibliografia.add(new Libro("hola " + bibliografia.size(), title, author));
        System.out.println(String.format("Libro incluido en la base de datos.[%d libros]", bibliografia.size()));
        lock.unlock();
    }

    /**
     * Metodo que devuelve el indice de un libro segun su titulo.
     * 
     * @param titulo Titulo del libro a buscar
     * @return int Indice del libro en la base de datos
     * @throws RemoteException
     */
    public int getIndexOfLibroByTitle(String titulo) throws RemoteException {
        lock.lock();
        for (int i = 0; i < bibliografia.size(); i++) {
            Libro lib = bibliografia.get(i);
            if (lib.getTitulo().equals(titulo)) {
                return i;
            }
        }
        lock.unlock();
        return -1;
    }

    /**
     * Metodo que elimina un libro de la base de datos a partir de su indice.
     * 
     * @param index Indice del libro a eliminar
     * @return boolean Devuelve si el libro se ha eliminado correctamente o no
     * @throws RemoteException
     */
    public boolean removeLibro(int index) throws RemoteException {
        boolean resultado = true;
        lock.lock();
        if (SLibros.indiceValido(index)) {
            bibliografia.remove(index);
            System.out.println(String.format("Libro eliminado de la base de datos.[%d libros]", bibliografia.size()));
        } else {
            System.out.println("Indice no existe.");
            resultado = false;
        }
        lock.unlock();
        return resultado;
    }

    /**
     * Metodo que devuelve el contenido de un libro a partir de su indice.
     * 
     * @param index Indice del libro a buscar
     * @return String Contenido del libro. Devuelve una cadena vacia en caso de
     *         error
     * @throws RemoteException
     */
    public String getContenido(int index) throws RemoteException {
        lock.lock();
        String out = "";
        if (indiceValido(index)) {
            out = bibliografia.get(index).getContenido();
        } else {
            System.out.println("Libro no existe.");
        }
        lock.unlock();
        return out;
    }

    /**
     * Metodo que devuelve el titulo de un libro a partir de su indice.
     * 
     * @param index Indice del libro a buscar
     * @return String Devuelve el titulo del libro. Devuelve una cadena vacia en
     *         caso de error
     * @throws RemoteException
     */
    public String getTitle(int index) throws RemoteException {
        lock.lock();
        String out = "";
        if (indiceValido(index)) {
            out = bibliografia.get(index).getTitulo();
        } else {
            System.out.println("Libro no existe.");
        }
        lock.unlock();
        return out;
    }

    /**
     * Metodo que devuelve el autor de un libro a partir de su indice.
     * 
     * @param index Indice del libro a buscar
     * @return String Devuelve el autor del libro. Devuelve una cadena vacia en caso
     *         de error
     * @throws RemoteException
     */
    public String getAuthor(int index) throws RemoteException {
        lock.lock();
        String out = "";
        if (indiceValido(index)) {
            out = bibliografia.get(index).getAutor();
        } else {
            System.out.println("Libro no existe.");
        }
        lock.unlock();
        return out;
    }

    /**
     * Metodo privado que comprueba que el indice introducido es valido.
     * 
     * @param index Indice a comprobar
     * @return boolean Devuelve true si el indice el mayor que 0 y menor que el
     *         tamano de la lista de libros, false en otro caso
     */
    private static boolean indiceValido(int index) {
        return index < bibliografia.size() - 1 && index >= 0;
    }
}
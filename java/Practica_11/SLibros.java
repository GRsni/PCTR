import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class SLibros extends UnicastRemoteObject implements ILibros {
    static ArrayList<Libro> bibliografia = new ArrayList<Libro>();
    static ReentrantLock lock = new ReentrantLock();

    public SLibros() throws RemoteException {
        System.out.println("Servidor bibliografico listo.");
    }

    public static void main(String[] args) throws Exception {
        ILibros ORemoto = new SLibros();
        Naming.rebind("bibliografia", ORemoto);
    }

    public void addNewLibro(String title, String author) throws RemoteException {
        lock.lock();
        bibliografia.add(new Libro("hola " + bibliografia.size(), title, author));
        System.out.println(String.format("Libro incluido en la base de datos.[%d libros]", bibliografia.size()));
        lock.unlock();
    }

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

    private static boolean indiceValido(int index) {
        return index < bibliografia.size() - 1 && index >= 0;
    }
}
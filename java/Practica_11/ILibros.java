import java.rmi.*;

/**
 * Interfaz ILibros. Modela el contrato de clase que cumple el servidor SLibros
 * 
 * @author Santiago Jesús Mas Peña
 * @version 14/01/20
 */
public interface ILibros extends Remote {

    public void addNewLibro(String title, String author) throws RemoteException;

    public int getIndexOfLibroByTitle(String title) throws RemoteException;

    public boolean removeLibro(int index) throws RemoteException;

    public String getContenido(int index) throws RemoteException;

    public String getAuthor(int index) throws RemoteException;

    public String getTitle(int index) throws RemoteException;
}

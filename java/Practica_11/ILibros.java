
import java.rmi.*;

public interface ILibros extends Remote {

    public void addNewLibro(String title, String author) throws RemoteException;

    public int getIndexOfLibroByTitle(String title) throws RemoteException;

    public boolean removeLibro(int index) throws RemoteException;

    public String getContenido(int index) throws RemoteException;

    public String getAuthor(int index) throws RemoteException;

    public String getTitle(int index) throws RemoteException;
}

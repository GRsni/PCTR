import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBingo extends Remote {

    public boolean enviarNumero(int numero) throws RemoteException;
    
}
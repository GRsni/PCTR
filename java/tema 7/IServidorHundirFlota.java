import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServidorHundirFlota extends Remote {
    public int comprobarCelda(int i, int j) throws RemoteException;
}
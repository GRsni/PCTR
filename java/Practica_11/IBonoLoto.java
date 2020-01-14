
import java.rmi.*;

public interface IBonoLoto extends Remote {

  public void resetServidor() throws RemoteException;

  public boolean compApuesta(int[] apuesta) throws RemoteException;

}
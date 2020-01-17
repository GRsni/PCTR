
import java.rmi.*;

/**
 * Interfaz IBonoLoto. Modela el contrato de clase que implementa el servidor
 * SBonoLoto
 * 
 * @author Santiago Jesús Mas Peña
 * @version 14/01/20
 */
public interface IBonoLoto extends Remote {

  public void resetServidor() throws RemoteException;

  public boolean compApuesta(int[] apuesta) throws RemoteException;

}
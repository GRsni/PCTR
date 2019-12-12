import java.rmi.*;
import java.rmi.server.*;

public class ServidorBingo extends UnicastRemoteObject implements IBingo {
    int numeroServidor = 18;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ServidorBingo() throws RemoteException {}

    public static void main(String[] args) throws Exception{
        ServidorBingo servidor=new ServidorBingo();
        Naming.rebind("bingo", servidor);
    }

    public boolean comprobarNumero(int numero) throws RemoteException {
        System.out.println("comprobando el numero " + numero);
        return numeroServidor == numero;
    }
}
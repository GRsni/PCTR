import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.net.*;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

public class SBonoLoto extends UnicastRemoteObject implements IBonoLoto {
    static int[] numeros = new int[6];
    ReentrantLock cerrojo = new ReentrantLock();

    public SBonoLoto() throws RemoteException {
        resetServidor();
    }

    public void resetServidor() throws RemoteException {
        cerrojo.lock();
        System.out.println("Obteniendo nuevos numeros");
        for (int i = 0; i < 6; i++) {
            numeros[i] = (int) (Math.random() * 50);
        }
        System.out.println(Arrays.toString(numeros));
        cerrojo.unlock();
    }

    public boolean compApuesta(int[] apuesta) throws RemoteException {
        cerrojo.lock();
        boolean res = Arrays.equals(numeros, apuesta);
        cerrojo.unlock();
        return res;
    }

    public static void main(String[] args) throws Exception {

        IBonoLoto ORemoto = new SBonoLoto();

        Naming.rebind("bonoloto", ORemoto);

        System.out.println("servidor preparado con valores:");
        System.out.println(Arrays.toString(numeros));

    }
}
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

public class SBonoLoto extends UnicastRemoteObject implements IBonoLoto {
    static int[] numeros = new int[6];
    ReentrantLock cerrojo = new ReentrantLock();

    public SBonoLoto() throws RemoteException {
        resetServidor();
    }

    public static ArrayList<Integer> cargarListaDePosibilidades() {
        ArrayList<Integer> lista = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            lista.add(i);
        }
        return lista;
    }

    public void resetServidor() throws RemoteException {
        cerrojo.lock();
        ArrayList<Integer> lista = cargarListaDePosibilidades();

        System.out.println("Obteniendo nuevos numeros");
        for (int i = 0; i < 6; i++) {
            int index = (int) (Math.random() * lista.size());
            numeros[i] = lista.get(index);
            lista.remove(index);
        }
        Arrays.sort(numeros);
        System.out.println(Arrays.toString(numeros));
        cerrojo.unlock();
    }

    public boolean compApuesta(int[] apuesta) throws RemoteException {
        cerrojo.lock();
        boolean res = Arrays.equals(numeros, apuesta);
        if (res) {
            System.out.println("Han acertado los numeros!");
            resetServidor();
        }
        cerrojo.unlock();
        return res;
    }

    public static void main(String[] args) throws Exception {

        IBonoLoto ORemoto = new SBonoLoto();

        Naming.rebind("bonoloto", ORemoto);

    }
}
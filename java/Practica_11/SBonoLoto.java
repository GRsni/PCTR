import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase SBonoLoto. Modela un servidor de apuestas BonoLoto, que se comunica con
 * los clientes mediante RMI
 * 
 * @author Santiago Jesús Mas Peña
 * @version 13/01/20
 */
public class SBonoLoto extends UnicastRemoteObject implements IBonoLoto {
    static int[] numeros = new int[6];
    ReentrantLock cerrojo = new ReentrantLock();

    /**
     * Contructor de clase, elige aleatoriamente los valores iniciales
     * 
     * @throws RemoteException
     */
    public SBonoLoto() throws RemoteException {
        resetServidor();
    }

    /**
     * Metodo privado que carga la lista de numeros posibles, entre 1 y 49
     * 
     * @return ArrayList<Integer> Lista de numeros que pueden elegirse
     *         aleatoriamente
     */
    private static ArrayList<Integer> cargarListaDePosibilidades() {
        ArrayList<Integer> lista = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            lista.add(i);
        }
        return lista;
    }

    /**
     * Metodo que reinicia el servidor de apuestas, eligiendo 6 valores aleatorios
     * 
     * @throws RemoteException
     */
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

    /**
     * Metodo que comprueba si los 6 valores introducidos por el cliente coinciden
     * con los del servidor
     * 
     * @param apuesta Numeros introducidos por el cliente
     * @return boolean Devuelve si la apuesta es correcta o no
     * @throws RemoteException
     */
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

    /**
     * Metodo principal, crea una instancia de servidor SBonoLoto, que se registra
     * bajo el nombre 'bonoloto'
     * 
     * @param args Parametros de entrada por consola, no se emplean
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        IBonoLoto ORemoto = new SBonoLoto();

        Naming.rebind("bonoloto", ORemoto);

    }
}
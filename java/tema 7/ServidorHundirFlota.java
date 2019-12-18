import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServidorHundirFlota extends UnicastRemoteObject implements IServidorHundirFlota {
    int[][] mapa = new int[10][10];

    public int comprobarCelda(int i, int j) {
        int resultado = 0;
        if (mapa[i][j] != 0)
            resultado = 1;
        return resultado;
    }

    public ServidorHundirFlota() throws RemoteException {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                mapa[i][j] = 0;
            }
        }
        mapa[3][5] = mapa[3][6] = mapa[3][7] = 1;
        mapa[0][2] = mapa[1][2] = mapa[2][2] = mapa[3][2] = 1;
    }

    public static void main(String[] args) throws Exception {
        ServidorHundirFlota servidor = new ServidorHundirFlota();
        Naming.rebind("hundirlaflota", servidor);
        System.out.println("servidor listo.");
    }
}
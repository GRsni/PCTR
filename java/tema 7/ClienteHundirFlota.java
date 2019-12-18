import java.rmi.Naming;
import java.util.Scanner;

public class ClienteHundirFlota {

    public static void main(String[] args) throws Exception {
        char[][] mapa = new char[10][10];
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                mapa[i][j] = ' ';
            }
        }

        IServidorHundirFlota servidor = (IServidorHundirFlota) Naming.lookup("hundirlaflota");
        Scanner se = new Scanner(System.in);
        while (true) {
            System.out.println("escribe las coordenadas en dos lineas: ");
            int i = se.nextInt();
            int j = se.nextInt();
            switch (servidor.comprobarCelda(i, j)) {
            case 0:
                System.out.println("agua.");
                mapa[i][j] = 'A';
                break;
            case 1:
                System.out.println("tocado.");
                mapa[i][j] = 'X';
                break;
            }
            ClienteHundirFlota.imprimirTablero(mapa);
        }
    }

    public static void imprimirTablero(char[][] mapa) {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                System.out.print(mapa[i][j] + "|");
            }
            System.out.println();
        }
    }
}
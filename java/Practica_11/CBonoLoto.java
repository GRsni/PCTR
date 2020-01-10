import java.rmi.*;
import java.rmi.registry.*;
import java.util.Arrays;

public class CBonoLoto {

    public static void main(String[] args) throws Exception {

        IBonoLoto RefORemoto = (IBonoLoto) Naming.lookup("//localhost/bonoloto");

        for (int i = 0; i < 1000; i++) {
            // int[] apuesta = { 49, 37, 25, 41, 5, 17 };
            int[] apuesta = new int[6];
            for (int j = 0; j < 6; j++) {
                apuesta[j] = (int) (Math.random() * 50);
            }
            System.out.println(Arrays.toString(apuesta));
            if (RefORemoto.compApuesta(apuesta)) {
                System.out.println("Apuesta acertada.");
                RefORemoto.resetServidor();
            }
        }
    }
}
import java.rmi.Naming;

public class ClienteBingo {

    public static void main(String[] args) throws Exception {
        IBingo servidor = (IBingo) Naming.lookup(args[0] + "/bingo");
        int num = 0;
        do {
            num = (int) (Math.random() * 100);
            System.out.println("mandando " + num);
            Thread.sleep(2001);
        } while (!servidor.enviarNumero(num));

        System.out.println("numero es " + num);
    }
}
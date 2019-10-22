/**
 * Clase redCajeros
 * 
 * @author Santiago Jesus Mas Peña
 * @version 22/10/19
 */
public class redCajeros {

    public redCajeros() {
    }

    public static void main(String[] args) throws InterruptedException {
        cuentaCorriente cuenta = new cuentaCorriente("pepito", "12345678", 100.0f);
        cajero caj1 = new cajero(cuenta, true);
        cajero caj2 = new cajero(cuenta, false);

        Thread t1 = new Thread(caj1);
        Thread t2 = new Thread(caj2);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("El saldo final es: " + cuenta.getSaldo());
    }

}
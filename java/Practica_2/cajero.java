/**
 * Clase Cajero implementa Interfaz Runnable
 * 
 * @author Santiago Jesus Mas Peña
 * @version 22/10/19
 */
public class cajero implements Runnable {
    cuentaCorriente cuenta;
    boolean ingresa;

    public cajero(cuentaCorriente cuenta, boolean ingresa) {
        this.cuenta = cuenta;
        this.ingresa = ingresa;
    }

    public void run() {
        if (ingresa) {
            for (int i = 0; i < 100000; i++) {
                cuenta.ingresa20();
            }
        } else {
            for (int i = 0; i < 100000; i++) {
                cuenta.saca20();
            }
        }
        // System.out.println("saldo: " + cuenta.getSaldo());
    }

}
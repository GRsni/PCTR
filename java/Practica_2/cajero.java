/**
 * Modela la entidad cajero, que ingresa y saca dinero de una entidad cuentaCorriente
 * 
 * @author Santiago Jesus Mas Peña
 * @version 22/10/19
 */
public class cajero implements Runnable {
    cuentaCorriente cuenta;
    boolean ingresa;

    /**
     * Constructor de la clase cajero
     * @param cuenta Referencia a una instancia de cuentaCorriente
     */
    public cajero(cuentaCorriente cuenta, boolean ingresa) {
        this.cuenta = cuenta;
        this.ingresa = ingresa;
    }

    /**
     * Método concurrente, ingresa o saca dinero de cuenta segun el tipo de cajero que sea
     */
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
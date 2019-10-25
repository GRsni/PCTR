/**
 * Clase cuentaCorriente
 * 
 * @author Santiago Jesus Mas Peña
 * @version 22/10/19
 */
public class cuentaCorriente {
    float saldo;
    String usuario, clave, iban;
    int movimientos, cantidadTarjetas;
    long[] numerosTarjetas;

    /**
     * Constructor de clase cuentaCorriente
     * @param usuario Nombre del propietario de la cuenta
     * @param clave Contraseña de dicho propietario
     * @param saldoInic Saldo inicial con el que se abre la cuenta
     */
    public cuentaCorriente(String usuario, String clave, float saldoInic) {
        this.usuario = usuario;
        this.clave = clave;
        this.iban = generateIban();
        this.saldo = saldoInic;
        this.movimientos = 0;
        this.cantidadTarjetas = 0;
        numerosTarjetas = null;
    }
    
    /**
     * Genera aleatoriamente el IBAN de la cuenta corriente
     * @return iban Cadena aleatoria de 34 caracteres de longitud
     */
    private String generateIban() {
        String iban = "";
        char[] possibleLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (int i = 0; i < 34; i++) {
            if (i < 2) {
                int index = (int) Math.floor(Math.random() * possibleLetters.length);
                iban += possibleLetters[index];
            } else {
                iban += (int) Math.floor(Math.random() * 10);
            }
        }
        System.out.println("Generated iban: " + iban);
        return iban;
    }
    
    /**
     * Método modificador basico de la cuenta corriente
     * @param amount Recibe el importe de la transacción
     */
    public void operacion(float amount) {
        saldo += amount;
        movimientos++;
    }

    /**
     * Ingresa 20 en la cuenta corriente
     */
    public void ingresa20() {
        operacion(20);
    }

    /**
     * Retira 20 de la cuenta corriente
     */
    public void saca20() {
        operacion(-20);
    }

    /**
     * Metodo observador del saldo actual de la cuenta
     * @return Devuelve el saldo actual de la cuenta
     */
    public float getSaldo() {
        return saldo;
    }
}
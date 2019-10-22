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

    public cuentaCorriente(String usuario, String clave, float saldoInic) {
        this.usuario = usuario;
        this.clave = clave;
        this.iban = generateIban();
        this.saldo = saldoInic;
        this.movimientos = 0;
        this.cantidadTarjetas = 0;
        numerosTarjetas = null;
    }

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

    public void operacion(float amount) {
        saldo += amount;
        movimientos++;
    }

    public void ingresa20() {
        operacion(20);
    }

    public void saca20() {
        operacion(-20);
    }

    public float getSaldo() {
        return saldo;
    }
}
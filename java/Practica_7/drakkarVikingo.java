
/**
 * Clase drakkarVikingo, modela un controlador de recursos compartidos segun el
 * problema del productor-consumidor
 * 
 * @author Santiago Jesús Mas Peña
 * @version 06/12/19
 */
public class drakkarVikingo {
    private int marmita;

    /**
     * Constructor de clase
     * 
     * @param marmita Valor inicial del recurso compartido
     */
    public drakkarVikingo(int marmita) {
        this.marmita = marmita;
    }

    /**
     * Protocolo de entrada del consumidor
     */
    public synchronized void comer() {
        while (marmita == 0) {
            notifyAll();
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        marmita--;
    }

    /**
     * Protocolo de entrada del productor
     */
    public synchronized void cocinar() {
        while (marmita > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        marmita = 10;
        notifyAll();
    }
}
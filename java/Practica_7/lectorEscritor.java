/**
 * Clase lectorEscritor. Hace la función de monitor de acceso al recurso
 * compartido, segun la especificación del problema de lectores-escritores
 * 
 * @author Santiago Jesús Mas Peña
 * @version 01/12/19
 */

public class lectorEscritor {
    int lectores;
    boolean escribiendo;

    /**
     * Constructor de clase Inicializa las variables de control
     */
    public lectorEscritor() {
        lectores = 0;
        escribiendo = false;
    }

    /**
     * Protocolo de entrada para los lectores
     * 
     * Comprueba que no se este escribiendo y da acceso al recurso, en caso opuesto
     * bloquea al proceso
     */
    public synchronized void empiezaLeer() {
        while (escribiendo) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        lectores++;
        notifyAll();

    }

    /**
     * Protocolo de salida de los lectores
     * 
     * Actualiza las variables de control y notifica al resto de procesos a la
     * espera
     */
    public synchronized void finLeer() {
        lectores--;
        if (lectores == 0) {
            notifyAll();
        }
    }

    /**
     * Protocolo de entrada de los escritores
     * 
     * Comprueba que no haya ni escritores ni lectores antes de dar acceso, en caso
     * negativo bloquea el proceso
     */
    public synchronized void empiezaEscribir() {
        while (lectores > 0 || escribiendo) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        escribiendo = true;
    }

    /**
     * Protocolo de salida de los lectores
     * 
     * Actualiza las variables de control y notifica al resto de procesos
     */
    public synchronized void finEscribir() {
        escribiendo = false;
        notifyAll();
    }
}
/**
 * Clase monitorImpresion
 * 
 * Modela el monitor de tres impresoras
 * 
 * @author Santiago Jesús Mas Peña
 * @version 12/12/19
 */
public class monitorImpresion {
    private boolean[] impresoras = new boolean[3];
    int libres;

    /**
     * Constructor de clase monitorImpresion
     * 
     * Inicializa el vector de impresoras y el contador de impresoras libres
     */
    public monitorImpresion() {
        for (int i = 0; i < 3; i++) {
            impresoras[i] = false;
        }
        libres = 3;
    }

    /**
     * Protocolo de entrada al monitor. Recorre el vector de impresoras y devuelve
     * el indice si encuentra una libre, si no, bloquea al proceso entrante
     * 
     * @return El indice de la impresora libre
     */
    public synchronized int coger_impresora() {
        while (libres == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        int i = 0;
        while (impresoras[i] && i < 3) {
            i++;
        }
        impresoras[i] = true;
        libres--;
        return i;
    }

    /**
     * Protocolo de salida, libera la impresora utilizada, actualiza el contador de
     * impresoras libres y notifica a todos los procesos bloqueados
     * 
     * @param i El indice de la impresora a liberar
     */
    public synchronized void soltar_impresora(int i) {
        impresoras[i] = false;
        libres++;
        notifyAll();
    }
}
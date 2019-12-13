/**
 * Clase UsamonitorImpresion Modela los hilos que acceden a las impresoras a
 * traves del monitor
 * 
 * @author Santiago Jesús Mas Peña
 * @version 12/12/19
 */

public class UsamonitorImpresion extends Thread {
    monitorImpresion monitor;
    int id;

    /**
     * Constructor de clase, recibe el identificador de proceso y la referencia al
     * monitor
     * 
     * @param id      Identificador del proceso
     * @param monitor Referencia al monitor
     */
    public UsamonitorImpresion(int id, monitorImpresion monitor) {
        this.id = id;
        this.monitor = monitor;
    }

    /**
     * Metodo principal. Crea 10 hilos que se ejecutan concurrentemente accediendo
     * al monitor
     * 
     * @param args Parametros de entrada por consola, no se utilizan en este
     *             programa
     * @throws InterruptedException La llamada al metodo Thread.join() puede lanzar
     *                              excepciones
     */
    public static void main(String[] args) throws InterruptedException {
        monitorImpresion monitor = new monitorImpresion();

        int numHilos = 10;
        UsamonitorImpresion[] hilos = new UsamonitorImpresion[numHilos];

        for (int i = 0; i < numHilos; i++) {
            hilos[i] = new UsamonitorImpresion(i, monitor);
            hilos[i].start();
        }

        for (int i = 0; i < numHilos; i++) {
            hilos[i].join();
        }
    }

    /**
     * Metodo de ejecución concurrente
     * 
     * Accede al monitor, busca una impresora libre, imprime y la libera
     */
    public void run() {
        for (;;) {
            int impresora = monitor.coger_impresora();
            imprimir(impresora);
            monitor.soltar_impresora(impresora);
            try {
                Thread.sleep((int) (Math.random() * 500));
            } catch (InterruptedException e) {
            }
        }

    }

    /**
     * Metodo que imprime por consola, simulando el uso de la impresora
     * 
     * @param i Indice de la impresora asignada
     */
    private void imprimir(int i) {
        System.out.println("Hilo " + id + " imprimiendo en impresora " + (i + 1));
    }

}
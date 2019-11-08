public class Semaforo {
    private int s, bloqueados = 0;

    /**
     * Contructor del semaforo
     * 
     * @param s Tamaño del semaforo
     */
    public Semaforo(int s) {
        this.s = s;
    }

    /**
     * Si el semaforo vale 0, el proceso que llame al metodo wait, se bloquea, en
     * otro caso, decrementa en 1 el contador
     * 
     * @throws InterruptedException
     */
    public synchronized void acquire() throws InterruptedException {
        if (s > 0) {
            s--;
        } else {
            bloqueados++;
            wait();
        }
    }

    /**
     * Si hay procesos bloqueados cuando hay se llama al metodo signal, se notifica
     * a los procesos bloqueados y s actualiza el contador de bloqueados, en otro
     * caso, se incrementa el semaforo
     */
    public synchronized void signal() {
        if (bloqueados > 0) {
            bloqueados--;
            notify();
        } else {
            s++;
        }
    }
}
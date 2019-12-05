public class Semaforo {
    private int S;

    public Semaforo(int valor_inicial) {
        S = valor_inicial;
    }

    public synchronized void esperar() {
        while (S == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        S--;
    }

    public synchronized void senalar() {
        notifyAll();
        S++;
    }

}

public class monitor_impresora {
    private boolean[] impresoras = new boolean[3];
    int libres;

    public monitor_impresora() {
        for (int i = 0; i < 3; i++) {
            impresoras[i] = false;
        }
        libres = 3;
    }

    public synchronized int coger_impresora() {
        while (libres == 0) {
            wait();
        }
        int i = 0;
        while (impresoras[i]) {
            i++;
        }
        impresoras[i] = true;
        return i;
    }

    public synchronized void soltar_impresora(int i) {
        impresoras[i] = false;
        notifyAll();
    }
}
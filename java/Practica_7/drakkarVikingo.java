public class drakkarVikingo {
    private int marmita;

    public drakkarVikingo(int marmita) {
        this.marmita = marmita;
    }

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
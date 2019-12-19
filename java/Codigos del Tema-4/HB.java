import java.util.concurrent.*;

public class HB extends Thread {

    Semaphore sC, sB;

    public void run() {
        for (;;) {
            try {
                sB.acquire();
            } catch (InterruptedException e) {
            }
            System.out.println("B");
            sC.release();
        }
    }

    /**
     * @param sB
     * @param sC
     * @return
     */
    public HB(Semaphore sB, Semaphore sC) {
        this.sC = sC;
        this.sB = sB;
    }
}
import java.util.concurrent.*;

public class HC extends Thread {

    Semaphore sA, sC;

    public void run() {
        for (;;) {
            try {
                sC.acquire();
            } catch (InterruptedException e) {
            }
            System.out.println("C");
            sA.release();
        }
    }

    /**
     * @param sA
     * @param sC
     * @return
     */
    public HC(Semaphore sA, Semaphore sC) {
        this.sA = sA;
        this.sC = sC;
    }
}
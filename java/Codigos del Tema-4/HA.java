import java.util.concurrent.Semaphore;

public class HA extends Thread{
    Semaphore sA, sB;

    public void run() {
        for (;;) {
            try {
                sA.acquire();
            } catch (InterruptedException e) {
            }
            System.out.println("A");
            sB.release();
        }
    }

    public HA(Semaphore sA, Semaphore sB) {
        this.sA = sA;
        this.sB = sB;
    }
}
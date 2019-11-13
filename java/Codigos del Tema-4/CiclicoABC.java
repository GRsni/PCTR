import java.util.concurrent.Semaphore;

public class CiclicoABC {

    public static void main(String[] args) {
        Semaphore A = new Semaphore(1);
        Semaphore B = new Semaphore(0);
        Semaphore C = new Semaphore(0);

        HA ha = new HA(A, B);
        HB hb = new HB(B, C);
        
        HC hc = new HC(A, C);
        ha.start();
        hb.start();
        hc.start();

    }

}
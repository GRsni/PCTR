import java.util.concurrent.locks.*;

public class Correc extends Thread {
    public static int nH = 10;
    public static int n = 0;
    public static ReentrantLock l = new ReentrantLock();
    public Condition v;

    public Correc() {
        v = l.newCondition();
    }

    public void run() {
        l.lock();
        try {
            try {
                v.await();
            } catch (InterruptedException e) {
            }
            n++;
        } finally {
            l.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Correc[] h = new Correc[nH];
        for (int i = 0; i < h.length; i++) {
            h[i] = new Correc();
            System.out.println(h[i].getName());
            h[i].start();
        }
        for (int i = 0; i < h.length; i++) {
            h[i] = new Correc();
            System.out.println(h[i].getName());
            h[i].join();
        }
        n++;
        System.out.print(n);
    }
}
import java.util.concurrent.locks.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class E131401 implements Runnable {
    private static AtomicInteger s = new AtomicInteger();
    private static ReentrantLock l = new ReentrantLock();
    private static int p = 2000;
    private static ArrayList<Condition> c = new ArrayList<Condition>();
    private int n;

    public E131401(int n) {
        this.n = n;
        c.add(n, l.newCondition());
    }

    public void run() {
        int j;
        for (int i = 0; i < 1000; i++)
            ;
        l.lock();
        try {
            j = s.incrementAndGet();
            c.get(0).signalAll();
            try {
                c.get(n).await();
            } catch (InterruptedException e) {
            }
        } finally {
            l.unlock();
        }
    }

    public static void main(String[] args) {
        ExecutorService ept = Executors.newCachedThreadPool();
        for (int i = 0; i < 2000; i++)
            ept.submit(new E131401(i));
        ept.shutdown();
        System.out.print(s);
    }
}
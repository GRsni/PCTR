import java.util.concurrent.*;

public class CyclicBarrierRunnable implements Runnable {
  CyclicBarrier barrier1 = null;

  public CyclicBarrierRunnable(CyclicBarrier barrier1) {
    this.barrier1 = barrier1;
  }

  public void run() {
    try {
      Thread.sleep(1000);
      System.out.println(Thread.currentThread().getName() + " esperando en barrera...");
      this.barrier1.await();
      System.out.println(Thread.currentThread().getName() + " y siguiendo...!");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (BrokenBarrierException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    CyclicBarrier barrier1 = new CyclicBarrier(2);
    new Thread(new CyclicBarrierRunnable(barrier1)).start();
    new Thread(new CyclicBarrierRunnable(barrier1)).start();
  }
}

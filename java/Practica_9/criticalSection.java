import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class criticalSection {
	public static void main(String[] args) {
		long i, n = 1;
		long limite = Integer.parseInt(args[0]);
		long inicTiempo = System.nanoTime();
		Object myObject = new Object();
		for (i = 0; i < limite; i++)
			synchronized (myObject) {
				n++;
			}
		long tiempoTotal = System.nanoTime() - inicTiempo;
		System.out.println("en " + tiempoTotal + " segundos...");

		n = 1;
		Semaphore s = new Semaphore(1);
		inicTiempo = System.nanoTime();
		for (i = 0; i < limite; i++) {
			try {
				s.acquire();
				n++;
			} catch (InterruptedException e) {
			}
			s.release();
		}
		tiempoTotal = System.nanoTime() - inicTiempo;
		System.out.println("en " + tiempoTotal + " segundos...");

		AtomicInteger atomicN = new AtomicInteger(1);
		inicTiempo = System.nanoTime();
		for (i = 0; i < limite; i++) {
			atomicN.addAndGet(1);
		}
		tiempoTotal = System.nanoTime() - inicTiempo;
		System.out.println("en " + tiempoTotal + " segundos...");

		ReentrantLock lock = new ReentrantLock();
		n = 1;
		inicTiempo = System.nanoTime();
		for (i = 0; i < limite; i++) {
			lock.lock();
			n++;
			lock.unlock();
		}
		tiempoTotal = System.nanoTime() - inicTiempo;
		System.out.println("en " + tiempoTotal + " segundos...");
	}
}
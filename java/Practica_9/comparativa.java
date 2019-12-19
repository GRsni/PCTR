import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase comparativa
 * 
 * @author Santiago Jesús Mas Peña
 * @version 19/12/19
 */
public class comparativa {

	/**
	 * Metodo principal, llama a las diferentes funciones de recuento y
	 * temporización
	 * 
	 * @param args Numero de iteraciones a realizar
	 */
	public static void main(String[] args) {
		long limite = Integer.parseInt(args[0]);
		tiempoSynchronized(limite);
		tiempoSemaforo(limite);
		tiempoAtomic(limite);
		tiempoReentrant(limite);
	}

	/**
	 * Metodo que emplea bloques synchronized en el recuento
	 * 
	 * @param limite Numero de iteraciones a realizar
	 */
	public static void tiempoSynchronized(long limite) {
		int n = 1;
		long inicTiempo = System.nanoTime();
		Object myObject = new Object();
		for (int i = 0; i < limite; i++)
			synchronized (myObject) {
				n++;
			}
		long tiempoTotal = System.nanoTime() - inicTiempo;
		// System.out.println("en " + tiempoTotal + " segundos...");
		System.out.println(tiempoTotal);
	}

	/**
	 * Metod que emplea semaforos de clase Semaphore en el recuento
	 * 
	 * @param limite Numero de iteraciones a realizar
	 */
	public static void tiempoSemaforo(long limite) {
		int n = 1;
		Semaphore s = new Semaphore(1);
		long inicTiempo = System.nanoTime();
		for (int i = 0; i < limite; i++) {
			try {
				s.acquire();
				n++;
			} catch (InterruptedException e) {
			}
			s.release();
		}
		long tiempoTotal = System.nanoTime() - inicTiempo;
		// System.out.println("en " + tiempoTotal + " segundos...");
		System.out.println(tiempoTotal);
	}

	/**
	 * Metodo que emplea variables de tipo atomic en el recuento
	 * 
	 * @param limite Numero de iteraciones a realizar
	 */
	public static void tiempoAtomic(long limite) {
		AtomicInteger atomicN = new AtomicInteger(1);
		long inicTiempo = System.nanoTime();
		for (int i = 0; i < limite; i++) {
			atomicN.addAndGet(1);
		}
		long tiempoTotal = System.nanoTime() - inicTiempo;
		// System.out.println("en " + tiempoTotal + " segundos...");
		System.out.println(tiempoTotal);
	}

	/**
	 * Metodo que emplea ReentrantLock para el recuento
	 * 
	 * @param limite Numero de iteraciones a realizar
	 */
	public static void tiempoReentrant(long limite) {
		ReentrantLock lock = new ReentrantLock();
		int n = 1;
		long inicTiempo = System.nanoTime();
		for (int i = 0; i < limite; i++) {
			lock.lock();
			n++;
			lock.unlock();
		}
		long tiempoTotal = System.nanoTime() - inicTiempo;
		// System.out.println("en " + tiempoTotal + " segundos...");
		System.out.println(tiempoTotal);
	}

}
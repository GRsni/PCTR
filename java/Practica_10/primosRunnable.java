import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Clase primosRunnable. Realiza el conteo de primos dado un rango mediante
 * tareas de tipo Runnable
 * 
 * @author Santiago Jesús Mas Peña
 * @version 08/01/20
 */
public class primosRunnable implements Runnable {
    static long rango;
    int id;
    long misPrimos;
    static long primosTotales;

    /**
     * Constructor de clase
     * 
     * @param id Identificador de la tarea
     */
    public primosRunnable(int id) {
        this.id = id;
        misPrimos = 0;
    }

    /**
     * Metodo de ejecucion concurrente, cuenta el numero de primos en el fragmento
     * de rango asignado
     */
    public void run() {
        for (long i = rango / 4 * id + 1; i < rango / 4 * (id + 1); i++) {
            if (esPrimo(i)) {
                misPrimos++;
            }
        }
        addToTotal();
    }

    /**
     * Añade los primos calculados por cada tarea al total de primos
     */
    public synchronized void addToTotal() {
        primosTotales += misPrimos;
    }

    /**
     * Función que calcula si un numero es primo o no
     * 
     * @param n Numero a comprobar
     * @return boolean Devuelve si el numero es primo o no
     */
    private boolean esPrimo(long n) {
        boolean esPrimo = true;
        for (int i = 2; i <= Math.sqrt(n) && esPrimo; i++) {
            if (n % i == 0) {
                esPrimo = false;
            }
        }
        return esPrimo;
    }

    /**
     * Metodo principal. Crea 4 tareas Runnable, que calculan el numero de primos en
     * su rango, y almacenan el resultado en una variable comun
     * 
     * @param args Recibe por consola el rango de numeros a calcular
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        long max = Long.parseLong(args[0]);
        int numHilos = 4;
        rango = max / numHilos;

        ExecutorService ejecutor = Executors.newFixedThreadPool(100);

        long initTime = System.nanoTime();
        for (int i = 0; i < numHilos; i++) {
            ejecutor.execute(new primosRunnable(i));
        }

        ejecutor.shutdown();
        ejecutor.awaitTermination(20, TimeUnit.DAYS);
        System.out.println(
                "Acabado en " + (System.nanoTime() - initTime) / 1.0e9 + ", encontrado " + (primosTotales - 1));
    }
}
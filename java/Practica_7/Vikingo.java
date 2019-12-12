
/**
 * Clase Vikingo, modela los procesos concurrentes que acceden al recurso
 * compartido
 * 
 * @author Santiago Jesús Mas Peña
 * @version 06/12/19
 */
public class Vikingo extends Thread {
    drakkarVikingo marmita;
    int tipo, id;

    /**
     * Constructor de clase
     * 
     * @param m  Referencia al controlador de recurso compartido
     * @param id Identificador del proceso
     */
    public Vikingo(drakkarVikingo m, int id) {
        marmita = m;
        this.tipo = (id > 1 ? 1 : 2);
        this.id = id;
    }

    /**
     * Metodo concurrente
     */
    public void run() {
        switch (tipo) {
        case 1:
            while (true) {
                System.out.println("Vikingo " + id + " ha comido.");
                marmita.comer();
            }
        case 2:
            while (true) {
                System.out.println("Cocinero " + id + " ha rellenado la marmita.");
                marmita.cocinar();
            }
        }

    }

    /**
     * Metodo principal
     * 
     * Crea una instancia del monitor de recurso compartido, que se pasa como
     * parametro a las instancias de proceso concurrente de clase Vikingo
     * 
     * @param args Argumentos de entrada por consola
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        drakkarVikingo m = new drakkarVikingo(20);
        Vikingo[] aldea = new Vikingo[10];
        for (int i = 0; i < 10; i++) {
            aldea[i] = new Vikingo(m, i);
            aldea[i].start();
        }

        for (int i = 0; i < 10; i++) {
            aldea[i].join();
        }

    }

}
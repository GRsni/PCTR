
/**
 * Clase usaConductores
 * 
 * @author Santiago Jesús Mas Peña
 * @version 05/12/19
 */
public class usaConductores extends Thread {
    Conductores lista;
    int id;

    /**
     * Constructor de clase usaConductores
     * 
     * @param lista Instancia compartida de la interfaz de la base de datos
     * @param id    Identificador de proceso concurrente
     * @return
     */
    public usaConductores(Conductores lista, int id) {
        this.lista = lista;
        this.id = id;
    }

    /**
     * Metodo principal Crea una instancia de la base de datos, que es compartida
     * por todos los procesos concurrentes
     * 
     * @param args No se emplean los parametros de paso por consola
     */
    public static void main(String[] args) {
        Conductores lista = new Conductores();
        for (int i = 0; i < 100; i++) {

            String nombre = "a" + Integer.toString(i);
            String DNI = Integer.toString((int) (Math.random() * 1000000));
            String matricula = Integer.toString((int) (Math.random() * 10000));
            int tipoPermiso = (int) (Math.random() * 5);

            Conductor c = new Conductor(nombre, DNI, matricula, tipoPermiso);
            lista.addConductor(c);
        }
        int numHilos = Runtime.getRuntime().availableProcessors();
        usaConductores[] hilos = new usaConductores[numHilos];
        for (int i = 0; i < numHilos; i++) {
            hilos[i] = new usaConductores(lista, i);
            hilos[i].start();
        }

        for (int i = 0; i < numHilos; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * Metodo concurrente
     * 
     * Los procesos obtienen los datos de un conductor de la base de datos e
     * imprimen sus datos por pantalla
     */
    public void run() {
        while (true) {
            int index = (int) (Math.random() * lista.getNumConductores());
            System.out.println("Hilo " + id + " : Conductor :" + lista.getNombreDeConductor(index) + " con DNI "
                    + lista.getDNIDeConductor(index));
            try {
                Thread.sleep(50);
            } catch (Exception e) {
            }
        }

    }
}
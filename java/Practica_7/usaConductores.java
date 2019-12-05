public class usaConductores extends Thread {
    Conductores lista;
    int id;

    public usaConductores(Conductores lista, int id) {
        this.lista = lista;
        this.id = id;
    }

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

public class mutex extends Thread {
    Semaforo em;
    int id;
    static int n = 0;

    public mutex(int id, Semaforo em) {
        this.id = id;
        this.em = em;
    }

    public static void main(String[] args) throws InterruptedException {
        Semaforo em = new Semaforo(1);
        Thread hilo1 = new mutex(0, em);
        Thread hilo2 = new mutex(1, em);

        hilo1.start();
        hilo2.start();
        hilo1.join();
        hilo2.join();
        System.out.println("El valor final del contador es " + n);

    }

    public void run() {
        for (int i = 0; i < 100000; i++) {
            em.esperar();
            // System.out.println("Hilo " + id + " entrando.");
            if (id == 0) {
                n++;
            } else {
                n--;
            }
            em.senalar();
        }
    }
}
public class usaSemaforo extends Thread {
    private static Semaforo em;
    private static int n = 0;

    public usaSemaforo() {
    }

    public static void main(String[] args) throws InterruptedException {
        em = new Semaforo(1);
        usaSemaforo usa1 = new usaSemaforo();
        usaSemaforo usa2 = new usaSemaforo();
        usa1.start();
        usa2.start();
        usa1.join();
        usa2.join();

        System.out.println(n);
    }

    public void run() {
        for (int i = 0; i < 100000; i++) {
                em.esperar();
            n++;
            em.senalar();
        }
    }

}
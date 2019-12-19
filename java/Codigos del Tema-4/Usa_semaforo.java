public class Usa_semaforo extends Thread {
    static int contador = 0;
    static Semaforo em = new Semaforo(1);

    public void run() {
        for (int i = 0; i < 1000000; i++) {
            try {
                em.acquire();
            } catch (InterruptedException e) {
            }
            contador++;
            em.signal();
        }
    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Usa_semaforo h1 = new Usa_semaforo(), h2 = new Usa_semaforo();
        h1.start();
        h2.start();
        h1.join();
        h2.join();
        System.out.println(contador);
    }
}
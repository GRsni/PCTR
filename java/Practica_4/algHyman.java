public class algHyman {
    static int procesoEjecutando = 0;
    static boolean[] in = new boolean[2];
    static int turno = 0;
    static int iter = 200000;
    static int n = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable task0 = () -> {
            for (int i = 0; i < iter; i++) {
                in[0] = true;
                while (turno != 0) {
                    while (in[1]) {
                        Thread.yield();
                    }
                    turno = 0;
                }
                n++;
                in[0] = false;
            }
        };
        Runnable task1 = () -> {
            for (int i = 0; i < iter; i++) {
                in[1] = true;
                while (turno != 1) {
                    while (in[0]) {
                        Thread.yield();
                    }
                    turno = 1;
                }
                n--;
                in[1] = false;
            }
        };

        Thread t1 = new Thread(task0);
        t1.start();
        Thread t2 = new Thread(task1);
        t2.start();

        t1.join();
        t2.join();
        
        System.out.println(n);
    }
}
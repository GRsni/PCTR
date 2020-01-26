import java.util.concurrent.*;

public class E17 extends Thread {
    static int x = 0;
    Semaphore cerrojo = new Semaphore(1);
    int t;

    public E17(int t) {
        this.t = t;
    }

    public void run() {
        switch (t) {
        case 0: {
            try {
                cerrojo.acquire();
            } catch (InterruptedException e) {
            }
            x++;
            cerrojo.release();
        }
        case 1: {
            try {
                cerrojo.acquire();
            } catch (InterruptedException e) {
            }
            x--;
            cerrojo.release();
        }
        }
    }

    public static void main(String[] args) throws Exception {
        E17[] lista = new E17[1000000];
        for (int i = 0; i < lista.length; i++) {
            lista[i] = new E17(1);
            lista[i].start();
        }
        for (int i = 0; i < lista.length; i++) {
            lista[i].join();
        }
        System.out.print(x);
    }
}
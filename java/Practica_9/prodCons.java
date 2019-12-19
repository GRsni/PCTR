import java.util.concurrent.Semaphore;

public class prodCons {
    long[] buffer;
    Semaphore em, capacidad;
    int puntIn, puntOut;

    /**
     * @param tam
     * @return
     */
    public prodCons(int tam) {
        em = new Semaphore(1);
        capacidad = new Semaphore(tam);
        buffer = new long[tam];
        for (int i = 0; i < tam; i++) {
            buffer[i] = 0;
        }
        puntIn = 0;
        puntOut = 0;
    }

    /**
     * @param elem
     * @throws InterruptedException
     */
    public void insertar(long elem) throws InterruptedException {
        em.acquire();
        buffer[puntIn] = elem;
        puntIn = (puntIn + 1) % buffer.length;
        em.release();
        capacidad.release();
    }

    /**
     * @return long
     * @throws InterruptedException
     */
    public long extraer() throws InterruptedException {
        capacidad.acquire();
        em.release();
        long elem = buffer[puntOut];
        puntOut = (puntOut + 1) % buffer.length;
        em.acquire();
        return elem;
    }
}
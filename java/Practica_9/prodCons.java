import java.util.concurrent.Semaphore;

/**
 * Clase prodCons. Modela un monitor de recursos compartidos para el problema de
 * productor-consumidor mediante la clase Semaphore
 * 
 * @author Santiago Jesús Mas Peña
 * @version 19/12/19
 */
public class prodCons {
    long[] buffer;
    Semaphore em, capacidad;
    int puntIn, puntOut;

    /**
     * Constructor de clase, inicializa el buffer y los semaforos
     * 
     * @param tam Tamaño maximo del buffer
     * @return Instancia de la clase prodCons
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
     * Metodo de inserción en el buffer. Si esta lleno, se bloquea el proceso hasta
     * que haya espacio
     * 
     * @param elem Elemento a insertar
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
     * Metodo de extraccion del buffer. Si esta vacio, se debe bloquear al proceso
     * hasta que haya elementos que extraer
     * 
     * @return long Elemento extraido del buffer
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
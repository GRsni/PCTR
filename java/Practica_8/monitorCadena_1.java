
/**
 * Clase monitorCadena_1 Modela el monitor de recursos compartidos del ejercicio
 * 2, encapsulando el buffer de matrices dentro de metodos de acceso
 * sincronizados
 * 
 * @author Santiago Jesus Mas Peña
 * @version 13/12/19
 */
public class monitorCadena_1 {
    private int[][][] buffer;
    private final int MAX_BUFFER_SIZE;
    private int contador = 0;
    private int punteroInsercion = 0;
    private int punteroExtraccion = 0;

    /**
     * Constructor de clase, recibe el tamaño del buffer compartido y el tamaño de
     * las matrices que se van a almacenar
     * 
     * @param tam        Tamaño maximo del buffer
     * @param matrixSize Tamaño de la matriz a almacenar
     */
    public monitorCadena_1(int tam, int matrixSize) {
        buffer = new int[tam][matrixSize][matrixSize];
        MAX_BUFFER_SIZE = tam;
    }

    /**
     * Metodo de inserción de matrices en el buffer, si el buffer esta lleno,
     * bloquea el proceso, en otro caso, añade la matriz, incrementa el contador de
     * matrices y avanza el puntero de inserción. Finalmente notifica a todos los
     * procesos bloqueados.
     * 
     * @param matrix La matriz a insertar en el buffer
     */
    public synchronized void addMatrix(int[][] matrix) {
        while (contador == MAX_BUFFER_SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        buffer[punteroInsercion] = matrix;
        contador++;
        punteroInsercion = (punteroInsercion + 1) % MAX_BUFFER_SIZE;
        notifyAll();
    }

    /**
     * Metodo de extraccion de matrices del buffer. si el buffer esta vacio, bloquea
     * el proceso, en otro caso, extrae la matriz apuntada por el puntero de
     * extraccion, decrementa el contador de matrices y avanza el puntero de
     * inserción. Finalmente notifica a todos los procesos bloqueados.
     * 
     * @return La matriz extraida del buffer
     */
    public synchronized int[][] getMatrix() {
        while (contador == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        int[][] matrix = buffer[punteroExtraccion];
        punteroExtraccion = (punteroExtraccion + 1) % MAX_BUFFER_SIZE;
        contador--;
        notifyAll();
        return matrix;
    }
}

/**
 * Clase UsamonitorCadena Modela los tres procesos que se han de ejecutar de
 * manera concurrente
 * 
 * @author Santiago Jesus Mas Peña
 * @version 13/12/19
 */
public class UsamonitorCadena extends Thread {
    monitorCadena_1 buffer1, buffer2;
    int tipoProceso;
    static int tamMatrix;

    /**
     * Constructor de clase UsamonitorCadena
     * 
     * @param tipo    Tipo de proceso que modela, 1, 2 o 3 segun sea A, B o C
     * @param buffer1 Referencia al monitor del primer buffer de matrices
     * @param buffer2 Referencia al monitor del segundo buffer de matrices
     */
    public UsamonitorCadena(int tipo, monitorCadena_1 buffer1, monitorCadena_1 buffer2) {
        this.tipoProceso = tipo;
        this.buffer1 = buffer1;
        this.buffer2 = buffer2;
    }

    /**
     * Metodo principal, instancia dos monitores de recursos, de tamaño 50 y 100, y
     * crea tambien los tres hilos de ejecución que modelan los tres procesos
     * diferentes.
     * 
     * @param args Recibe por consola el tamaño de la matriz a crear
     */
    public static void main(String[] args) {
        tamMatrix = Integer.parseInt(args[0]);
        monitorCadena_1 buffer1 = new monitorCadena_1(100, tamMatrix), buffer2 = new monitorCadena_1(50, tamMatrix);
        UsamonitorCadena[] hilos = new UsamonitorCadena[3];

        for (int i = 0; i < 3; i++) {
            hilos[i] = new UsamonitorCadena(i + 1, buffer1, buffer2);
            hilos[i].start();
        }

        for (int i = 0; i < 3; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * Metodo de ejecución concurrente, llama a los metodos de cada proceso seugn el
     * tipo que sean
     */
    public void run() {
        while (true) {
            switch (tipoProceso) {
            case 1:
                buffer1.addMatrix(createMatrix());
                break;
            case 2:
                buffer2.addMatrix(transposeMatrix(buffer1.getMatrix()));
                break;
            case 3:
                calculateProduct(buffer2.getMatrix());
                break;
            }
        }
    }

    /**
     * Metodo del proceso A, crea una matriz cuadrada de enteros de tamaño tamMatrix
     * con valores aleatorios
     * 
     * @return Devuelve la matriz generada
     */
    private int[][] createMatrix() {
        System.out.println("Generando matriz aleatoria.");
        int[][] matrix = new int[tamMatrix][tamMatrix];
        for (int i = 0; i < tamMatrix; i++) {
            for (int j = 0; j < tamMatrix; j++) {
                matrix[i][j] = (int) (Math.random() * 100);
            }
        }
        return matrix;
    }

    /**
     * Metodo del proceso B, recibe una matriz por parametro, y devuelve la matriz
     * transpuesta
     * 
     * @param matrix Matriz de entrada
     * @return Devuelve la matriz de enteros transpuesta
     */
    private int[][] transposeMatrix(int[][] matrix) {
        System.out.println("Transponiendo matrix.");
        int[][] transposed = new int[tamMatrix][tamMatrix];
        for (int i = 0; i < tamMatrix; i++) {
            for (int j = 0; j < tamMatrix; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }

    /**
     * Metodo del proceso C, calcula el producto de la matriz diagonal principal
     * 
     * @param matrix Matriz a calcular
     */
    private void calculateProduct(int[][] matrix) {
        System.out.println("Calculando producto de diagonal principal.");
        long prod = 1;
        for (int i = 0; i < tamMatrix; i++) {
            prod *= matrix[i][i];
        }
        System.out.println("El producto de la diagonal principal es " + prod);
    }

    /**
     * Metodo observador, imprime por consola la matriz pasada por parametro
     * 
     * @param matrix La matriz a imprimir
     */
    public void imprimeMatriz(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
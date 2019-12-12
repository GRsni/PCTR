
/**
 * Clase usaLectorEscritor
 * 
 * @author Santiago Jesús Mas Peña
 * @version 01/12/19
 */
public class usaLectorEscritor extends Thread {
    lectorEscritor controlador;
    static String contenido;
    int tipo;

    /**
     * Constructor de clase usaLectorEscritor
     * 
     * @param controlador Referencia al controlador de acceso modelado en la clase
     *                    lectorEscritor
     * @param tipo        Indica si el proceso es de tipo lector o escritor
     */
    public usaLectorEscritor(lectorEscritor controlador, int tipo) {
        this.controlador = controlador;
        this.tipo = tipo;
    }

    /**
     * Metodo concurrente
     * 
     * Imprime por pantalla o escribe en la variable compartida segun el proceso sea
     * de tipo lector o escritor
     */
    public void run() {
        switch (tipo) {
        case 0:
            while (true) {
                controlador.empiezaLeer();
                System.out.println("Lector lee: " + contenido);
                controlador.finLeer();
            }
        case 1:
            while (true) {
                controlador.empiezaEscribir();
                contenido = Long.toString(System.currentTimeMillis());
                controlador.finEscribir();
            }
        }

    }

    /**
     * Metodo principal
     * 
     * Crea una instancia del controlador lectorEscritor, que comparten todas las
     * instancias concurrentes de clase usaLectorEscritor
     * 
     * @param args No se emplean los argumentos de paso por consola
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        lectorEscritor controlador = new lectorEscritor();
        usaLectorEscritor[] entidades = new usaLectorEscritor[10];
        for (int i = 0; i < 10; i++) {
            int tipo = i < 5 ? 0 : 1;
            entidades[i] = new usaLectorEscritor(controlador, tipo);
            entidades[i].start();
        }

        for (int i = 0; i < 10; i++) {
            entidades[i].join();
        }
    }

}
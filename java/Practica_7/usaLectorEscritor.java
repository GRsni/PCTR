public class usaLectorEscritor extends Thread {
    lectorEscritor controlador;
    static String contenido;
    int tipo;

    public usaLectorEscritor(lectorEscritor controlador, int tipo) {
        this.controlador = controlador;
        this.tipo = tipo;
    }

    public void run() {
        switch (tipo) {
        case 0:
            while (true) {
                controlador.empiezaLeer();
                System.out.println(contenido);
                controlador.finLeer();
            }
        case 1:
            while (true) {

            }
        }

    }

    public static void main(String[] args) {

    }

}
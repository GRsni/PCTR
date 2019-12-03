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
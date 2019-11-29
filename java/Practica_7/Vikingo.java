public class Vikingo extends Thread {
    drakkarVikingo marmita;
    int tipo;
    int id;

    public Vikingo(drakkarVikingo m, int id) {
        marmita = m;
        this.tipo = (id > 1 ? 0 : 1);
        this.id = id;
    }

    public void run() {
        switch (tipo) {
        case 1:
            while (true) {
                System.out.println("Vikingo " + id + " ha comido.");
                marmita.comer();
            }
        case 2:
            while (true) {
                System.out.println("Cocinero " + id + " ha rellenado la marmita.");
                marmita.cocinar();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        drakkarVikingo m = new drakkarVikingo(20);
        Vikingo[] aldea = new Vikingo[10];
        for (int i = 0; i < 10; i++) {
            aldea[i] = new Vikingo(m, i);
            aldea[i].start();
        }

        for (int i = 0; i < 10; i++) {
            aldea[i].join();
        }

    }

}
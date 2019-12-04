public class cuentaCorrienteSegura {
    int saldo = 0;

    public cuentaCorrienteSegura(int saldo) {
        this.saldo = saldo;
    }

    public synchronized int consultarSaldo() {
        return saldo;
    }

    public synchronized void ingresar() {
        saldo += 20;
    }

    public synchronized void sacar() {
        saldo -= 20;
    }

    public static void main(String[] args) throws InterruptedException {
        cuentaCorrienteSegura cuenta = new cuentaCorrienteSegura(100);
        int iteraciones = 1000000;
        System.out.println("El saldo inicial es de: " + cuenta.consultarSaldo());
        Runnable tareaIngresa = () -> {
            for (int i = 0; i < iteraciones; i++) {
                cuenta.ingresar();
            }
        };

        Runnable tareaSaca = () -> {
            for (int i = 0; i < iteraciones; i++) {
                cuenta.sacar();
            }
        };

        Thread t1 = new Thread(tareaIngresa);
        Thread t2 = new Thread(tareaSaca);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("El saldo final es de: " + cuenta.consultarSaldo() + " con " + iteraciones + " iteraciones.");
    }

}

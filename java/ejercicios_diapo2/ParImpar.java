public class ParImpar extends Thread {
    final private int cant, id;

    public ParImpar(int cant, int id) {
        this.cant = cant;
        this.id = id;
    }

    public void run() {
        for (int i = 0; i < cant; i++) {
            System.out.println(id + " : " + i + " es " + (cant % 2 == 0 ? "par" : "impar"));
        }

    }
}
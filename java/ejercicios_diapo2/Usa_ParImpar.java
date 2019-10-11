public class Usa_ParImpar {
    public Usa_ParImpar() {
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            new ParImpar((int)(Math.random()*10), i).start();
        }
    }

}
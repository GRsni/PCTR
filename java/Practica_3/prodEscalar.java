public class prodEscalar {

    public static void main(String[] args) {
        int tamV = 100;
        int[] v1 = new int[tamV], v2 = new int[tamV];

        for (int i = 0; i < tamV; i++) {
            v1[i] = 1;
            v2[i] = 1;
        }
        long timeStart = System.currentTimeMillis();
        System.out.println("El producto escalar de v1 y v2 es: " + prodEscalar.productoEsc(v1, v2));
        System.out.println("Tiempo: " + (System.currentTimeMillis() - timeStart) / 1000.0);
    }

    public static int productoEsc(int[] v1, int[] v2) {
        int sum = 0;
        for (int i = 0; i < v1.length; i++) {
            sum += v1[i] * v2[i];
        }
        return sum;
    }

}
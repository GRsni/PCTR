import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ficheroSeguro extends Thread {
    static RandomAccessFile archivo;
    static int iter = 10;

    public void run() {
        int num = (int) (Math.random() * 10);

        for (int i = 0; i < iter; i++) {
            synchronized (archivo) {
                try {
                    archivo.writeInt(num);
                    System.out.println(num);
                } catch (Exception e) {
                    System.err.println("Excepcion de tipo" + e.toString());
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            archivo = new RandomAccessFile("archivoRandom.bin", "rw");
        } catch (FileNotFoundException e) {
        }
        ficheroSeguro[] f = new ficheroSeguro[100];
        for (int i = 0; i < 100; i++) {
            f[i] = new ficheroSeguro();
            f[i].start();
        }
        for (int i = 0; i < 100; i++) {
            try {
                f[i].join();
            } catch (InterruptedException e) {
            }
        }
        try {
            System.out.println(archivo.length());
            archivo.seek(0);
            while (archivo.getFilePointer() < archivo.length()) {
                System.out.println("pointer: " + archivo.getFilePointer() + " : " + archivo.readInt());
            }
        } catch (IOException e) {
        }

    }

}
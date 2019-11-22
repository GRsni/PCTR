
// import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.Random;

public class randomDataFile {

    static Scanner sc = new Scanner(System.in);
    static RandomAccessFile fichero = null;
    static Random gcl = new Random();

    public static void main(String[] args) {
        try {
            fichero = new RandomAccessFile("enteros.dat", "rw");
            for (int i = 1; i <= 10; i++)
                fichero.writeInt(gcl.nextInt());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
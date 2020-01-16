import java.rmi.*;
import java.rmi.server.*;
import java.util.concurrent.atomic.AtomicLong;
import java.rmi.registry.*;
import java.net.*;

/**
 * Clase SPiMonteCarlo. Modela un proceso servidor que permite la ejecución
 * remota de métodos mediante la API java.rmi Implementa la interfaz
 * iPiMonteCarlo.
 * 
 * @author Santiago Jesús Mas Peña
 * @version 16/01/20
 */
public class SPiMonteCarlo extends UnicastRemoteObject implements iPiMonteCarlo {
    AtomicLong puntosTotales = new AtomicLong(), puntosValidos = new AtomicLong();

    public SPiMonteCarlo() throws RemoteException {
        System.out.println("Servidor de Monte Carlo listo.");
    }

    /**
     * Metodo principal. Crea una instancia de clase SPiMonteCarlo, registrando su
     * referencia bajo el nombre 'montecarlo'
     * 
     * @param args Parametros de consola, no se utilizan
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        iPiMonteCarlo ORemoto = new SPiMonteCarlo();

        Naming.rebind("montecarlo", ORemoto);
    }

    /**
     * Implementación de metodo de interfaz iPiMonteCarlo, devuelve puntosTotales y
     * puntosValidos a 0
     * 
     * @throws RemoteException
     */
    public void reset() throws RemoteException {
        puntosTotales.getAndSet(0);
        puntosValidos.getAndSet(0);
        System.out.println("Calculo reiniciado.");
    }

    /**
     * Implementación de metodo de interfaz iPiMonteCarlo, recibe el numero de
     * puntos a calcular, que añade al total existente. Finalmente, calcula la nueva
     * aproximacion de PI
     * 
     * @param nPuntos Numero de puntos a calcular
     * @throws RemoteException
     */
    public void masPuntos(int nPuntos) throws RemoteException {
        System.out.println("Recibidos " + nPuntos + " nuevos. Calculando aproximacion.");
        puntosTotales.getAndAdd(nPuntos);
        for (int i = 0; i < nPuntos; i++) {
            if (puntoValido()) {
                puntosValidos.getAndIncrement();
            }
        }
        System.out.println("El valor aproximado de Pi es " + calcularPi() + "\n");
    }

    /**
     * Metodo privado que realiza el metodo de Monte Carlo, generando puntos
     * aleatorios
     * 
     * @return boolean Devuelve si el punto generado es valido
     */
    private boolean puntoValido() {
        double x = Math.random();
        double y = Math.random();
        return (x * x + y * y) < 1;
    }

    /**
     * Metodo privado que realiza el calculo aproximado de la constante PI
     * 
     * @return double Devuelve el valor aproximado calculado
     */
    private double calcularPi() {
        return (double) (puntosValidos.get() / (double) puntosTotales.get()) * 4;
    }

}
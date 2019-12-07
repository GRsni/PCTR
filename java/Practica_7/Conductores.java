import java.util.ArrayList;
import java.util.Date;

/**
 * Clase Conductores, sirve de interfaz para la lista de objetos de clase
 * Conductor
 * 
 * @author Santiago Jesús Mas Peña
 * @version 06/12/19
 */
public class Conductores {
    ArrayList<Conductor> conductores = new ArrayList<>();

    /**
     * Constructor de clase por defecto
     * 
     * @return
     */
    public Conductores() {
    }

    /**
     * Metodo observador
     * 
     * @param c Objeto de clase Condutor a buscar
     * @return int Indice del Conductor en la base de datos
     */
    public synchronized int getIndexConductor(Conductor c) {
        return conductores.indexOf(c);
    }

    /**
     * Metodo observador
     * 
     * @param index
     * @return Conductor
     */
    public Conductor getConductor(int index) {
        return conductores.get(index);
    }

    /**
     * Metodo modificador, añade un nuevo Conductor a la lista
     * 
     * @param c Objeto de clase Conductor
     */
    public synchronized void addConductor(Conductor c) {
        conductores.add(c);
    }

    /**
     * Metodo modificador, elimina un Conductor de la lista
     * 
     * @param index Indice del Conductor a eliminar
     */
    public synchronized void removeConductor(int index) {
        conductores.remove(index);
    }

    /**
     * Metodo observador
     * 
     * @return int Devuelve el numero de Conductores almacenados
     */
    public synchronized int getNumConductores() {
        return conductores.size();
    }

    /**
     * Metodo observador
     * 
     * @param index Indice del Conductor
     * @return String Devuelve el nombre del Conductor
     */
    public String getNombreDeConductor(int index) {
        return conductores.get(index).getNombre();
    }

    /**
     * Metodo observador
     * 
     * @param index Indice del Conductor
     * @return String Devuelve el DNI del Conductor
     */
    public String getDNIDeConductor(int index) {
        return conductores.get(index).getDNI();
    }

    /**
     * Metodo observador
     * 
     * @param index Indice del Conductor
     * @return String Devuelve la matricula asociada al Conductor
     */
    public String getMatriculaDeConductor(int index) {
        return conductores.get(index).getMatricula();
    }

    /**
     * Metodo modificador
     * 
     * @param index     Indice del Conductor
     * @param matricula Modifica la matricula asociada al Conductor
     */
    public void setMatricula(int index, String matricula) {
        conductores.get(index).setMatricula(matricula);
    }

    /**
     * Metodo observador
     * 
     * @param index Indice del Conductor
     * @return Date Devuelve la fecha de matriculación del Conductor
     */
    public Date getFechaMatriculacionDeConductor(int index) {
        return conductores.get(index).getFechaMatriculacion();
    }

    /**
     * Metodo observador
     * 
     * @param index Indice del Conductor
     * @return int Devuelve el tipo de permiso del Conductor
     */
    public int getTipoPermisoDeConductor(int index) {
        return conductores.get(index).getTipoPermiso();
    }

    /**
     * Metodo modificador, modifica el tipo de permiso del Conductor
     * 
     * @param index       Indice del Conductor
     * @param tipoPermiso Nuevo permiso del Conductor
     */
    public void setTipoPermisoDeConductor(int index, int tipoPermiso) {
        conductores.get(index).setTipoPermiso(tipoPermiso);
    }

    /**
     * Metodo observador
     * 
     * @param index Indice del Conductor
     * @return int Devuelve los puntos del Conductor
     */
    public int getPuntosDeConductor(int index) {
        return conductores.get(index).getPuntos();
    }

    /**
     * Metodo modificador, incrementa los puntos del Conductor
     * 
     * @param index  Indice del Conductor
     * @param puntos Puntos a incrementar
     */
    public void sumarPuntosAConductor(int index, int puntos) {
        conductores.get(index).setPuntos(puntos);
    }

    /**
     * Metodo modificador, decrementa los puntos del Conductor
     * 
     * @param index  Indice del Conductor
     * @param puntos Puntos a decrementar
     */
    public void restarPuntosAConductor(int index, int puntos) {
        conductores.get(index).setPuntos(puntos * -1);
    }

}
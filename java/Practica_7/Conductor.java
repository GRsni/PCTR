import java.util.Date;

/**
 * Clase Conductor
 * 
 * Modela una entidad almacenada en la base de datos
 * 
 * @author Santiago Jesús Mas Peña
 * @version 06/12/19
 */
public class Conductor {
    private String nombre, DNI, matricula;
    private Date fechaMatriculacion;
    private int tipoPermiso, puntos;

    /**
     * Constructor de clase
     * 
     * @param nombre      Nombre del conductor
     * @param DNI         DNI del conductor
     * @param matricula   Matricula asociada al conductor
     * @param tipoPermiso Permiso de conducir que posee
     */
    public Conductor(String nombre, String DNI, String matricula, int tipoPermiso) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.matricula = matricula;
        this.tipoPermiso = tipoPermiso;
        this.puntos = 10;
        fechaMatriculacion = new Date(System.currentTimeMillis());
    }

    /**
     * @return String Devuelve el nombre del conductor
     */
    public synchronized String getNombre() {
        return nombre;
    }

    /**
     * @param nombre Modifica el nombre del conductor
     */
    public synchronized void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return String Devuelve el DNI del conductor
     */
    public synchronized String getDNI() {
        return DNI;
    }

    /**
     * @param dNI Modifica el DNI del conductor
     */
    public synchronized void setDNI(String dNI) {
        DNI = dNI;
    }

    /**
     * @return String Devuelve la matricula asociada al conductor
     */
    public synchronized String getMatricula() {
        return matricula;
    }

    /**
     * @param matricula Modifica la matricula asociada al conductor
     */
    public synchronized void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * @return Date Devuelve la fecha de matriculación
     */
    public synchronized Date getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    /**
     * @return int Devuelve el permiso del conducir que posee el conductor
     */
    public synchronized int getTipoPermiso() {
        return tipoPermiso;
    }

    /**
     * @param tipoPermiso Modifica el permiso de conducir del conductor
     */
    public synchronized void setTipoPermiso(int tipoPermiso) {
        this.tipoPermiso = tipoPermiso;
    }

    /**
     * @return int Devuelve los puntos que posee el conductor
     */
    public synchronized int getPuntos() {
        return puntos;
    }

    /**
     * @param puntos Modifica los puntos que posee el conductor
     */
    public synchronized void setPuntos(int puntos) {
        this.puntos += puntos;
    }

}
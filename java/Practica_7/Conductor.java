import java.util.Date;

public class Conductor {
    private String nombre, DNI, matricula;
    private Date fechaMatriculacion;
    private int tipoPermiso, puntos;

    public Conductor(String nombre, String DNI, String matricula, int tipoPermiso) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.matricula = matricula;
        this.tipoPermiso = tipoPermiso;
        this.puntos = 10;
        fechaMatriculacion = new Date(System.currentTimeMillis());
    }

    public synchronized String getNombre() {
        return nombre;
    }

    public synchronized void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public synchronized String getDNI() {
        return DNI;
    }

    public synchronized void setDNI(String dNI) {
        DNI = dNI;
    }

    public synchronized String getMatricula() {
        return matricula;
    }

    public synchronized void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public synchronized Date getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public synchronized int getTipoPermiso() {
        return tipoPermiso;
    }

    public synchronized void setTipoPermiso(int tipoPermiso) {
        this.tipoPermiso = tipoPermiso;
    }

    public synchronized int getPuntos() {
        return puntos;
    }

    public synchronized void sumarPuntos(int puntos) {
        this.puntos += puntos;
    }

    public synchronized void restarPuntos(int puntos) {
        this.puntos -= puntos;
    }

}
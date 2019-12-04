import java.util.ArrayList;
import java.util.Date;

public class Conductores {
    ArrayList<Conductor> conductores = new ArrayList<>();

    public Conductores() {
    }

    public synchronized int getIndexConductor(Conductor c) {
        return conductores.indexOf(c);
    }

    public Conductor getConductor(int index) {
        return conductores.get(index);
    }

    public synchronized void addConductor(Conductor c) {
        conductores.add(c);
    }

    public synchronized void removeConductor(int index) {
        conductores.remove(index);
    }

    public synchronized int getNumConductores() {
        return conductores.size();
    }

    public String getNombreDeConductor(int index) {
        return conductores.get(index).getNombre();
    }

    public void setNombreDeConductor(int index, String nombre) {
        conductores.get(index).setNombre(nombre);
    }

    public String getDNIDeConductor(int index) {
        return conductores.get(index).getDNI();
    }

    public void setDNIDeConductor(int index, String DNI) {
        conductores.get(index).setDNI(DNI);
    }

    public String getMatriculaDeConductor(int index) {
        return conductores.get(index).getMatricula();
    }

    public void setMatricula(int index, String matricula) {
        conductores.get(index).setMatricula(matricula);
    }

    public Date getFechaMatriculacionDeConductor(int index) {
        return conductores.get(index).getFechaMatriculacion();
    }

    public int getTipoPermisoDeConductor(int index) {
        return conductores.get(index).getTipoPermiso();
    }

    public void setTipoPermisoDeConductor(int index, int tipoPermiso) {
        conductores.get(index).setTipoPermiso(tipoPermiso);
    }

    public int getPuntosDeConductor(int index) {
        return conductores.get(index).getPuntos();
    }

    public void sumarPuntosAConductor(int index, int puntos) {
        conductores.get(index).sumarPuntos(puntos);
    }

    public void restarPuntosAConductor(int index, int puntos) {
        conductores.get(index).restarPuntos(puntos);
    }

}
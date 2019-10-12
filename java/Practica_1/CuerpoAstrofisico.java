/**********************************
 * Clase CuerpoAstrofisico
 * 
 * Autor: Santiago Jesus Mas Peña Fecha: 11/10/2019
 ***********************************/

public class CuerpoAstrofisico {
    String name;
    float mass, period, radius;

    public CuerpoAstrofisico(String name, float mass, float period, float radius) {
        this.name = name;
        this.mass = mass;
        this.period = period;
        this.radius = radius;
    }

    public CuerpoAstrofisico(String name, float mass, float radius) {
        this(name, mass, 0f, radius);
    }

    public CuerpoAstrofisico(String name) {
        this(name, 0f, 0f, 1f);
    }

    public String getName() {
        return name;
    }

    public float getMass() {
        return mass;
    }

    public float getPeriod() {
        return period;
    }

    public float getRadius() {
        return radius;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public void setPeriod(float period) {
        this.period = period;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
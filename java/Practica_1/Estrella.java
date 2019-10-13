/**********************************
 * Clase Estrella
 * 
 * Autor: Santiago Jesus Mas Peña Fecha: 11/10/2019
 ***********************************/

public class Estrella extends CuerpoAstrofisico {
    String type;
    float lifeSpan;

    public Estrella(String name, float mass, float period, float radius, float lifeSpan, String type) {
        super(name, mass, period, radius);
        this.lifeSpan = lifeSpan;
        this.type = type;
    }

    public Estrella(String name, float mass, float radius, float lifeSpan, String type) {
        super(name, mass, radius);
        this.lifeSpan = lifeSpan;
        this.type = type;
    }

    public Estrella(String name, float lifeSpan, String type) {
        super(name);
        this.lifeSpan = lifeSpan;
        this.type = type;
    }

    public float getLifeSpan() {
        return lifeSpan;
    }

    public String getType() {
        return type;
    }

    public void setLifeSpan(float lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public void setType(String type) {
        this.type = type;
    }

}
/**********************************
 * Clase Estrella
 * 
 * Autor: Santiago Jesus Mas Peña Fecha: 11/10/2019
 ***********************************/

public class Estrella extends CuerpoAstrofisico {
    String lifeSpan, type;

    public Estrella(String name, float mass, float period, float radius, String lifeSpan, String type) {
        super(name, mass, period, radius);
        this.lifeSpan = lifeSpan;
        this.type = type;
    }

    public Estrella(String name, float mass, float radius, String lifeSpan, String type) {
        super(name, mass, radius);
        this.lifeSpan = lifeSpan;
        this.type = type;
    }

    public Estrella(String name, String lifeSpan, String type) {
        super(name);
        this.lifeSpan = lifeSpan;
        this.type = type;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public String getType() {
        return type;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public void setType(String type) {
        this.type = type;
    }

}
/**********************************
 * Clase Satelite
 * 
 * Autor: Santiago Jesus Mas Peña Fecha: 11/10/2019
 ***********************************/

public class Satelite extends CuerpoAstrofisico {
    CuerpoPlanetario parent;
    String material;
    float axisTilt;

    public Satelite(String name, float mass, float axisTilt, float period, float radius, CuerpoPlanetario parent,
            String material) {
        super(name, mass, axisTilt, period, radius);
        this.parent = parent;
        this.material = material;
        this.axisTilt=axisTilt;
    }

    public Satelite(String name, float mass, float axisTilt, float radius, String material) {
        super(name, mass, axisTilt, radius);
        this.material = material;
        this.axisTilt=axisTilt;
    }

    public Satelite(String name, float mass, float axisTilt, float period, float radius, String material) {
        super(name, mass, axisTilt, period, radius);
        this.material = material;
        this.axisTilt=axisTilt;
    }

    public CuerpoPlanetario getParent() {
        return parent;
    }

    public String getMaterial() {
        return material;
    }

    public float getAxisTilt(){
        return axisTilt;
    }

    public void setParent(CuerpoPlanetario parent) {
        this.parent = parent;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setAxisTilt(float axisTilt){
        this.axisTilt=axisTilt;
    }

}
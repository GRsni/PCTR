/**********************************
 * Clase CuerpoPlanetario
 * 
 * Autor: Santiago Jesus Mas Peña Fecha: 11/10/2019
 ***********************************/

public class CuerpoPlanetario extends CuerpoAstrofisico {
    String material;
    boolean hasLife, hasAtmosphere;
    Estrella parent;
    float axisTilt;

    public CuerpoPlanetario(String name, float mass, float axisTilt, float radius, String material, boolean hasLife,
            boolean hasAtmosphere, float period, Estrella centre) {
        super(name, mass, period, radius);
        this.material = material;
        this.hasLife = hasLife;
        this.hasAtmosphere = hasAtmosphere;
        this.parent = centre;
        this.axisTilt = axisTilt;
    }

    public CuerpoPlanetario(String name, float mass, float axisTilt, float radius, String material, boolean hasLife,
            boolean hasAtmosphere, Estrella centre) {
        super(name, mass, axisTilt, radius);
        this.material = material;
        this.hasLife = hasLife;
        this.hasAtmosphere = hasAtmosphere;
        this.parent = centre;
        this.axisTilt = axisTilt;
    }

    public CuerpoPlanetario(String name, String material, boolean hasLife, boolean hasAtmosphere) {
        super(name);
        this.material = material;
        this.hasLife = hasLife;
        this.hasAtmosphere = hasAtmosphere;
        this.axisTilt = 0f;
    }

    public String getMaterial() {
        return material;
    }

    public boolean hasLife() {
        return hasLife;
    }

    public boolean hasAtmosphere() {
        return hasAtmosphere;
    }

    public Estrella getParent() {
        return parent;
    }

    public float getAxisTilt() {
        return axisTilt;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setHasLife(boolean hasLife) {
        this.hasLife = hasLife;
    }

    public void setHasAtmosphere(boolean hasAtmosphere) {
        this.hasAtmosphere = hasAtmosphere;
    }

    public void setCentre(Estrella centre) {
        this.parent = centre;
    }

    public void setAxisTilt(float axisTilt) {
        this.axisTilt = axisTilt;
    }

}
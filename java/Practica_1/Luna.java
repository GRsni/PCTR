/**********************************
 * Clase Luna
 * 
 * Autor: Santiago Jesus Mas Peña Fecha: 11/10/2019
 ***********************************/

public class Luna extends Satelite {

    public Luna(CuerpoPlanetario parent) {
        super("Luna", 7.342e22f, 5.145f, 27.321661f, 1737100, parent, "Rock");
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s, Masa: %.4f kg, Radio: %f m, Periodo: %f dias, Planeta asociado: %s",
                getName(), getMass(), getRadius(), getPeriod(), parent.getName());
    }

}
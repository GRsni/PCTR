/**********************************
 * Clase Luna
 * 
 * Autor: Santiago Jesus Mas Peña Fecha: 11/10/2019
 ***********************************/

public class Luna extends Satelite {

    public Luna(CuerpoPlanetario parent) {
        super("Luna", 7.342e22, 5.145f, 27.321661f, 1737100, parent, "Rock");
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s, Masa: %d, Radio: %f, Tiempo de vida restante: %f, Planeta asociado: %s",
                getName(), getMass(), getRadius(), getLifeSpan(), parent.getName());
    }

}
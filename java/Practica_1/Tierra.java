/**********************************
 * Clase Tierra
 * 
 * Autor: Santiago Jesus Mas Peña Fecha: 11/10/2019
 ***********************************/

public class Tierra extends CuerpoPlanetario {

    public Tierra(Estrella sol) {
        super("Tierra", 5.97237e24, 7.155f, 6371000, "Rock/Water", true, true, sol);
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s, Masa: %d, Radio: %f, Material: %s, Estrella asociada: %s", getName(),
                getMass(), getRadius(), getMaterial(), parent.getName());
    }
}
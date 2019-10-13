/**********************************
 * Clase Tierra
 * 
 * Autor: Santiago Jesus Mas Peña Fecha: 11/10/2019
 ***********************************/

public class Tierra extends CuerpoPlanetario {

    public Tierra(Estrella sol) {
        super("Tierra", 5.97237e24f, 7.155f, 6371000f, "Roca/Agua", true, true, sol);
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s, Masa: %.4f kg, Radio: %f m, Material: %s, Estrella asociada: %s", getName(),
                getMass(), getRadius(), getMaterial(), parent.getName());
    }
}
/**********************************
 * Clase Sol
 * 
 * Autor: Santiago Jesus Mas Peña Fecha: 11/10/2019
 ***********************************/

public class Sol extends Estrella {

    public Sol() {
        super("Sol", 1.989e30f, 0f, 695510000, 5e9f, "G2V");
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s, Masa: %.4f kg, Radio: %f m, Tiempo de vida restante: %f dias", getName(),
                getMass(), getRadius(), getLifeSpan());
    }

}
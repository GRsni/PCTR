/**********************************
 * Clase SistemaSolar
 * 
 * Autor: Santiago Jesus Mas Peña Fecha: 11/10/2019
 ***********************************/

public class SistemaSolar {
    Sol sol;
    Tierra tierra;
    Luna luna;

    public SistemaSolar(Sol sol, Tierra tierra, Luna luna) {
        this.sol=sol;
        this.tierra=tierra;
        this.luna=luna;
    }

    public static void main(String[] args) {
        Sol sol=new Sol();
        Tierra tierra=new Tierra(sol);
        Luna luna=new Luna(tierra);

        SistemaSolar sistemaSolar=new SistemaSolar(sol, tierra, luna);
        System.out.println( sistemaSolar.sol.toString());
        System.out.println( sistemaSolar.tierra.toString());
        System.out.println( sistemaSolar.luna.toString());
        
    }
}
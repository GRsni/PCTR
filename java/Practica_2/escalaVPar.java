/**
 * Clase escalaVPar
 * 
 * @author Santiago Jesus Mas Peña
 * @version 18/10/19
 */

 public class escalaVPar extends Thread{
    private int start, end;
    float escalado;

    public escalaVPar(int start, int end, float escalado){
        this.start=start;
        this.end=end;
        this.escalado=escalado;
    }

    public void escalar(double[] vector){
        for(int i=start; i<end; i++){
            vector[i]*=escalado;
        }
    }
 }
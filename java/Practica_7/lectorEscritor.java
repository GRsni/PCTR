public class lectorEscritor {
    int lectores;
    boolean escribiendo;

    public lectorEscritor() {
        lectores = 0;
        escribiendo = false;
    }

    public synchronized void empiezaLeer() {
        while (escribiendo) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        lectores++;
        notifyAll();

    }

    public synchronized void finLeer() {
        lectores--;
        if (lectores == 0) {
            notifyAll();
        }
    }

    public synchronized void empiezaEscribir(){
        while(lectores>0 || escribiendo){
            try{
            wait();
            }catch(InterruptedException e){}
        }
        escribiendo=true;
    }

    public synchronized void finEscribir(){
        escribiendo=false;
        notifyAll();
    }
}
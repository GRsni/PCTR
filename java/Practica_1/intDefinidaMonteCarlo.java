import java.util.Scanner;

class intDefinidaMonteCarlo{
    final int steps;

    public intDefinidaMonteCarlo(int n){
        steps=n;
    }
    

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.println("Escribe en numero de iteraciones: ");
        int steps=in.nextInt();
        intDefinidaMonteCarlo entidad=new intDefinidaMonteCarlo(steps);
        entidad.calculaMonteCarlo();
        in.close();
    }

    void calculaMonteCarlo(){
        int exitos=0;
        for(int i=0; i<steps; i++){
            double randX=Math.random();
            double randY=Math.random();
            if(randY<=functionX(randX)){
                exitos++;
            }
        }
        System.out.println("Integral aproximada:" +(double)exitos/steps);
    }

    double functionX(double x){
        return x;
    }

}
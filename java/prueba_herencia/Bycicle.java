public class Bycicle{
    public int cadence;
    public int gear;
    public int speed;

    public Bycicle(int cadence, int gear, int speed){
        this.cadence=cadence;
        this.gear=gear;
        this.speed=speed;
    }

    public void setCadence(int newCadence){
        cadence=newCadence;
    }

    public void brake(int slow){
        speed-=slow;
    }

    public void speedUp(int increment){
        speed+=increment;
    }

}
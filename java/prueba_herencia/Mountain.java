public class Mountain extends Bycicle{
    public int seatHeight;

    public Mountain(int seat, int cadence, int gear, int speed){
        super(cadence, gear, speed);
        seatHeight=seat;
    }

    public void setHeight(int height){
        seatHeight=height;
    }
    
    public void brake(int slow){
        speed-=slow*2;
    }

    public static void main(String[] args) {
        Mountain mBike=new Mountain(10, 3, 1, 0);        
        System.out.println(mBike.speed);
        mBike.speedUp(50);
        System.out.println(mBike.speed);
        mBike.brake(10);
        System.out.println(mBike.speed);

    }
}
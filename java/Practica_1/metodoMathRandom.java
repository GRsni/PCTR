public class metodoMathRandom {

	public static void main(String[] args) {

		int num1=50;
		int num2=120;
<<<<<<< HEAD
		final int cant=10;

		System.out.println("Nï¿½meros generados entre 0 y 20, con decimales (sin incluir el 0 y el 20)");
		for (int i=0;i<cant;i++){
=======

		System.out.println("Números generados entre 0 y 20, con decimales (sin incluir el 0 y el 20)");
		for (int i=0;i<1000;i++){
>>>>>>> 62d9ed4c3418f4699588c3fe16de98efacfabe1a
			double numAleatorio=Math.random()*20;
			System.out.println(numAleatorio);
		}

<<<<<<< HEAD
		System.out.println("Nï¿½meros generados entre 5 y 20, con decimales (sin incluir el 5 y el 20)");
		for (int i=0;i<cant;i++){
=======
		System.out.println("Números generados entre 5 y 20, con decimales (sin incluir el 5 y el 20)");
		for (int i=0;i<1000;i++){
>>>>>>> 62d9ed4c3418f4699588c3fe16de98efacfabe1a
			double numAleatorio=Math.random()*(20-5)+5;
			System.out.println(numAleatorio);
		}

<<<<<<< HEAD
		System.out.println("Nï¿½meros generados entre 50 y 120, sin decimales (sin incluir el 50 y el 120)");
		for (int i=0;i<cant;i++){
=======
		System.out.println("Números generados entre 50 y 120, sin decimales (sin incluir el 50 y el 120)");
		for (int i=0;i<1000;i++){
>>>>>>> 62d9ed4c3418f4699588c3fe16de98efacfabe1a
			int numAleatorio=(int)Math.floor(Math.random()*(num1-num2)+num2);
			System.out.println(numAleatorio);
		}

<<<<<<< HEAD
                System.out.println("Nï¿½meros generados entre 50 y 120, sin decimales (incluyendo el 50 y el 120)");
		for (int i=0;i<cant;i++){
=======
                System.out.println("Números generados entre 50 y 120, sin decimales (incluyendo el 50 y el 120)");
		for (int i=0;i<1000;i++){
>>>>>>> 62d9ed4c3418f4699588c3fe16de98efacfabe1a
			int numAleatorio=(int)Math.floor(Math.random()*(num1-(num2+1))+(num2));
			System.out.println(numAleatorio);
		}
	}

}
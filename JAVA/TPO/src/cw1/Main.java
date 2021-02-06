package cw1;

public class Main {

	public static void main(String[] args) {
		final Vector GRAV=new Vector(0, -10);//  metr/sec^2
		final Vector WIATR=new Vector(-3, 0);
		
		double k=0.3; //opor osrodka
		
		double x=0;//start x
		double y=0;//start y
		
		
		Vector start=new Vector(10, 10);
		
		double t=0.1;//przedzial czasu w sec
		System.out.println(x+" | "+y);
		do{
			
			double DSx=t*start.Vx,
				   DSy=t*start.Vy,
				   DVx=t*GRAV.Vx,
				   DVy=t*GRAV.Vy;
			x+=DSx;
			y+=DSy;
			start.dodaj(new Vector(DVx, DVy));
			System.out.println(x+" | "+y);
		}while(y>0);
	}

}

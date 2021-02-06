package cw1;

public class Vector {
	
	double Vx,Vy;
	public Vector(double Vx,double Vy) {
		this.Vx=Vx;
		this.Vy=Vy;
	}
	public void dodaj(Vector x){
		this.Vx=this.Vx+x.Vx;
		this.Vy=this.Vy+x.Vy;
	}
}

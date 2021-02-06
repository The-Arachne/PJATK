package proj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Perceptron {
	
	Double[]tab;
	Double Omega;
	String name;
	public Perceptron(int howMany,String name) {//INICJACJA WART od 0 do -200
		this.name=name;
		Omega=Math.random()*-200;
		tab=new Double[howMany];
		for(int i=0;i<tab.length;i++)
			tab[i]=Math.random()*-200;
	}
	
	public Double[] Learn(int estimatedResult,ArrayList<Paczka> vectorSetosa){
		int indx=0;
		double wsplLearning=0.4;
		boolean ostRunda=true;
		while(indx<vectorSetosa.size()){
			
			Double s=0.0;
			for(int i=0;i<tab.length;i++){
				//System.out.println(tab[i]+" "+vectorSetosa.get(indx).list.get(i)+" a");
				s+=tab[i]*vectorSetosa.get(indx).list.get(i);
			}
			s-=Omega;
			int odp=(s>=0)?1:-1;
			
			if(odp==estimatedResult){
				indx++;
				//System.out.println("yes "+s);
				if(indx==vectorSetosa.size()&&ostRunda){
					//System.out.println("ONE MORE TIME");
					ostRunda=false;
					indx=0;
				}
			}else{
				ostRunda=true;
				//System.out.println("NOPE "+s);
				for(int i=0;i<tab.length;i++){
					tab[i]=tab[i]+(estimatedResult-odp)*wsplLearning*vectorSetosa.get(indx).list.get(i);
				}
				Omega=Omega+estimatedResult-odp;
				indx=0;
			}
			
		}
		return tab;
		
	}
	public String CheckAnswer(ArrayList<Double> vec){
		Double s=0.0;
		for(int i=0;i<tab.length;i++){
			//System.out.println(tab[i]+" "+vectorSetosa.get(indx).list.get(i)+" a");
			s+=tab[i]*vec.get(i);
		}
		if(s>=Omega)
			return name;
		else
			return "sth_else";
					
		
	}

	public Double[] getVec() {
		
		return tab;
	}
	public Double getOmega(){
		return Omega;
	}

}

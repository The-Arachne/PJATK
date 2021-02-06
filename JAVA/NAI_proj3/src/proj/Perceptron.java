package proj;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class Perceptron {
	
	Double[]tab;
	Double Omega;
	String name;
	public Perceptron(int howMany,String name) {//INICJACJA WART od 0 do -200
		this.name=name;
		Omega=0.0;
		tab=new Double[howMany];
		for(int i=0;i<tab.length;i++)
			tab[i]=0.0;
	}
	
	public Double[] Learn(int estimatedResult,ArrayList<Paczka> vector){
		
		
		for(Paczka xd:vector){
			double euclides=0.0;
			for(Double benc:xd.list){
				euclides+=benc;
			}
			for(Double benc:xd.list){
				benc=benc/euclides;
			}
			System.out.println(xd);
		}
		
		
		int indx=0;
		double wsplLearning=0.11;
		boolean ostRunda=false;
		while(indx<vector.size()){
			
			Double s=0.0;
			for(int i=0;i<tab.length;i++){
				//System.out.println(tab[i]+" "+vector.get(indx).list.get(i)+" a");
				s+=tab[i]*vector.get(indx).list.get(i);
			}
			s-=Omega;
			int actualResult=(s>=0)?1:0;
			
			if(actualResult==estimatedResult){
				indx++;
				System.out.println("yes "+s);
				if(indx==vector.size()&&ostRunda){
					//System.out.println("ONE MORE TIME");
					ostRunda=false;
					indx=0;
				}
			}else{
				ostRunda=true;
				System.out.println("NOPE "+s);
				for(int i=0;i<tab.length;i++){
					tab[i]=tab[i]+(estimatedResult-actualResult)*wsplLearning*vector.get(indx).list.get(i);
				}
				//Omega=Omega+(estimatedResult-actualResult)*wsplLearning;
				indx=0;
			}
			
		}
		return tab;
		
	}
	public double CheckAnswer(ArrayList<Double> vec){
		Double ak=0.0;
		for(Double xd:vec){
			ak+=xd;
		}
		for(Double xd:vec){
			xd=xd/ak;
		}
		Double s=0.0;
		for(int i=0;i<tab.length;i++){
			//System.out.println(tab[i]+" "+vectorSetosa.get(indx).list.get(i)+" a");
			s+=tab[i]*vec.get(i);
		}

		return 1/(1+Math.pow(Math.E, -1*s*1.42));
	}

	public Double[] getVec() {
		
		return tab;
	}
	public Double getOmega(){
		return Omega;
	}
	@Override
	public String toString() {
		String tmp="";
		DecimalFormat df = new DecimalFormat("####0.000");
		for(Double xd:tab)
			tmp+=df.format(xd)+" | ";
		return "[_"+name+"_] ()Omega: "+df.format(Omega)+" ()Vec: "+tmp;
	}

}


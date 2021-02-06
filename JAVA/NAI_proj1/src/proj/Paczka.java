package proj;

import java.util.ArrayList;

public class Paczka {
	
	String name;
	ArrayList<Double> list=new ArrayList<>();
	
	
	void wypisz(){
		System.out.print(name + " ->|");
		for(Double xd : list){
			System.out.print(xd+" ");
		}
		System.out.println();
	}
}

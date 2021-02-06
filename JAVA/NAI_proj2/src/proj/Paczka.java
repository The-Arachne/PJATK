package proj;

import java.util.ArrayList;

public class Paczka {
	
	String flowersName;
	ArrayList<Double> list=new ArrayList<>();
	
	
	void wypisz(){
		System.out.print(flowersName + " ->|");
		for(Double xd : list){
			System.out.print(xd+" ");
		}
		System.out.println();
	}
}


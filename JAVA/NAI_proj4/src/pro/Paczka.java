package pro;

import java.util.ArrayList;

public class Paczka {
	
	String nameInPaczka;
	ArrayList<Double> soldierPosition=new ArrayList<>();
	
	
	
	public String toString() {
		String xd=nameInPaczka + " ->|";
		for(int i=0;i<soldierPosition.size();i++){
			xd+=(char)('a'+i)+": "+String.format("%.2f", (soldierPosition.get(i)))+" ";
		}
		return xd;
	};
}
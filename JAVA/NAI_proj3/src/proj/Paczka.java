package proj;

import java.util.ArrayList;

public class Paczka {
	
	String nameInPaczka;
	ArrayList<Double> list=new ArrayList<>();
	
	
	public String toString() {
		String xd=nameInPaczka + " ->|";
		for(int i=0;i<list.size();i++){
			xd+=(char)('a'+i)+": "+String.format("%.5f", (list.get(i)))+" ";
		}
		return xd;
	};
}
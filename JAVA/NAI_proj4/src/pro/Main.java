package pro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[]args) throws FileNotFoundException{
		
		
		//STAGE 1: Import data
		
		Scanner scan=new Scanner(new File(System.getProperty("user.home")+"/Desktop"+"/Nai/iris_training.txt"));
		ArrayList<Paczka> list=new ArrayList<>();
		
		while(scan.hasNext()){
			Paczka tmp=new Paczka();
			Scanner line=new Scanner(scan.nextLine().replaceAll(",", "."));
			while(line.hasNext()){
				String trueName=line.next().trim();
				if(!line.hasNext()){
					tmp.nameInPaczka=trueName;
				}else{
					tmp.soldierPosition.add(Double.valueOf(trueName));
				}	
			}
			list.add(tmp);
			line.close();
		}
		scan.close();
		System.out.println(list);
		
		PerceptronNON perc=new PerceptronNON();
		perc.computeAllData(list);
		
		
	}
}

package pac;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<ArrayList<Paczka>> dane=getDataFrom(new File(System.getProperty("user.home")+"/Desktop"+"/Nai/tsp.txt"));

	}
	private static ArrayList<ArrayList<Paczka>>getDataFrom(File fil) throws FileNotFoundException{
		Scanner scan=new Scanner(fil);
		ArrayList<ArrayList<Paczka>> odpowiedz=new ArrayList<>();
		
		char xfrom='A';
		for(String line="";scan.hasNextLine();line=scan.nextLine().replaceAll("\\D+", " ")){
			if(line.length()>1) {
				
				ArrayList<Paczka> tmpList=new ArrayList<>();
				Scanner scanCyfr=new Scanner(line);
				for(Integer cyfra=Integer.parseInt(scanCyfr.next());scanCyfr.hasNext();cyfra=Integer.parseInt(scanCyfr.next())){
					System.out.print(cyfra+" ");
				}
				System.out.println();
			}
		}
		scan.close();
		return odpowiedz;
	}
}

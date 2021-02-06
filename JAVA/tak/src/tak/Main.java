package tak;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan=new Scanner(new File("C:\\Users\\Sebastian Kachniarz\\Desktop\\Derby\\database\\POZYCJE.txt"));
		PrintWriter zapis=new PrintWriter("C:\\Users\\Sebastian Kachniarz\\Desktop\\Derby\\database\\AUTOR2.txt");
		while(scan.hasNext()){
			String xd=scan.nextLine();
			xd=xd.replaceAll("\"", "\'");
			xd=xd.replaceFirst(",", "\',");
			xd="('"+xd+"),";
			zapis.append(xd);
			zapis.flush();
		}
		System.out.println("DONE");
		

	}

}

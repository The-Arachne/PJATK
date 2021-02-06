package tmp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan=new Scanner(new File("C:\\Users\\Sebastian Kachniarz\\Desktop\\Sławuś\\marki.txt"));
		PrintWriter zapis=new PrintWriter("C:\\Users\\Sebastian Kachniarz\\Desktop\\Sławuś\\marki2.txt");
		
		while(scan.hasNext()){
			String xd="\""+scan.next()+"\", ";
			zapis.append(xd);
		}
		zapis.flush();

	}

}

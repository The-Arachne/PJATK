package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Futil {

	public static void processDir(String dirName, String resultFileName) {
		System.out.println(dirName);
		Path starting_dir=Paths.get(dirName);
		//Path res_in_file=Paths.get(resultFileName);
		
		
		try {
			FileVisitorBenc visitor=new FileVisitorBenc(resultFileName);
			Files.walkFileTree(starting_dir, visitor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

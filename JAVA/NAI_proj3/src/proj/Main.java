package proj;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class Main {

	static TxtParser txtpars=new TxtParser();
	public static void main(String[] args) {
		
		
		//STAGE 1: get data & reformat to latino alphabet & get propotions of every char in txt
		ArrayList<Perceptron>perceptrons=new ArrayList<>();
		ArrayList<ArrayList<Paczka>> proportions=new ArrayList<>();
		try {
			Stream<Path> paths=Files.walk(Paths.get(System.getProperty("user.home") + "/Desktop/Nai"+"/DANE"));
			paths.filter(Files::isDirectory).forEach(dName->{
				String name=dName.toString().replace("C:\\Users\\Sebastian Kachniarz\\Desktop\\Nai\\DANE", "").replace("\\", "");
				
				if(name.length()>0){
					perceptrons.add(new Perceptron(26, name));
					try {
						ArrayList<Paczka>tmpProportions=new ArrayList<>();
						Stream<Path>file=Files.walk(Paths.get(String.valueOf(dName)));
						file.filter(Files::isRegularFile).forEach(fName->{
							try {
								tmpProportions.add(TxtParser.countLetters(TxtParser.getLatinoAlphabet(Files.readAllLines(fName).toString()),name));
							} catch (Exception e) {
								e.printStackTrace();
							}
						});
						proportions.add(tmpProportions);
						file.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			paths.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//STAGE 2: Perceptrons are learning their proportions
		for(int i=0;i<32;i++)
		for(int indx=0;indx<proportions.size();indx++){
			ArrayList<Paczka>tmpProportions=proportions.get(indx);
			for(Perceptron perc:perceptrons){
				int what=(perc.name.equals(tmpProportions.get(0).nameInPaczka))? 1:0;
				//System.out.println(perc.name+" vs "+tmpProportions.get(0).nameInPaczka+" |"+what);
				if(what==1){
					//System.out.println(perc);
					perc.Learn(what, tmpProportions);
					//System.out.println(perc);
				}else
				if(i%3==1){
					perc.Learn(what, tmpProportions);
				}
				
			}
		}
		
		//STAGE 3: GUI
		GUI gui=new GUI(txtpars,perceptrons);
		
		
		
	}
}
class TxtParser{
	static String getLatinoAlphabet(String input){
		return input.replaceAll("(?![a-zA-Z]).", "").toLowerCase();
	}
	static Paczka countLetters(String input,String name){
		
		HashMap<Character,Integer>howMany=new HashMap<>();
		for (char ch = 'a'; ch <= 'z'; ++ch) 
			howMany.put(ch, 0); 
		
		for(int i=0;i<input.length();i++){
			char tmp=input.charAt(i);
			if(howMany.containsKey(tmp)){
				howMany.replace(tmp, howMany.get(tmp)+1);
			}
		}
		Paczka output=new Paczka();
		output.nameInPaczka=name;
		ArrayList<Double>propotions=new ArrayList<>();
		for(Entry<Character, Integer> entry : howMany.entrySet()) {
			propotions.add(entry.getValue()*1.0/input.length());
		}
		output.list=propotions;
		return output;
	}

}

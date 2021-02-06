package proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		
		
		//STAGE 1 get DATA for Setosa
		Scanner readFile=new Scanner(new File(System.getProperty("user.home") + "\\Desktop\\iris_training.txt"));
		ArrayList<Paczka> vectorSetosa=new ArrayList<>();
		ArrayList<Paczka> resztaSmieci=new ArrayList<>();
		
		while(readFile.hasNext()){
			Paczka tmp=new Paczka();
			Scanner line=new Scanner(readFile.nextLine().replaceAll(",", "."));
			while(line.hasNext()){
				String flowersName=line.next().trim();
				if(!line.hasNext()){
					tmp.flowersName=flowersName;
				}else{
					tmp.list.add(Double.valueOf(flowersName));
				}	
			}
			if(tmp.flowersName.equals("Iris-setosa"))
			vectorSetosa.add(tmp);
			else
				resztaSmieci.add(tmp);
			line.close();
		}
		//STAGE 2 PERCEPTRON iris-setosa is LEARNING
		Perceptron setosa=new Perceptron(vectorSetosa.get(0).list.size(),"Iris-setosa");
		for(int i=0;i<10;i++){
			setosa.Learn(1, vectorSetosa);
			setosa.Learn(-1, resztaSmieci);
		}
		
		//STAGE 3 GET THE TEST DATA and test it with perceptron
		
		readFile=new Scanner(new File(System.getProperty("user.home") + "\\Desktop\\iris_test.txt"));
		double howManyInTestFile=0,howManyCorrectAnswrs=0;
		while(readFile.hasNext()){
			howManyInTestFile++;
			String odp="";
			ArrayList<Double>vec=new ArrayList<>();
			Scanner line=new Scanner(readFile.nextLine().replaceAll(",", "."));
			while(line.hasNext()){
				String tmpx=line.next().trim();
				if(!line.hasNext()){
					odp=tmpx.equals("Iris-setosa")?"Iris-setosa":"sth_else";
				}else{
					vec.add(Double.valueOf(tmpx));
				}	
			}
			line.close();
			//STAGE 3 check
			
			if(setosa.CheckAnswer(vec).equals(odp))
				howManyCorrectAnswrs++;
		}
		readFile.close();
		System.out.println("prawidlowo zaklasyfikowanych przez perceptron zostalo: "+howManyCorrectAnswrs+" ["+(howManyCorrectAnswrs/howManyInTestFile)*100+"%] -> dla: omega:"+setosa.getOmega()+" vec: "+Arrays.deepToString(setosa.getVec()));
		System.out.println("/////////////////////////////////////////////////////////////////////");
		//STAGE 4 infinite crusade
		
		int result=1;
		while(result!=-1){
			JComponent[] inputs = new JComponent[setosa.getVec().length*2];
			ArrayList<JTextField>text=new ArrayList<>();
			for(int i=0;i<inputs.length;i+=2){
				inputs[i]=new JLabel("Atrybut:"+((i+2)/2));
				JTextField tmp=new JTextField();
				inputs[i+1]=tmp;
				text.add(tmp);
			}
			
			result = JOptionPane.showConfirmDialog(null, inputs, "Wprowadz wartosci atrybutow(default:1)", JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.OK_OPTION) {
				ArrayList<Double>tak=new ArrayList<>();
			    for(int i=0;i<text.size();i++){
			    	double x=1.0;
			    	try {
			    		x=Double.parseDouble(text.get(i).getText());
					} catch (Exception e) {
						
					}
			    	tak.add(x);
			    	
			    }
			    System.out.println("dla wart Vec: "+tak.toString()+" wynik wynosi: "+setosa.CheckAnswer(tak));
			}
		}
	
	}

}

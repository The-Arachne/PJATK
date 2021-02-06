import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		ArrayList<Paczka> trening=getDataFrom(new File(System.getProperty("user.home")+"/Desktop"+"/Nai/iris_training.txt"));
		ArrayList<Paczka> test=getDataFrom(new File(System.getProperty("user.home")+"/Desktop"+"/Nai/iris_test.txt"));
		int ileKlas=6;
		ZmienNaDyskretne dysk=new ZmienNaDyskretne(ileKlas);
		System.out.println(trening);
		trening=dysk.convertAndSet(trening);
		System.out.println(trening);
		System.out.println();
		
		System.out.println(test);
		test=dysk.convert(test);
		System.out.println(test);
		Bayess bas=new Bayess(trening,ileKlas);
		bas.computeAll(test);
		
		
		
		System.out.println("///////////////////////////////\n");
		/*int result=1;
		while(result!=-1){
			JComponent[] inputs = new JComponent[trening.get(0).soldierPosition.size()*2];
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
			    Paczka tmp=new Paczka();
			    tmp.nameInPaczka="x";
			    tmp.soldierPosition=tak;
			    ArrayList<Paczka>xd=new ArrayList<>();
			    xd.add(tmp);
			    System.out.println("dla wart Vec: "+tak.toString()+" wynik wynosi: "+bas.werdykt(dysk.convert(xd)));
			}
		}
		*/
		
	}
	private static ArrayList<Paczka>getDataFrom(File fil) throws FileNotFoundException{
		Scanner scan=new Scanner(fil);
		ArrayList<Paczka> lista=new ArrayList<>();
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
			lista.add(tmp);
			line.close();
		}
		scan.close();
		return lista;
	}

}

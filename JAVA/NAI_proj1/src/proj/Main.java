package proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		
		//Stage 1 input wiedza
		Scanner odczyt=new Scanner(new File(System.getProperty("user.home") + "\\Desktop\\Nai\\iris_training.txt"));
		ArrayList<Paczka> lista=new ArrayList<>();
		
		while(odczyt.hasNext()){
			Paczka tmp=new Paczka();
			Scanner line=new Scanner(odczyt.nextLine().replaceAll(",", "."));
			while(line.hasNext()){
				String tmpx=line.next().trim();
				if(!line.hasNext()){
					tmp.name=tmpx;
				}else{
					tmp.list.add(Double.valueOf(tmpx));
				}	
			}
			lista.add(tmp);
			line.close();
		}
		
		//Stage 2: testing input!!
		odczyt=new Scanner(new File(System.getProperty("user.home") + "\\Desktop\\Nai\\iris_test.txt"));
		int k=0;
		while(k<1){
			try {
				//BIERZEMY INFO OD UZYTKOWNIKA
				k=Integer.parseInt(JOptionPane.showInputDialog(null, "Podaj wartosc parametru K (integer)"));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Cos poszlo nie tak sprobuj jeszcze raz", "Blad przy parsowaniu na int", JOptionPane.ERROR_MESSAGE);
			}
		}
		double traf=0,ile=0;
		while(odczyt.hasNext()){
			ile++;
			String odp="";
			ArrayList<Double>vec=new ArrayList<>();
			Scanner line=new Scanner(odczyt.nextLine().replaceAll(",", "."));
			while(line.hasNext()){
				String tmpx=line.next().trim();
				if(!line.hasNext()){
					odp=tmpx;
				}else{
					vec.add(Double.valueOf(tmpx));
				}	
			}
			line.close();
			//STAGE 3 check
			if(ZnajdzNajbliszyIPowtarzajacy(lista,vec,k).equals(odp))
				traf++;
		}
		odczyt.close();
		System.out.println("dla k= "+k+" prawidlowo zaklasyfikowanych zostalo: "+traf+" ["+(traf/ile)*100+"%]");
		System.out.println("/////////////////////////////////////////////////////////////////////////");
		
		
		
		//stage 4 infinite crusade
		int result=1;
		while(result!=-1){
			JComponent[] inputs = new JComponent[lista.get(0).list.size()*2];
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
			    System.out.println("dla k= "+k+" i wart Vec: "+tak.toString()+" wynik wynosi: "+ZnajdzNajbliszyIPowtarzajacy(lista, tak, k));
			}
		}
		
	}

	private static String ZnajdzNajbliszyIPowtarzajacy(ArrayList<Paczka> lista, ArrayList<Double> vec, int ile) {
		//Wol roboczy tego programu- moze zawierac blad!!!
		ArrayList<Synch>najblizsze=new ArrayList<>();
		for(Paczka xd:lista){
			Double suma=0.0;
			for(int i=0;i<vec.size();i++){
				suma+=Math.abs(vec.get(i)-xd.list.get(i));//LICZENIE ODLEGLOSCI
			}
			najblizsze.add(new Synch(xd.name, suma));
		}
		Collections.sort(najblizsze);
		if(najblizsze.get(0).odleglosc==0)
			return najblizsze.get(0).name;
		
		HashMap<String,Integer>wynik=new HashMap<>();
		for(int i=0;i<ile;i++){
			if(wynik.containsKey(najblizsze.get(i).name)){
				wynik.replace(najblizsze.get(i).name, wynik.get(najblizsze.get(i).name)+1);
			}else
				wynik.put(najblizsze.get(i).name,1);
		}
		String name="";
		int max=0;
		for(Entry<String, Integer> ta:wynik.entrySet()){
			if(ta.getValue()>max){
				max=ta.getValue();
				name=ta.getKey();
			}
		}
		return name;
	}

}
class Synch implements Comparable<Synch>{
	String name;
	double odleglosc;
	public Synch(String nam,double od) {
		name=nam;
		odleglosc=od;
	}
	@Override
	public int compareTo(Synch o) {
		return this.odleglosc > o.odleglosc? 1 : this.odleglosc < o.odleglosc? -1 : 0;
	}
}

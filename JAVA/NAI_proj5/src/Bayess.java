import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bayess {
	
	ArrayList<Paczka> baza;
	String log;
	int jes;
	public Bayess(ArrayList<Paczka> trening,int jas) {
		baza=trening;
		log="";
		jes=jas;
	}
	
	public void computeAll(ArrayList<Paczka> test) {
		int traf=0;
		//inicjowanie roznych setos
		HashMap<String,Double>chance=new HashMap<>();
		HashMap<String,HashMap<String,Integer>>ilosc=new HashMap<>();
		HashMap<String,Integer>inner=new HashMap<>();
		for(Paczka base:baza){
			if(!chance.containsKey(base.nameInPaczka)){
				chance.put(base.nameInPaczka, 0.0);
				inner.put(base.nameInPaczka, 0);
				ilosc.put(base.nameInPaczka, null);
			}
		}
		for(Map.Entry<String,HashMap<String,Integer>> entry:ilosc.entrySet())
			entry.setValue(new HashMap<>(inner));
			
		for(Paczka input:test){
			log+="\n"+input.nameInPaczka+"\n";
			
			for(Map.Entry<String, Double> entry:chance.entrySet()){
				double[]ileCzego=new double[input.soldierPosition.size()];
				int ile=0;
				for(Paczka base:baza){
					if(base.nameInPaczka.equals(entry.getKey())){
						ile++;
						for(int i=0;i<ileCzego.length;i++){
							if(base.soldierPosition.get(i).equals(input.soldierPosition.get(i))){
								ileCzego[i]++;
							}
						}
					}
						
				}//czy wygladzamy?
				double przedWygl=1;
				boolean czyWygladzamyAll=false;
				for(int i=0;i<ileCzego.length;i++){
					log+=(ileCzego[i]+" ");
					przedWygl=przedWygl*(ileCzego[i]/(ile*1.0));
					if(ileCzego[i]==0.0)
						czyWygladzamyAll=true;
				}
					
				
				//WYGLADZANIE ALL
				if(czyWygladzamyAll){
					for(int i=0;i<ileCzego.length;i++)
						ileCzego[i]=(ileCzego[i]+1.0)/(ile+jes);
						
				}else{//Wygladzamy pierwszy
					ileCzego[0]=(ileCzego[0]+1)/(ile+jes);
				}
				
				double licznik=1;
				for(int i=0;i<ileCzego.length;i++){
					
					licznik*=(ileCzego[i]/(ile*1.0));
				}
				entry.setValue(licznik/(ile/(1.0*baza.size())));
				log+=(czyWygladzamyAll+" "+entry.getKey()+" "+ile+" | przed Wygl: "+(przedWygl/(1.0*baza.size()))+" po Wygl: "+entry.getValue())+"\n";
				
			}
			double max=0;
			String odp="";
			for(Map.Entry<String, Double> entry:chance.entrySet()){
				if(max<entry.getValue()){
					max=entry.getValue();
					odp=entry.getKey();
				}
			}
			for(Map.Entry<String,HashMap<String,Integer>> entry:ilosc.entrySet())
				if(entry.getKey().equals(input.nameInPaczka)){
					for(Map.Entry<String,Integer> innerMap:entry.getValue().entrySet())
						if(innerMap.getKey().equals(odp))
							innerMap.setValue(innerMap.getValue()+1);
					break;
				}
			if(odp.equals(input.nameInPaczka))
				traf++;
			log+="werdykt: "+odp+"\n";
		}
		System.out.println(log);
		
		System.out.println("///////////////////////////////\n");
		for(Map.Entry<String,Integer> innerMap:ilosc.get("Iris-versicolor").entrySet())
			System.out.print(innerMap.getKey()+" | ");
			System.out.print("<- zaklasyfikowano jako\n");
		
		for(Map.Entry<String,HashMap<String,Integer>> entry:ilosc.entrySet()){
			System.out.print("|     ");
			for(Map.Entry<String,Integer> innerMap:entry.getValue().entrySet())
				System.out.print(innerMap.getValue()+"    |    ");
			System.out.print(entry.getKey()+"\n");
		}
			System.out.println();
		System.out.println("ACCURACY: "+roundToDecimal(100*((traf*1.0)/test.size()),2)+"%\n");
		
		NumberFormat formatter = new DecimalFormat("00");  
		for(Map.Entry<String,HashMap<String,Integer>> dlaKazdego:ilosc.entrySet()){
			System.out.println("Dla: "+dlaKazdego.getKey());
			int TP=0,FP=0,FN=0,TN=0;
			for(Map.Entry<String,HashMap<String,Integer>> entry:ilosc.entrySet()){
				//System.out.println("\t"+entry.getKey());
				for(Map.Entry<String,Integer> innerMap:entry.getValue().entrySet()){
					//System.out.println("\t\t"+innerMap.getKey()+" "+innerMap.getValue());
					if(dlaKazdego.getKey().equals(entry.getKey())){
						if(dlaKazdego.getKey().equals(innerMap.getKey())){
							TP+=innerMap.getValue();
						}else{
							FN+=innerMap.getValue();
						}
					}else{
						if(dlaKazdego.getKey().equals(innerMap.getKey())){
							FP+=innerMap.getValue();
						}else{
							TN+=innerMap.getValue();
						}
					}
				}
			}
			System.out.println("\t    |RE +|RE -|");
			System.out.println("\tPR +| "+formatter.format(TP)+" | "+formatter.format(FN)+" |");
			System.out.println("\tPR -| "+formatter.format(FP)+" | "+formatter.format(TN)+" |");
			System.out.println();
			System.out.println("\tCzulosc: "+roundToDecimal(((TP*1.0)/(TP+FN))*100,2)+"%");
			System.out.println("\tSwoistosc: "+roundToDecimal(((TN*1.0)/(FP+TN))*100,2)+"%");
			System.out.println("\tPrecyzja: "+roundToDecimal(((TP*1.0)/(TP+FP))*100,2)+"%");
			System.out.println("\tDokladnosc: "+roundToDecimal(((TP+TN*1.0)/((TP+FN)+(FP+TN)))*100,2)+"%");
		}
		
	}
	
	public String werdykt(ArrayList<Paczka> arrayList){
		HashMap<String,Double>chance=new HashMap<>();
		for(Paczka base:baza)
			if(!chance.containsKey(base.nameInPaczka))
				chance.put(base.nameInPaczka, 0.0);
		Paczka input=arrayList.get(0);
		
		for(Map.Entry<String, Double> entry:chance.entrySet()){
			double[]ileCzego=new double[input.soldierPosition.size()];
			int ile=0;
			for(Paczka base:baza){
				if(base.nameInPaczka.equals(entry.getKey())){
					ile++;
					for(int i=0;i<ileCzego.length;i++){
						if(base.soldierPosition.get(i).equals(input.soldierPosition.get(i))){
							ileCzego[i]++;
						}
					}
				}
					
			}//czy wygladzamy?
			double przedWygl=1;
			boolean czyWygladzamyAll=false;
			for(int i=0;i<ileCzego.length;i++){
				log+=(ileCzego[i]+" ");
				przedWygl=przedWygl*(ileCzego[i]/(ile*1.0));
				if(ileCzego[i]==0.0)
					czyWygladzamyAll=true;
			}
				
			
			//WYGLADZANIE ALL
			if(czyWygladzamyAll){
				for(int i=0;i<ileCzego.length;i++)
					ileCzego[i]=(ileCzego[i]+1.0)/(ile+jes);
					
			}else{//Wygladzamy pierwszy
				ileCzego[0]=(ileCzego[0]+1)/(ile+jes);
			}
			
			double licznik=1;
			for(int i=0;i<ileCzego.length;i++){
				
				licznik*=(ileCzego[i]/(ile*1.0));
			}
			entry.setValue(licznik*(ile/(1.0*baza.size())));
			
		}
		double max=0;
		String odp="";
		for(Map.Entry<String, Double> entry:chance.entrySet()){
			if(max<entry.getValue()){
				max=entry.getValue();
				odp=entry.getKey();
			}
		}
		
		return odp;
	
	}
	
	private static double roundToDecimal(double num, int dec) {
	    int multi = (int) Math.pow(10, dec);
	    int temp = (int) Math.round(num * multi);
	    return (double) temp / multi;
	}
}

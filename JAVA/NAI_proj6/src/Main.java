import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static int maxSIZE;
	static int maxCAP;
	public static void main(String[] args) throws FileNotFoundException {
		
		ArrayList<ArrayList<Paczka>> dane=getDataFrom(new File(System.getProperty("user.home")+"/Desktop"+"/Nai/plecak.txt"));
		//czy wszystkie dane?
//		for(ArrayList<Paczka>xd:dane){
//			System.out.println("Paczka: ");
//			for(Paczka dx:xd)
//				System.out.println("\t"+dx);
//		}
		
		int co=(int)6;
		System.out.println("Numer zestawu: "+(co+1));//bo od 1 chcemy a zaczynamy od 0
		computeBackpack(dane.get(co));
	}
	private static void computeBackpack(ArrayList<Paczka> arrayList) {
		
		//max do ktorego bede porownywal 
		ArrayList<Paczka>forMax = null;
		double forMaxPojemn=0;
		double forMaxWart=0;
		
		//pomiar czasu - start
		long timeStart=System.currentTimeMillis();
		
		//ile obrotow Math.pow(2, maxSIZE)
		for(Integer i=1;i<Math.pow(2, maxSIZE);i++){
			//lokalne wartosci
			double currCapacity=0,currVal=0;
			ArrayList<Paczka>tmpPrzedmioty=new ArrayList<>();
			
			//tworzenie z liczby obrotu -> bity i iterowanie po niej(tablicy char)
			String binary=Integer.toBinaryString(i);
			for(int liter=0;liter<binary.length();liter++){
				char c=binary.charAt(binary.length()-1-liter);
				if(c=='1'){
					currCapacity+=arrayList.get(liter).size;
					if(currCapacity>maxCAP){
						break;
					}
					currVal+=arrayList.get(liter).value;
					tmpPrzedmioty.add(arrayList.get(liter));
				}
			}
			if(currCapacity<=maxCAP){
				if(currVal>forMaxWart){
					forMaxWart=currVal;
					forMaxPojemn=currCapacity;
					forMax=tmpPrzedmioty;
					//System.out.println("\nNOWE WART: ");
					//System.out.println("Calk WART: "+forMaxWart);
					//System.out.println("Calk POJEM: "+forMaxPojemn);
				}
			}
		}
		long finish=System.currentTimeMillis();
		System.out.println("\nODPOWIEDZi!");
		for(Paczka tak:forMax)
			System.out.println(tak);
		System.out.println("Calk WART: "+forMaxWart);
		System.out.println("Calk POJEM: "+forMaxPojemn);
		System.out.println("Czas: "+(finish-timeStart));
	}
	private static ArrayList<ArrayList<Paczka>>getDataFrom(File fil) throws FileNotFoundException{
		Scanner scan=new Scanner(fil);
		ArrayList<ArrayList<Paczka>> odpowiedz=new ArrayList<>();
		
		String inputOptions=scan.nextLine();
		inputOptions=inputOptions.replaceAll("\\D+", " ");
		maxSIZE=Integer.parseInt(inputOptions.split(" ")[1]);
		maxCAP=Integer.parseInt(inputOptions.split(" ")[2]);
		
		for(String line="";scan.hasNextLine();line=scan.nextLine())
			if(line.contains("sizes")){
				ArrayList<Paczka> tmpList=new ArrayList<>();
				String[] line1=line.replaceAll("\\D+", " ").split(" ");
				String[] line2=scan.nextLine().replaceAll("\\D+", " ").split(" ");
				
				for(int i=0;i<maxSIZE;i++){
					Paczka tmp=new Paczka();
					tmp.nrPrzedmiotu=i+1;
					tmp.size=Integer.parseInt(line1[i+1]);
					tmp.value=Integer.parseInt(line2[i+1]);
					tmpList.add(tmp);
				}
				odpowiedz.add(tmpList);
			}
		scan.close();
		return odpowiedz;
	}
}


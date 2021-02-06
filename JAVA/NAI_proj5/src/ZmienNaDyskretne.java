import java.util.ArrayList;

public class ZmienNaDyskretne {

	ArrayList<double[]>przedzialy;
	int liczbPrzedzialow;
	public ZmienNaDyskretne(int ile) {
		liczbPrzedzialow=ile;
		przedzialy=new ArrayList<>();
	}
	
	public ArrayList<Paczka> convertAndSet(ArrayList<Paczka> trening) {

		double[]max=new double[trening.get(0).soldierPosition.size()];
		double[]min=new double[max.length];
		
		for(Paczka xd:trening){
			for(int i=0;i<xd.soldierPosition.size();i++){
				if(max[i]==0 || max[i]<xd.soldierPosition.get(i))
					max[i]=xd.soldierPosition.get(i);
				
				if(min[i]==0 || min[i]>xd.soldierPosition.get(i))
					min[i]=xd.soldierPosition.get(i);
			}
		}
		
		for(int i=0;i<max.length;i++){
			double xd=max[i]-min[i];
			xd=xd/liczbPrzedzialow;
			double[]tmp=new double[liczbPrzedzialow];
			for(int j=0;j<liczbPrzedzialow;j++){
				tmp[j]=roundToDecimal((min[i]+(j*xd)), 2);
				
			}
			przedzialy.add(tmp);
		}
		for(double[] X:przedzialy){
			for(double D:X){
				System.out.print(D+" ");
			}
			System.out.println();
		}
		
		return convert(trening);
	}
	public ArrayList<Paczka> convert(ArrayList<Paczka> trening) {
		ArrayList<Paczka> result=new ArrayList<>();
		for(int i=0;i<trening.size();i++){
			Paczka tmp=new Paczka();
			tmp.nameInPaczka=trening.get(i).nameInPaczka;
			for(int j=0;j<trening.get(i).soldierPosition.size();j++){
				double liczba=trening.get(i).soldierPosition.get(j);
				for(int k=0;k<liczbPrzedzialow;k++){
					if(liczba>=przedzialy.get(j)[k]){
						if(k==(liczbPrzedzialow-1)){
							tmp.soldierPosition.add((k+1)*1.0);
							break;
						}
						continue;
					}else{
						tmp.soldierPosition.add(k*1.0);
						break;
					}
				}
			}
			result.add(tmp);
		}
		return result;
	}
	private static double roundToDecimal(double num, int dec) {
	    int multi = (int) Math.pow(10, dec);
	    int temp = (int) Math.round(num * multi);
	    return (double) temp / multi;
	}
}

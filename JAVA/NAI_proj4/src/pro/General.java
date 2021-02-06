package pro;

import java.util.ArrayList;
import java.util.HashMap;

public class General {
	String name;

	public General(char n) {
		name=n+"";
	}
	ArrayList<Paczka> soldiers=new ArrayList<>();
	ArrayList<Double>generalPosition=new ArrayList<>();
	
	public ArrayList<Double> setGenPosition(){
		generalPosition.clear();
		if(soldiers.size()<1)
			return null;
		for(int i=0;i<soldiers.get(0).soldierPosition.size();i++){
			double tmp=0;
			for(int j=0;j<soldiers.size();j++){
				if(soldiers.get(j).soldierPosition.get(i)!=null)
				tmp+=soldiers.get(j).soldierPosition.get(i);
			}
			tmp=tmp/soldiers.size();
			generalPosition.add(tmp);
		}
		return generalPosition;
	}
	
	public void sumSquareDistance(){
		double res=0;
		for(Paczka xd:soldiers){
			res+=computeDistanceSquare(generalPosition, xd.soldierPosition);
		}
		System.out.println(name+"|\t"+res);
	}
	
	private Double computeDistanceSquare(ArrayList<Double> generalPosition, ArrayList<Double> soldierPosition){
		if(generalPosition.size()!=soldierPosition.size())
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		double result=0;
		for(int i=0;i<generalPosition.size();i++){
			result+=Math.pow(generalPosition.get(i)-soldierPosition.get(i),2);
		}
		return Math.sqrt(result);
	}
	
	public void getEntropy(){
		HashMap<String,Integer>howManyOfEach=new HashMap<>(); 
		for(Paczka sold:soldiers){
			if(howManyOfEach.containsKey(sold.nameInPaczka)){
				howManyOfEach.replace(sold.nameInPaczka, howManyOfEach.get(sold.nameInPaczka)+1);
			}else
				howManyOfEach.put(sold.nameInPaczka, 1);
		}
		
		//lambda nie lubi zmiennych dlatego jest to sposob na ich obejscie (tablica czegosc)
		double[] res=new double[1];
		res[0]=0;
		int sum[] = new int[1];
		for (int val : howManyOfEach.values()){
		    sum[0]+= val;
		}
		String[] sold=new String[1];
		sold[0]="";
		
		howManyOfEach.forEach((k,v)->{
			sold[0]+=k+" "+v+" | ";
			double tmpRes=v;
			tmpRes=tmpRes/sum[0];
			res[0]+=tmpRes*(Math.log10(tmpRes)/Math.log10(2));
		});
		//System.out.println(name+"|\tENTROPHY: "+(-res[0])+"\t"+soldiers);
		System.out.println(name+"|\tENTROPHY: "+(-res[0])+"\t"+sold[0]);
	}
}

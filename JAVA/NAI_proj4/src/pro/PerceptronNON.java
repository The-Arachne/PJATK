package pro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.JOptionPane;

public class PerceptronNON {
	
	
	ArrayList<General> generals;
	public PerceptronNON() {
		generals=new ArrayList<>();
	}
	
	void computeAllData(List<Paczka> list){
		boolean changed=true;
		int k=-1;
		while(k<1){
			try{
				String input=JOptionPane.showInputDialog("daj k wieksze od 0");
				k=Integer.parseInt(input);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Cos jest nie tak z liczba");
			}
		}
		//k "generals"
		for(int i=0;i<k;i++)
			generals.add(new General((char)('A'+i)));
		
		//initiate- Each to different "general"
		for(int i=0;i<list.size();i++)
			generals.get(i%k).soldiers.add(list.get(i));
		
		//where is general?
		for(General tak:generals){
			tak.setGenPosition();
			tak.sumSquareDistance();
		}
			
		
		while(changed){
			System.out.println("///////////////////////////////");
			changed=false;
			
			//1 compute centre 4 gen
			for(General gr:generals)
				gr.setGenPosition();
			
			//2 compute distances & transfer between generals
			for(General gen:generals){
				HashSet<Paczka>toRemove=new HashSet<>();
				for(int i=0;i<gen.soldiers.size();i++){
					Double distance=null;
					General nearestGen=null;

					//for each soldier compute distance to every available general
					for(General everyGeneral:generals){
						Double tmpDistance=computeDistance(everyGeneral.generalPosition,gen.soldiers.get(i).soldierPosition);
						if(distance==null ||(tmpDistance!=null && tmpDistance<distance)){
							distance=tmpDistance;
							nearestGen=everyGeneral;
						}
					}
					//and transfer him to nearest general
					if(!nearestGen.equals(gen)){
						changed=true;
						nearestGen.soldiers.add(gen.soldiers.get(i));
						toRemove.add(gen.soldiers.get(i));
					}
				}
				gen.soldiers.removeAll(toRemove);
			}
			for(General tak:generals)
				tak.sumSquareDistance();
		}
		System.out.println("ENTROPHY");
		for(General tak:generals)
			tak.getEntropy();
	}
	
	private Double computeDistance(ArrayList<Double> generalPosition, ArrayList<Double> list){
		if(generalPosition.size()!=list.size())
			return null;
		
		double result=0;
		for(int i=0;i<generalPosition.size();i++){
			result+=Math.abs(generalPosition.get(i)-list.get(i));
		}
		return result;
	}
}

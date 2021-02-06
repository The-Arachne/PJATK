import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;

public class MachinaGameOfLife {
  GUI gui;
  ArrayList<Integer>tak;
  ArrayList<Integer>nie;
  
  MachinaGameOfLife(GUI gui) {
    tak=new ArrayList<>();
    nie=new ArrayList<>();
    tak.addAll(gui.getZyjace());
    nie.addAll(gui.getNiezyjace());
  }
  private boolean[][] initialCompute(boolean[][] currMatryca) {
	  boolean[][] nextMatryca=new boolean[currMatryca.length][currMatryca[0].length];
	  
	  for (int row = 0; row <= currMatryca.length; row++) {
      for (int col = 0; col <= currMatryca[0].length; col++) {
        int numberOfNeighbors = getNumberOfNeighbors(row, col);
        if (currMatryca[row][col]) {
        	nextMatryca[row][col] = false;
        	for(Integer xd:tak)
        		if(xd==numberOfNeighbors){
        			nextMatryca[row][col] = true;
            		break;
        		}
        } else {
        	nextMatryca[row][col] = false;
        	for(Integer xd:nie)
        		if(xd==numberOfNeighbors){
        			nextMatryca[row][col] = true;
            		break;
        		}
        }
      }
    }
	  return nextMatryca;
  }

  private int getNumberOfNeighbors(int row, int col,boolean[][] matryca) {
    int Count = 0;
    for (int pion=-1;pion<=1;pion++) {
    	if(col+pion<0 || col+pion>=matryca.length)
    		continue;
	    for (int poziom=-1;poziom <=1;poziom++) {
	    	if ((pion==0)&&(poziom==0))
	    		continue;
	    	if(row+poziom<0 || row+poziom>=matryca[0].length)
	    		continue;
	    	
	    	if(matryca[col+pion][row+poziom])
	    		Count++;
	    }
    }
    return Count;
  }
	public boolean[][] przelicz(boolean[][] matryca) {

		
		
		
		
		
		
		
		
		
		return null;
	}
}
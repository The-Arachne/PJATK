package pac;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		int n=5;
		int tab[][]=new int[n][n];
		
		for(int i=1;i<n-1;i++){
			tab[0][i]=200;
			tab[n-1][i]=150;
			tab[i][0]=100;
			tab[i][n-1]=50;
		}
		
		
		
		
		PrintWriter pisak = new PrintWriter(".\\odp.txt");
		ArrayList<int[]>list=new ArrayList<>();
		for(int y=0;y<n-2;y++)
			for(int x=1;x<n-1;x++){
				//tab[n-2-y][x]=x+y*(n-2);
				int idx=x+y*(n-2);
				int[]tmp=new int[(n-2)*(n-2)];
				int yG=(x+(y+1)*(n-2)<n-1)?x+(y+1)*(n-2):-1,
					yD=(x+(y-1)*(n-2)>0)?x+(y-1)*(n-2):-1;
				System.out.println("dla: "+idx+" G:"+yG+" D:"+yD);	
			}
		wypisz(tab);
		
	}
	static void wypisz(int[][]xd){
		for(int i=0;i<xd.length;i++){
			for(int j=0;j<xd[i].length;j++){
				System.out.print(xd[i][j]+" | ");
			}
			System.out.println();
		}
	}

}

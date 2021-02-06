package pac1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class Main {

	public static void main(String[] args)  {
		
		DecimalFormat df=new DecimalFormat("##.#######");
		PrintWriter pisakDoY = null;
		PrintWriter pisakDoEnergii=null;
		try {
			pisakDoY = new PrintWriter(".\\sebusPSM_x_WieleY.txt");
			pisakDoEnergii = new PrintWriter(".\\sebusPSM_energia.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		double dt=0.2;
		int n=10;
		double L=Math.PI;
		double dx=L/n;
		double[] x=new double[n];
		pisakDoY.append("x:\n");
		for(int i=0;i<x.length;i++){
			x[i]=(i==0)?0:x[i-1]+dx;
			pisakDoY.append(df.format(x[i]).replace(".", ",")+" ");
			
		}
		pisakDoY.append(df.format(L)+" \ny:\n");
		
		
		double[] y=new double[x.length+1];
		double[]v=new double[x.length];
		double[]v_2=new double[x.length];
		double[]a=new double[x.length];
		double[]a_2=new double[x.length];
		double[]y_2=new double[x.length+1];
		System.out.println("y przed petla:");
		for(int i=0;i<y.length-1;i++){
			y[i]=(i==0)?0:Math.sin(x[i]);
			
			System.out.print(df.format(y[i])+" ");
			pisakDoY.append(df.format(y[i]).replace(".", ",")+" ");
		}
		pisakDoY.append(" 0,0\n");
		pisakDoY.flush();
		System.out.println();
		System.out.println("a przed petla:");
		for(int i=1;i<a.length;i++){
			a[i]=(y[i-1]-2*y[i]+y[i+1])/(dx*dx);
			System.out.print(df.format(a[i])+ " ");
		}System.out.println();
		
		for(int iter=1;iter<20;iter++){
			double eP=0,eK=0;
			
			
			System.out.println("\nY_2["+iter+"]: ");		//Y(t0+t/2)
			for(int i=1;i<y_2.length-1;i++){				
				y_2[i]=y[i]+(v[i]*(dt/2));
				System.out.print(df.format(y_2[i])+ " ");
			}
			System.out.println("\na_2["+iter+"]: ");		//a(t0+t/2)
			for(int i=1;i<a_2.length;i++){
				a_2[i]=(y_2[i-1]-2*y_2[i]+y_2[i+1])/(dx*dx);
				System.out.print(df.format(a_2[i])+ " ");
			}
			
			System.out.println("\nV_2["+iter+"]: ");		//V(t0+t/2)
			for(int i=1;i<v_2.length;i++){
				v_2[i]=v[i]+(a[i]*(dt/2));
				System.out.print(df.format(v_2[i])+ " ");
			}
			
			System.out.println("\nY["+iter+"]: ");			//Y(t0+t)
			for(int i=0;i<y.length-1;i++){
				y[i]=y[i]+(v_2[i]*dt);
				System.out.print(df.format(y[i])+ " ");
				pisakDoY.append(df.format(y[i]).replace(".", ",")+" ");
			}
			pisakDoY.append("0\n");
			System.out.println("\nV["+iter+"]: ");//wstaw potem
			for(int i=1;i<v.length;i++){
				v[i]=v[i]+(a_2[i]*dt);
				System.out.print(df.format(v[i])+ " ");
			}
			for(int i=1;i<a.length;i++){
				a[i]=(y[i-1]-2*y[i]+y[i+1])/(dx*dx);
				System.out.print(df.format(a[i])+ " ");
			}
			
			
			for(int i=0;i<y.length-1;i++){
				eK+=dx*(v[i]*v[i])/2;
				eP+=Math.pow(y[i+1]-y[i],2)/(2*dx);
			}
			System.out.println("\nEC: "+(eP+eK)+"\n");
			pisakDoEnergii.append(df.format(eK).replace(".", ",") + " "+df.format(eP).replace(".", ",")+" "+df.format(eK+eP).replace(".", ",")+"\n");
			pisakDoEnergii.flush();
			pisakDoY.flush();
			System.out.println("\n////////////////////////////////////////////");
		}
		
			
		
		
			
	}

}

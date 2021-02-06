import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
public class Main {

	 public static void main(String[] args) {
		 Lsys x=new Lsys();
		 x.setResizable(false);
		 x.setVisible(true);
	 }
}
class Lsys extends JFrame{
	
	List<Point> list;
	public Lsys() {
		super("Tree or bush");
		
		list=new ArrayList<>();
        setBounds(10, 10, 1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(20,120,30));
		int levels=7;
        try {
			xompute(g, 500, 600, -100, levels);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void xompute(Graphics g, int x, int y, double angle, int levels) throws Exception {
		int curX=x,curY=y;
		double curAngle=angle;
		
		String tekst="X";
		for(int i=levels;i>0;i--){
			System.out.println("iteracja: "+i);
			
			char[]tab=tekst.toCharArray();
			tekst="";
			for(int ch=0;ch<tab.length;ch++){
				char xd=tab[ch];
				//System.out.print("("+xd+")");
				switch(xd){
					case 'X':
						tekst+="F+[X-X]-F[-FX]+X";
						break;
					case 'F':
						
						tekst+="FF";
						if(i==1){
							//System.out.println("RYSI");
							//dla parzystych (ost liczba przy mnozeniu) rysunek sie rozwala

							int x2 = curX + (int) (Math.cos(Math.toRadians(curAngle)) * 5.0);
					        int y2 = curY + (int) (Math.sin(Math.toRadians(curAngle)) * 4.0);
					        g.drawLine(curX, curY, x2, y2);
					        //System.out.println("cur: "+curX+" "+curY+" ang: "+curAngle+" nast:"+x2+" "+y2);
					        curX=x2;
					        curY=y2;
						}
						
						//tekst+="FF";
						break;
					case '-':
						tekst+="-";
						curAngle-=25;
						break;
					case '+':
						tekst+="+";
						curAngle+=25;
						break;
					case '[':
						tekst+="[";
						list.add(new Point(curX, curY, curAngle));
						break;
					case ']':
						tekst+="]";
						Point tmp=list.remove(list.size()-1);
						curAngle=tmp.angle;
						curX=tmp.x;
						curY=tmp.y;
						break;
					default:
						throw new Exception("NIE MA TAKIEGO ZNAKU!");
				}
			}
			System.out.println();
			for(char xd:tab)
				System.out.print(xd);
			System.out.println();
			System.out.println(tekst);
			
		}
		//g.drawLine(x, y, x, y-100);
		
	}
}
class Point{
	public Point(int xd,int yd,double ad) {
		x=xd;
		y=yd;
		angle=ad;
	}
	int x;
	int y;
	double angle;
}
package tesst;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		String text=JOptionPane.showInputDialog("Daj STRINGi");
		
		//get number of every char in ascending order
		CharCounter cc=new CharCounter();
		List<Entry<Character, Integer>> list=cc.build(text);
		
		//test
		System.out.println(list);
		
		//build huffman tree
		Huffman huff=new Huffman();
		huff.build(list);
		
		//print result
		ArrayList<PaczkoDpowiedz>xd=huff.getData();
		Collections.sort(xd,new Comparator<PaczkoDpowiedz>() {

			@Override
			public int compare(PaczkoDpowiedz o1, PaczkoDpowiedz o2) {
				System.out.println(o1.character+" ? "+o2.character);
				return (o1.character<=o2.character)?1:0;
			}
		});
		System.out.println("Dla: "+text);
		xd.stream().forEach(e->System.out.println(e));
		System.out.println();
		
		HashMap<Character,String>ff=new HashMap<>();
		xd.stream().forEach(e->ff.put(e.character, e.lrCombination));
		text=text.toUpperCase().replaceAll(" ", "_");

		String odp="";
		for(char hc:text.toCharArray()) {
			odp+=ff.get(hc);
		}
		System.out.println("Zakodowany ciag: "+odp);
		System.out.println("Size: "+odp.length());
	}

}

package tesst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

public class Huffman {

//mr. node 
class Node implements Comparable<Node>{
	public Node(int sum,String letters,Node l,Node r) {
		this.sum=sum;
		this.letters=letters;
		left=l;
		right=r;
	}
	
	int sum;
	String letters="";
	Node left=null;
	Node right=null;
	
	@Override
	public int compareTo(Node o) {
		return sum-o.sum;
	}
	@Override
	public String toString() {
		return (left!=null)+" <- [ "+letters+" / "+sum+" ] -> "+(right!=null);
	}
	
}
	
	ArrayList<PaczkoDpowiedz>res=new ArrayList<PaczkoDpowiedz>();
	public void build(List<Entry<Character, Integer>> list) {

		//	Iterator<Entry<Character, Integer>> iter=list.iterator();
		//	List<Entry<Character, Integer>>tmp=list;
		ArrayList<Node> nods=new ArrayList<Huffman.Node>();
		for(int i=0;i<list.size();i++) {
			nods.add(new Node(list.get(i).getValue(), list.get(i).getKey().toString().contains(" ")?"_":list.get(i).getKey()+"", null, null));
		}
		//nods.stream().forEach(e->System.out.println(e));
		System.out.println("////////////////////////////");
		
		ArrayList<Node> tmp=nods;
		while(tmp.size()>1) {
			System.out.println(tmp.size());
			tmp.stream().forEach(e->System.out.println(e));
			Node l=tmp.remove(0),p=tmp.remove(0),nowyNode=null;
			if(l.sum==p.sum) {
				for(int i=0;i<l.letters.length() &&i<p.letters.length();i++) {
					if(l.letters.charAt(i)<p.letters.charAt(i)) {
						nowyNode=new Node(l.sum+p.sum, l.letters+p.letters, l, p);
						break;
					}else if(l.letters.charAt(i)>p.letters.charAt(i)) {
						nowyNode=new Node(l.sum+p.sum, p.letters+l.letters, p, l);
						break;
					}
				}
			}else {
				nowyNode=new Node(l.sum+p.sum, l.letters+p.letters, l, p);
			}
				
			
			tmp.add(nowyNode);
			Collections.sort(tmp);
		}
		System.out.println("1");
		tmp.stream().forEach(e->System.out.println(e));
		System.out.println("////////////////////////////");
		
		wypisz(tmp.remove(0),"");
	}
	private void wypisz(Node td,String fix) {
		if(td!=null) {
			wypisz(td.left, fix+"0");
			wypisz(td.right, fix+"1");
			if(td.left==null && td.right==null)
				res.add(new PaczkoDpowiedz(td.letters.charAt(0),fix));
		}
	}
	public ArrayList<PaczkoDpowiedz> getData(){
		return res;
	}
}

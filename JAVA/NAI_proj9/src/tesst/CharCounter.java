package tesst;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
public class CharCounter {

	public List<Entry<Character, Integer>> build(String text) {
		text=text.toUpperCase();
		HashMap<Character, Integer>map=new HashMap<>();

		//counter every char in map ( char , int(how many of chars) )
		for(char ch:text.toCharArray()) {
			if(!map.containsKey(ch))
				map.put(ch, 1);
			else
				map.replace(ch, map.get(ch)+1);
		}
	
		//get sorted to list by stream
		List<Map.Entry<Character, Integer>> result=
				map.entrySet()
				.stream()
				.sorted((Map.Entry.comparingByValue()))
				.collect(Collectors.toList());
		 return result;
	}

}

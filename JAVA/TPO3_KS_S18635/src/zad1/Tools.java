/**
 *
 *  @author Kachniarz Sebastian S18635
 *
 */

package zad1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.yaml.snakeyaml.*;

public class Tools {

	public static Options createOptionsFromYaml(String fileName) throws FileNotFoundException {
		Scanner scan=new Scanner(new File(fileName));
		String txt="";
		while(scan.hasNext())
			txt+=scan.nextLine()+"\n";
		scan.close();
		
		Yaml y=new Yaml();
		Map<String,Object>ymap=(Map<String,Object>)y.load(txt);
		
		String host=ymap.get("host").toString();
		int port=Integer.parseInt(ymap.get("port").toString());
		Boolean concurMode=ymap.get("concurMode").toString().equals("true");
		Boolean showSendRes=ymap.get("showSendRes").toString().equals("true");
		Map<String, List<String>> clientsMap=(Map<String, List<String>>) ymap.get("clientsMap"); 
		
		return new Options(host, port, concurMode, showSendRes, clientsMap);
	}
}

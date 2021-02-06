/**
 *
 *  @author Kachniarz Sebastian S18635
 *
 */

package zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Service {

	
	private final String apiID="c49567436f47e1a8cbe3aaa612a8d08a";
	private String country;
	Locale con;
	
	public Service(String string) {
		country=string;
		con=new Locale("", country);
	}
	
	public String getWeather(String string) {
		URL url;
		try {
			url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+string+","+getCountryCode()+"&appid="+apiID+"&units=metric&mode=json");
			 String json = "";
			    try (BufferedReader in = new BufferedReader(
			                 new InputStreamReader(url.openStream(), "UTF-8"))) {
			      String line;
			      while((line = in.readLine()) != null) json +=line;
			    } catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    //System.out.println(json);
				return json;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return null;
	}

	private String getCountryCode() {
		Locale.setDefault(Locale.ENGLISH);
		Map<String,String>tak=new TreeMap<>();
		for(String iso:Locale.getISOCountries()){
			try {
				Locale l= new Locale("",iso);
				tak.put(l.getDisplayCountry(),iso);
			} catch (Exception e) {
				//System.out.println("cannot get currency");
			}
		}
		return tak.get(country);
	}

	public Double getRateFor(String string) {
		URL url;
		try {
			
			
			
			
			
			String json="";
			url = new URL("https://api.exchangeratesapi.io/latest?base="+string+"&symbols="+getCode());
			    try (BufferedReader in = new BufferedReader(
			                 new InputStreamReader(url.openStream(), "UTF-8"))) {
			      String line;
			      while((line = in.readLine()) != null) json +=line;
			      json=json.replaceAll(":", " ");
			      json=json.replaceAll("}", " ");
			      Scanner scan=new Scanner(json);
			      scan.next();
			      scan.next();
			      return Double.parseDouble(scan.next());
			    } catch (UnsupportedEncodingException e) {
				
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    //System.out.println(json);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return null;
		
	}

	public Double getNBPRate() {
		URL url;
		try {
			String json="";
			url = new URL("https://api.exchangeratesapi.io/latest?base="+getCode()+"&symbols=PLN");
			    try (BufferedReader in = new BufferedReader(
			                 new InputStreamReader(url.openStream(), "UTF-8"))) {
			      String line;
			      while((line = in.readLine()) != null) json +=line;
			      json=json.replaceAll(":", " ");
			      json=json.replaceAll("}", " ");
			      Scanner scan=new Scanner(json);
			      scan.next();
			      scan.next();
			      return Double.parseDouble(scan.next());
			    } catch (UnsupportedEncodingException e) {
				
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			    //System.out.println(json);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	   return null;
	}
	public String getCode(){
		Locale.setDefault(Locale.ENGLISH);
		Locale[]locales = Locale.getAvailableLocales();
		Map<String,String>currencies=new TreeMap<>();
		for(Locale loc:locales){
			try {
				currencies.put(loc.getDisplayCountry(), Currency.getInstance(loc).getCurrencyCode());
				//System.out.println(loc.getDisplayCountry()+" "+Currency.getInstance(loc).getCurrencyCode());
			} catch (Exception e) {
				//System.out.println("cannot get currency");
			}
		}
		return currencies.get(country);
	}
}  

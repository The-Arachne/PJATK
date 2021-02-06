/**
 *
 *  @author Kachniarz Sebastian S18635
 *
 */

package zad1;

import java.text.DecimalFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Time {

	public static String passed(String dataFrom, String dataTo) {
		LocalDate timeFrom;
		LocalDate timeTo;
		String dpatt="d MMMM yyyy (EEEE)";
		String tpatt="d MMMM yyyy (EEEE) 'godz.' HH:mm";
		String odp="",dodLinijka="";
		Locale PL=new Locale("pl","PL");
		try{
			if(dataTo.contains("T")){
				LocalDateTime tmpFrom=LocalDateTime.parse(dataFrom);
				LocalDateTime tmpTo=LocalDateTime.parse(dataTo);
				timeFrom=tmpFrom.toLocalDate();
				timeTo=tmpTo.toLocalDate();
				ZonedDateTime zdt1=ZonedDateTime.of(tmpFrom, ZoneId.of("Europe/Warsaw"));
				ZonedDateTime zdt2=ZonedDateTime.of(tmpTo, ZoneId.of("Europe/Warsaw"));
				
				odp= "Od "+tmpFrom.format(DateTimeFormatter.ofPattern(tpatt,PL))+" do "+tmpTo.format(DateTimeFormatter.ofPattern(tpatt,PL));
				dodLinijka="\n - godzin: "+ChronoUnit.HOURS.between(zdt1, zdt2)+", minut: "+ChronoUnit.MINUTES.between(zdt1, zdt2); 
			}else{
				timeFrom=LocalDate.parse(dataFrom);
				timeTo=LocalDate.parse(dataTo);
				 odp= "Od "+timeFrom.format(DateTimeFormatter.ofPattern(dpatt,PL))+" do "+timeTo.format(DateTimeFormatter.ofPattern(dpatt,PL));
			}
			
			java.text.DecimalFormat tak=new DecimalFormat();
			tak.setMaximumFractionDigits(2);
			int dni=(int)ChronoUnit.DAYS.between(timeFrom, timeTo);
			odp+="\n - mija: "+dni+" "+((dni==1)?"dzień":"dni")+", tygodni "+tak.format((dni/7.00)).replace(",", ".");
			odp+=dodLinijka;
			Period p=Period.between(timeFrom, timeTo);
			int days=p.getDays(),months=p.getMonths(),years=p.getYears();
			String tmpYear=years+"";
				   tmpYear+=(years==1)? " rok" : (years < 5 || (years > 21 && years % 10 > 1 && years % 10 < 5) ? " lata" : " lat");
			String tmpMonth=months+"";
				   tmpMonth+=(months==1) ? " miesiąc" : (months < 5 || (months > 21 && months % 10 > 1 && months % 10 < 5) ? " miesiące" : " miesięcy");
			String tmpDays=days+"";
				   tmpDays+=(days==1)?" dzień" : " dni";
			odp+="\n - kalendarzowo: ";
			if(years>0)
				odp+=tmpYear;
			if(months>0){
				if(years>0)
					odp+=", ";
				odp+=tmpMonth;
			}
			if(days>0){
				if(years>0 || months>0)
					odp+=", ";
				odp+=tmpDays;
			}
				
			
			
			
			
			return odp;
			
		}catch(DateTimeParseException e){
			return "*** java.time.format.DateTimeParseException: "+e.getMessage();
		}
	}
}

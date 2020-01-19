package de.automat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import de.automat.utils.Utils;

public class ParkAutomat {

	public static void main(String args[]) throws IOException{	
	//Calendar cal = Calendar.getInstance();
	
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    System.out.print("Bitte die Oeffnungszeit eingeben [hh:mm - hh:mm] : \n");
    String oeffnungszeit = reader.readLine();
    int hh1 = Integer.parseInt(oeffnungszeit.substring(0,2));
	int mm1 = Integer.parseInt(oeffnungszeit.substring(3,5));
    int hh2 = Integer.parseInt(oeffnungszeit.substring(8,10));
	int mm2 = Integer.parseInt(oeffnungszeit.substring(11,13));
	
    System.out.print("Bitte das Systemdatum eingeben [dd.mm.yyyy] : \n");
	String datum = reader.readLine();
	int jahr = Integer.parseInt(datum.substring(6,10));
	int monat = Integer.parseInt(datum.substring(3,5));
	int tag = Integer.parseInt(datum.substring(0,2));
	
	System.out.print("Bitte die Einfahrzeit eingeben [hh:mm] : \n");
	String einfahrzeit = reader.readLine();
	int hh = Integer.parseInt(einfahrzeit.substring(0,2));
	int mm = Integer.parseInt(einfahrzeit.substring(3,5));
    Calendar cal = new GregorianCalendar(jahr,monat-1,tag,hh,mm);
    cal.setTimeZone(TimeZone.getDefault());
    System.out.println("Datum: " + Utils.getDate(cal));
    
    if(Utils.liegtInDerOeffnungszeit(hh, mm, hh1, mm1, hh2, mm2)) {
    	System.out.println("EinfahrtsZeit: " + Utils.getTime(cal) + " Uhr");
    }else {
    	System.out.println("Sie koennen nicht momentan parken, bitte kommen Sie in der Oeffnungszeit");
    	System.exit(0);
    }
    	
    int stunde = (int) (Math.random()*24) + 1;
    int minute =  (int) (Math.random()*60) + 1;
    int minuten =  stunde*60 + minute;
    //int minuten =  280;
    //adding minutes into Date time
    cal.add(Calendar.MINUTE, minuten);
    System.out.println("AusfahrtsZeit: " + Utils.getTime(cal) + " Uhr");
    
    System.out.println("ParkZeit: " + stunde + ":" + minute + " h");
    
    double preis = 0;
    while(minuten > 0) {
    	if(minuten == 1440) {
    		preis += 9;
    	}
    	
    	if(minuten > 180 && minuten < 1440) {
    		minuten -= 60;
    		preis += 1;
    	}
    	
    	if(minuten > 120 && minuten <= 180) {
    		minuten -= 60;
    		preis += 1.3;
    	}
    	
    	if(minuten > 60 && minuten <= 120) {
    		minuten -= 60;
    		preis += 1.3;
    	}
    	
    	if(minuten <= 60) {
    		minuten -= 120;
    		preis += 1.5;
    	}
    }
    
    System.out.println("Parkgebühr: " + preis + " €");
    
    double gezahlt = 0;
    int g10 = 0;
    int g5 = 0;
    int m2 = 0;
    int m1 = 0;
    int m50 = 0;
    int m20 = 0;
    int m10 = 0;
    
    while(gezahlt < preis) {
    	System.out.print("Bitte Geldscheine/Muenze eingeben[10e, 5e, 2e, 1e, 50c, 20c, 10c] \n");
    	String mg = reader.readLine();
    	
    	if(mg.equals("10e")) {
    		gezahlt += 10;
    		++g10;
    	}
    	
    	if(mg.equals("5e")) {
    		gezahlt += 5;
    		++g5;
    	}
    	
    	if(mg.equals("2e")) {
    		gezahlt += 2;
    		++m2;
    	}
    	
    	if(mg.equals("1e")) {
    		gezahlt += 1;
    		++m1;
    	}
    	
    	if(mg.equals("50c")) {
    		gezahlt += 0.5;
    		++m50;
    	}
    	
    	if(mg.equals("20c")) {
    		gezahlt += 0.2;
    		++m20;
    	}
    	
    	if(mg.equals("10c")) {
    		gezahlt += 0.1;
    		++m10;
    	}   		
    }
    String strDouble = String.format(Locale.ENGLISH,"%.2f", gezahlt);  
    gezahlt = Double.parseDouble(strDouble);
    
    System.out.println("Gezahlt: "+ gezahlt + " € " + "(10€:"+ g10 + ", 5€:" + g5 + ", 2€:" + m2 + ", 1€:" + m1 + ", 50Ct:" + m50 + ", 20Ct:" + m20 + ", 10Ct:" + m10 + ")");
    
    double rueckgeld = gezahlt - preis;
    double rueckgelda = rueckgeld;
    int m21 = 0;
    int m11 = 0;
    int m501 = 0;
    int m201 = 0;
    int m101 = 0;
    
    while(rueckgeld > 0.1) {
    	if(rueckgeld >= 2) {
    		rueckgeld -= 2;
    		++m21;
    	}
    	
    	if(rueckgeld < 2 && rueckgeld >= 1) {
    		rueckgeld -= 1;
    		++m11;
    	}
    	
    	if(rueckgeld < 1 && rueckgeld >= 0.5) {
    		rueckgeld -= 0.5;
    		++m501;
    	}
    	
    	if(rueckgeld < 0.5 && rueckgeld >= 0.2) {
    		rueckgeld -= 0.2;
    		++m201;
    	}
    	
    	if(rueckgeld < 0.2 && rueckgeld >= 0.1) {
    		rueckgeld -= 0.1;
    		++m101;
    	}
    	
    	if(rueckgeld <= 0.05 && rueckgeld >= 0) {
    		rueckgeld -= 0.1;
    		++m101;
    	}
    }
    
    String strDouble1 = String.format(Locale.ENGLISH,"%.2f", rueckgelda);  
    rueckgelda = Double.parseDouble(strDouble1);
    
    System.out.println("Rueckgeld: "+ rueckgelda + " € " + "(2€:" + m21 + ", 1€:" + m11 + ", 50Ct:" + m501 + ", 20Ct:" + m201 + ", 10Ct:" + m101 + ")");
}

}

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Checker {
	
//Ellenõrzõ metódusok
	
//Ellenõrzi, hogy ki van-e töltve a szövegmezõ	
	
	public boolean filled(JTextField a, String an){
		String s=RTF(a);
		if(s.length()>0) return true;
		else {
			SM("Hiba: a(z) "+an+"mezõ üres!", 0);
			return false;
		}
	}

//Ellenõrzi szám adatot	
	
	public boolean goodInt(JTextField a, String an){
		String s=RTF(a);
		boolean b=filled(a,an);
		if(b) try {
			Integer.parseInt(s);
		}catch (NumberFormatException e) {
			SM("A(z) "+an+"mezõben hibás a számadat!", 0);
			b=false;
		}
		return b;
	}
	
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
	
	public void SM(String msg, int tipus) {
		JOptionPane.showMessageDialog(null,msg,"Programüzenet", tipus);
	}
	
//Megadja a dátum formátumot év, hónap,nap	
	
	public boolean DateFormatChecker(String SDate) {
		SimpleDateFormat RDF= new SimpleDateFormat("yyyy.MM.dd");
		try {
			java.util.Date pdate= RDF.parse(SDate);
			if(!RDF.format(pdate).equals(SDate)) {
				return false;
			}
			return true;
		}catch(ParseException ef) {
			return false;
		}
	}
	
//Ellenõrzi a dátum formátumot év, hónap,nap	
	
	public boolean goodDate(JTextField a, String an) {
		String s=RTF(a);
		boolean b=filled(a,an);
		if(b && DateFormatChecker(s))
			return true;
		else {
			SM("A(z) "+an+" mezõben hibás a dátum!", 0);
			return false;
		}
	}

//Megadja a dátum formátumot óra, perc	
	
	public boolean TimeFormatChecker(String SDate) {
		SimpleDateFormat RDF= new SimpleDateFormat("HH.mm");
		try {
			java.util.Date ptime= RDF.parse(SDate);
			if(!RDF.format(ptime).equals(SDate)) {
				return false;
			}
			return true;
		}catch(ParseException ef) {
			return false;
		}
	}
	
//Ellenõrzi a dátum formátumot óra, perc	
	
	public boolean goodTime(JTextField a, String an) {
		String s=RTF(a);
		boolean b=filled(a,an);
		if(b && TimeFormatChecker(s))
			return true;
		else {
			SM("A(z) "+an+" mezõben hibás az idõpont!", 0);
			return false;
		}
	}
	
//Van-e adat a mezõben	
	
	public boolean filled(JTextField a){
		String s=RTF(a);
		if(s.length()>0) return true;
		else return false;
		}
	
//szöveg számmá alakítása
	
	public int StringToInt(String s) {
		int x=-1;
		try {
			x=Integer.valueOf(s);
		}catch (NumberFormatException nfe) {
			SM("StringToInt: "+nfe.getLocalizedMessage(),0);
		}
		return x;
	}
}

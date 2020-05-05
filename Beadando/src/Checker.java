import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Checker {
	
//Ellen�rz� met�dusok
	
//Ellen�rzi, hogy ki van-e t�ltve a sz�vegmez�	
	
	public boolean filled(JTextField a, String an){
		String s=RTF(a);
		if(s.length()>0) return true;
		else {
			SM("Hiba: a(z) "+an+"mez� �res!", 0);
			return false;
		}
	}

//Ellen�rzi sz�m adatot	
	
	public boolean goodInt(JTextField a, String an){
		String s=RTF(a);
		boolean b=filled(a,an);
		if(b) try {
			Integer.parseInt(s);
		}catch (NumberFormatException e) {
			SM("A(z) "+an+"mez�ben hib�s a sz�madat!", 0);
			b=false;
		}
		return b;
	}
	
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
	
	public void SM(String msg, int tipus) {
		JOptionPane.showMessageDialog(null,msg,"Program�zenet", tipus);
	}
	
//Megadja a d�tum form�tumot �v, h�nap,nap	
	
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
	
//Ellen�rzi a d�tum form�tumot �v, h�nap,nap	
	
	public boolean goodDate(JTextField a, String an) {
		String s=RTF(a);
		boolean b=filled(a,an);
		if(b && DateFormatChecker(s))
			return true;
		else {
			SM("A(z) "+an+" mez�ben hib�s a d�tum!", 0);
			return false;
		}
	}

//Megadja a d�tum form�tumot �ra, perc	
	
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
	
//Ellen�rzi a d�tum form�tumot �ra, perc	
	
	public boolean goodTime(JTextField a, String an) {
		String s=RTF(a);
		boolean b=filled(a,an);
		if(b && TimeFormatChecker(s))
			return true;
		else {
			SM("A(z) "+an+" mez�ben hib�s az id�pont!", 0);
			return false;
		}
	}
	
//Van-e adat a mez�ben	
	
	public boolean filled(JTextField a){
		String s=RTF(a);
		if(s.length()>0) return true;
		else return false;
		}
	
//sz�veg sz�mm� alak�t�sa
	
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

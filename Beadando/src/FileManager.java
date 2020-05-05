import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JOptionPane;

public class FileManager {
	
//met�dus, ami beolvassa a f�jl tartalm�t	
	
	public static RendelesTM CsvReader() {
		Object Rendelestmn[]= {"Jel","Sorsz�m","N�v","Pizza","M�ret","D�tum","Id�pont","�r"};
		RendelesTM rtm= new RendelesTM(Rendelestmn,0);
		try {
			BufferedReader in= new BufferedReader(new FileReader("adatok.csv"));
			String s=in.readLine();
			s=in.readLine();
			while(s!=null) {
				String[] st=s.split(";");
				rtm.addRow(new Object[] {false,st[0],st[1],st[2],st[3],st[4],st[5],st[6]});
				s=in.readLine();
			}
			in.close();
		}catch (IOException ioe) {
			System.out.println("CsvReader: "+ioe.getMessage());
		}
		return rtm;
	}
	
//adatok f�jlba �r�sa
	
	public static void InsertCsv(String sorsz,String nev,String tip,String meret,String date,String ido,String ar) {
		String x=";";
		try{
			PrintStream out= new PrintStream(new FileOutputStream("adatok.csv", true));
		out.println(sorsz+x+nev+x+tip+x+meret+x+date+x+ido+x+ar);
		out.close();
		SM("Adatok ki�rva!", 1);
	}catch (IOException ioe) {
		SM("CsvReader: "+ioe.getMessage(),0);
		}
	}
	
//Program�zenet �r�
	
	public static void SM(String msg, int tipus) {
		JOptionPane.showMessageDialog(null,msg,"Program�zenet", tipus);
	}

//adatok f�jlba �r�sa ellen�rz�ssel	
	
	public static void InsertCsv(RendelesTM rtm) {
		String x=";";
		try{
			PrintStream out= new PrintStream(new FileOutputStream("adatok.csv"));
		out.println("Sorsz�m,N�v,Pizza,M�ret,D�tum,Id�pont,�r");
		for(int i=0; i<rtm.getRowCount();i++) {
			String sorsz=rtm.getValueAt(i,1).toString();
			String nev=rtm.getValueAt(i,2).toString();
			String tip=rtm.getValueAt(i,3).toString();
			String meret=rtm.getValueAt(i,4).toString();
			String date=rtm.getValueAt(i,5).toString();
			String ido=rtm.getValueAt(i,6).toString();
			String ar=rtm.getValueAt(i,7).toString();
			out.println(sorsz+x+nev+x+tip+x+meret+x+date+x+ido+x+ar);
		}
		out.close();
		SM("Adatok ki�rva!", 1);
	}catch (IOException ioe) {
		SM("CsvReader: "+ioe.getMessage(),0);
		}
	}
	
}

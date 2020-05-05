import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DbMethods {
	private java.sql.Statement s=null;
	private Connection conn=null;
	private ResultSet rs=null;

//driver regisztr�ci�	
	
	public void Reg(){
		try {
			Class.forName("org.sqlite.JDBC");
			SM("Sikeres driver regisztr�ci�!", 1);
		}catch (ClassNotFoundException e) {
			SM("Hib�s driver regisztr�ci�!"+e.getMessage(),0);
		}
	}
	public void SM(String msg, int tipus) {
		JOptionPane.showMessageDialog(null,msg,"Program�zenet", tipus);
	}
	
//csatlakoz�s adatb�zishoz	
	
	public void Connect() {
		try {
			String url="jdbc:sqlite:C:/Users/Robi/sqlite/beadandodb";
			conn=DriverManager.getConnection(url);
			SM("Connection OK!", 1);
		}catch (SQLException e) {
			SM("JDBC Connect: "+e.getMessage(),0);
		}
	}
	
//adatb�zis kapcsolat bont�sa	
	
	public void DisConnect() {
		try {
			conn.close();
			SM("DisConnection OK!", 1);
		}catch (SQLException e) {
			SM(e.getMessage(),0);
		}
	}

//t�bl�zat l�trehoz�sa
	
	public RendelesTM ReadData() {
		Object Rendelestmn[]= {"Jel","sorsz","nev","tip","meret","date","ido","ar"};
		RendelesTM rtm= new RendelesTM(Rendelestmn,0);
		String nev="", tip="", date="", ido="";
		int sorsz=0, meret=0, ar=0;
		String sqlp= "select sorsz, nev, tip, meret, date, ido, ar from rendeles";
		try {
			s=conn.createStatement();
			rs=s.executeQuery(sqlp);
			while(rs.next()){
				sorsz=rs.getInt("sorsz");
				nev=rs.getString("nev");
				tip=rs.getString("tip");
				meret=rs.getInt("meret");
				date=rs.getString("date");
				ido=rs.getString("ido");
				ar=rs.getInt("ar");
				rtm.addRow(new Object[] {false, sorsz, nev, tip, meret, date, ido, ar });
			}
		rs.close();
			}
			catch (SQLException e) {
		SM(e.getMessage(),0);
	}
	return rtm;
}
	
//adatbevitel adatb�zisba	
	
	public void InsertData(String sorsz,String nev,String tip,String meret,String date, String ido, String ar) {
		String sqlp= "insert into rendeles values("+sorsz+", '"+nev+"', '"+tip+"', "+meret+",'"+date+"','"+ido+"',"+ar+")";
		try { 
			s=conn.createStatement();
			s.execute(sqlp);
			SM("Insert OK!",1);
		}catch (SQLException e) {
			SM("JDBC Insert: "+ e.getMessage(),0);
		}
	}

//rekord t�rl�se adatb�zisb�l	
	
	public void DelData(String sorsz) {
		String sqlp="delete from rendeles where sorsz="+sorsz;
		try {
			s=conn.createStatement();
			s.execute(sqlp);
			SM("Delete OK!", 1);
		}catch (SQLException e) {
			SM("JDBC Delete: "+e.getMessage(), 0);
		}
	}

//rekord m�dos�t�sa az adatb�zisban	
	
	public void UpdateData(String sorsz,String mnev, String madat) {
		String sqlp= "update rendeles set "+mnev+ "='"+madat+"'where sorsz="+sorsz;
		try {
			s=conn.createStatement();
			s.execute(sqlp);
			SM("Update OK!",1);
		}catch (SQLException e) {
			SM("JDBC Update: "+ e.getMessage(),0);
		}
	}
	
}

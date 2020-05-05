import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RendelesMod extends JDialog {

	private final JPanel contentPanel=new JPanel();
	private JTable table;
	private RendelesTM rtm;
	private Checker c= new Checker();
	private DbMethods dbm= new DbMethods();
	private JTextField sorsz;
	private JTextField nev;
	private JTextField tip;
	private JTextField meret;
	private JTextField date;
	private JTextField ido;
	private JTextField ar;
	
//M�dos�t�s panel	
	
	public RendelesMod(JFrame f, RendelesTM brtm, int abkez) {
		super(f, "Rendel�sek m�dos�t�sa", true);
		rtm=brtm;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton btnBezr = new JButton("Bez\u00E1r");
		btnBezr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnBezr.setBounds(323, 228, 97, 25);
		getContentPane().add(btnBezr);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 432, 179);
		getContentPane().add(scrollPane);
		
//T�bl�zat l�trehoz�sa		
		
		table = new JTable(rtm);
		scrollPane.setViewportView(table);
		
		TableColumn tc=null;
		for(int i=0; i<8; i++)
		{
			tc=table.getColumnModel().getColumn(i);
			if(i==0|i==1) tc.setPreferredWidth(30);
			else if (i==4) tc.setPreferredWidth(150);
			else {
				tc.setPreferredWidth(100);
			}
		}
		table.setAutoCreateRowSorter(true);
		
/*M�dos�t�sgomb esem�nykezel�je ellen�rz�ssel, rekordok kijel�l�s�nek ellen�rz�se,
 * bevitt adat helyess�g�nek ellen�rz�se majd adatok bevitele abkez �llapot�t�l f�gg�en f�jlba vagy adatb�zisba*/
		
		JButton btnModositas = new JButton("M\u00F3dos\u00EDt\u00E1s");
		btnModositas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db=0, jel=0,x=0;
				for(x=0; x<rtm.getRowCount(); x++)
					if((Boolean)rtm.getValueAt(x, 0)) {
						db++;
						jel=x;
					}
				if(db==0) c.SM("Nincs kijel�lve a m�dos�tand� rekord!", 0);
				if(db>1) c.SM("T�bb Rekord van kijel�lve!\nEgyszerre csak egy rekord m�dos�that�!", 0);
				if(db==1 ) {
					if (modDataPc()>0) {
						boolean ok=true;
						if(c.filled(sorsz)) ok=c.goodInt(sorsz, "Sorsz�m");
						if(ok && c.filled(meret)) ok=c.goodInt(meret, "M�ret");
						if(ok && c.filled(ar)) ok=c.goodInt(ar, "�r");
						if(ok) {
							if(abkez==1) {
								String mkod=rtm.getValueAt(jel, 1).toString();
								dbm.Connect();
								if(c.filled(nev)) dbm.UpdateData(mkod,"nev",c.RTF(nev));
								if(c.filled(tip)) dbm.UpdateData(mkod,"tip",c.RTF(tip));
								if(c.filled(meret)) dbm.UpdateData(mkod,"meret",c.RTF(meret));
								if(c.filled(date)) dbm.UpdateData(mkod,"date",c.RTF(date));
								if(c.filled(ido)) dbm.UpdateData(mkod,"ido",c.RTF(ido));
								if(c.filled(ar)) dbm.UpdateData(mkod,"ar",c.RTF(ar));
								if(c.filled(sorsz)) dbm.UpdateData(mkod,"sorsz",c.RTF(sorsz));
								dbm.DisConnect();
								}
							if(c.filled(sorsz)) rtm.setValueAt(c.StringToInt(c.RTF(sorsz)),jel, 1);
							if(c.filled(nev)) rtm.setValueAt(c.RTF(nev),jel, 2);
							if(c.filled(tip)) rtm.setValueAt(c.RTF(tip),jel, 3);
							if(c.filled(meret)) rtm.setValueAt(c.RTF(meret),jel, 4);
							if(c.filled(date)) rtm.setValueAt(c.RTF(date),jel, 5);
							if(c.filled(ido)) rtm.setValueAt(c.RTF(ido),jel, 6);
							if(c.filled(ar)) rtm.setValueAt(c.StringToInt(c.RTF(ar)),jel, 7);
							
							if(abkez==0)FileManager.InsertCsv(rtm);
							
							c.SM("A rekord m�dos�tva!", 1);
							reset(jel);
						}
					}else {
						c.SM("Nincs kit�ltve egyetlen m�dos�t� mez� sem", 0);
					}
					
				}
			}
			
		});
		btnModositas.setBounds(10, 228, 124, 25);
		getContentPane().add(btnModositas);

//Beviteli mez�k		
		
		sorsz = new JTextField();
		sorsz.setBounds(25, 192, 30, 22);
		getContentPane().add(sorsz);
		sorsz.setColumns(10);
		
		nev = new JTextField();
		nev.setBounds(57, 192, 95, 22);
		getContentPane().add(nev);
		nev.setColumns(10);
		
		tip = new JTextField();
		tip.setBounds(154, 192, 69, 22);
		getContentPane().add(tip);
		tip.setColumns(10);
		
		meret = new JTextField();
		meret.setBounds(225, 192, 39, 22);
		getContentPane().add(meret);
		meret.setColumns(10);
		
		date = new JTextField();
		date.setBounds(266, 192, 61, 22);
		getContentPane().add(date);
		date.setColumns(10);
		
		ido = new JTextField();
		ido.setBounds(329, 192, 49, 22);
		getContentPane().add(ido);
		ido.setColumns(10);
		
		ar = new JTextField();
		ar.setBounds(380, 192, 40, 22);
		getContentPane().add(ar);
		ar.setColumns(10);
		TableRowSorter<RendelesTM> trs = (TableRowSorter<RendelesTM>)table.getRowSorter();
		trs.setSortable(0, false);
	}

//m�dos�tott mez�k sz�mol�sa	
	
	public int modDataPc() {
		int pc=0;
		if(c.filled(sorsz)) pc++;
		if(c.filled(nev)) pc++;
		if(c.filled(tip)) pc++;
		if(c.filled(meret)) pc++;
		if(c.filled(date)) pc++;
		if(c.filled(ido)) pc++;
		if(c.filled(ar)) pc++;
		return pc;
	}
	
//mez�k tartalm�nak t�rl�se	
	
	public void reset(int i) {
		sorsz.setText("");
		nev.setText("");
		tip.setText("");
		meret.setText("");
		date.setText("");
		ido.setText("");
		ar.setText("");
		rtm.setValueAt(false, i, 0);
	}
}

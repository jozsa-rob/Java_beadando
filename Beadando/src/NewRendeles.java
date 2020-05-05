import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

//Új adat bevitele

public class NewRendeles extends JDialog {
	private JTextField sorsz;
	private JTextField nev;
	private JTextField tip;
	private JTextField date;
	private JTextField ido;
	private JTextField ar;
	private JTextField meret;
	private DbMethods dbm=new DbMethods();
	private Checker c=new Checker();

//grafikus felület létrehozása feliratok, szövegbeviteli mezõk
	
	public NewRendeles(int abkez) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblSRSZ = new JLabel("Rendel\u00E9s sz\u00E1ma:");
		lblSRSZ.setBounds(12, 24, 121, 16);
		getContentPane().add(lblSRSZ);
		
		JLabel lblNv = new JLabel("N\u00E9v:");
		lblNv.setBounds(12, 53, 56, 16);
		getContentPane().add(lblNv);
		
		JLabel lblRendelsDtuma = new JLabel("Rendel\u00E9s d\u00E1tuma:");
		lblRendelsDtuma.setBounds(12, 140, 121, 16);
		getContentPane().add(lblRendelsDtuma);
		
		JLabel lblido = new JLabel("Rendel\u00E9s id\u0151pontja:");
		lblido.setBounds(12, 163, 121, 16);
		getContentPane().add(lblido);
		
		JLabel lbltipc = new JLabel("Pizza:");
		lbltipc.setBounds(12, 82, 56, 16);
		getContentPane().add(lbltipc);
		
		JLabel lblMret = new JLabel("M\u00E9ret:");
		lblMret.setBounds(12, 111, 56, 16);
		getContentPane().add(lblMret);
		
		JLabel lblar = new JLabel("\u00C1r:");
		lblar.setBounds(12, 192, 56, 16);
		getContentPane().add(lblar);
		
		sorsz = new JTextField();
		sorsz.setBounds(164, 21, 116, 22);
		getContentPane().add(sorsz);
		sorsz.setColumns(10);
		
		nev = new JTextField();
		nev.setBounds(164, 50, 116, 22);
		getContentPane().add(nev);
		nev.setColumns(10);
		
		tip = new JTextField();
		tip.setBounds(164, 79, 116, 22);
		getContentPane().add(tip);
		tip.setColumns(10);
		
		meret = new JTextField();
		meret.setBounds(164, 108, 116, 22);
		getContentPane().add(meret);
		meret.setColumns(10);
		
		date = new JTextField();
		date.setBounds(164, 137, 116, 22);
		getContentPane().add(date);
		date.setColumns(10);
		
		ido = new JTextField();
		ido.setBounds(164, 160, 116, 22);
		getContentPane().add(ido);
		ido.setColumns(10);
		
		ar = new JTextField();
		ar.setBounds(164, 186, 116, 22);
		getContentPane().add(ar);
		ar.setColumns(10);

//A beszúr gomb eseménykezelõje ellenõrzött adatbevitellel abkez változó állapotától függõen adatbázisba vagy csv fájlba		
		
		JButton btnBeszr = new JButton("Besz\u00FAr");
		btnBeszr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.goodInt(sorsz, "Rendelés száma"))
					if(c.filled(nev,"Név"))
						if(c.filled(tip, "Pizza"))
							if(c.goodInt(meret,"Méret"))
								if (c.goodDate(date, "Rendelés dátuma"))
									if (c.goodTime(ido, "Rendelés idõpontja"))
										if(c.goodInt(ar,"Ár"))
											if(abkez==0) {
									FileManager.InsertCsv(RTF(sorsz),RTF(nev),RTF(tip),RTF(meret),RTF(date),RTF(ido),RTF(ar));}
											else{
												dbm.Connect();
												dbm.InsertData(RTF(sorsz), RTF(nev), RTF(tip), RTF(meret),RTF(date), RTF(ido), RTF(ar));
												dbm.DisConnect();
												}
			}
		});
		btnBeszr.setBounds(164, 215, 97, 25);
		getContentPane().add(btnBeszr);
		
	
	}
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
}

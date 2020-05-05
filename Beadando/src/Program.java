import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;

public class Program extends JFrame {

	
	private JPanel contentPane;
	private DbMethods dbm=new DbMethods();
	private RendelesTM rtm;
	private int abkez=0;			//változó a Db kezelés chkbox állapotának figyelésére 
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program frame = new Program();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

//A Program ablak létrehozása vezérlõ gombok felrakása és a gombok eseménykezelõinek megírása
	
	public Program() {
		dbm.Reg();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLista = new JButton("Lista");
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			if(abkez==0) rtm=FileManager.CsvReader();
				
			else{
				dbm.Connect();
				rtm=dbm.ReadData();
				dbm.DisConnect();
				}
			RendelesList	rl = new RendelesList(Program.this, rtm);
			rl.setVisible(true);
			}
			});
		btnLista.setBounds(12, 36, 145, 25);
		contentPane.add(btnLista);
		
		JButton btnjAdatsor = new JButton("\u00DAj adatsor");
		btnjAdatsor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewRendeles nr=new NewRendeles(abkez);
				nr.setVisible(true);
			}
		});
		btnjAdatsor.setBounds(12, 74, 145, 25);
		contentPane.add(btnjAdatsor);
		
		JButton btnTrls = new JButton("T\u00F6rl\u00E9s");
		btnTrls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(abkez==0) rtm=FileManager.CsvReader();
				
				else {
					dbm.Connect();
					rtm=dbm.ReadData();
					dbm.DisConnect();
					}
				RendelesDel	rd = new RendelesDel(Program.this, rtm, abkez);
				rd.setVisible(true);
			}
		});
		btnTrls.setBounds(12, 112, 145, 25);
		contentPane.add(btnTrls);
		
		JButton btnMdosts = new JButton("M\u00F3dos\u00EDt\u00E1s");
		btnMdosts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(abkez==0) rtm=FileManager.CsvReader();

				else {
					dbm.Connect();
					rtm=dbm.ReadData();
					dbm.DisConnect();
					}
				RendelesMod	rm = new RendelesMod(Program.this, rtm, abkez);
				rm.setVisible(true);
			}
		});
		btnMdosts.setBounds(12, 150, 145, 25);
		contentPane.add(btnMdosts);

//Jelölõ adatbázis vezérléséhez 		
		
		JCheckBox chckbxDbKetzel = new JCheckBox("AB kezel\u00E9s");
		chckbxDbKetzel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxDbKetzel.isSelected()) abkez=1;
				else abkez=0;
			}
		});
		chckbxDbKetzel.setBounds(311, 189, 113, 25);
		contentPane.add(chckbxDbKetzel);
		
		Object Rendelestmn[]= {"Jel","Sorszám","Név","Pizza","Méret","Dátum","Idõpont","Ár"};
		rtm= new RendelesTM(Rendelestmn,0);
		
	}
}

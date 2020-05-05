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

public class RendelesDel extends JDialog {

	private final JPanel contentPanel=new JPanel();
	private JTable table;
	private RendelesTM rtm;
	private Checker c= new Checker();
	private DbMethods dbm= new DbMethods();
	
//törlés panel létrehozása	
	
	public RendelesDel(JFrame f, RendelesTM brtm, int abkez) {
		super(f, "Rendelések törlése", true);
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
		scrollPane.setBounds(0, 0, 432, 225);
		getContentPane().add(scrollPane);

//táblázat létrehozása, oszlopszélességek megadása		
		
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

//törlés gomb eseménykezelõje ellenõrzéssel		
		
		JButton btnAdatokTrlse = new JButton("Adatok t\u00F6rl\u00E9se");
		btnAdatokTrlse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db=0, jel=0,x=0;
				for(x=0; x<rtm.getRowCount(); x++)
					if((Boolean)rtm.getValueAt(x, 0)) {
						db++;
						jel=x;
					}
				if(db==0) c.SM("Nincs kijelölve a törlendõ rekord!", 0);
				if(db>1) c.SM("Több Rekord van kijelölve!\nEgyszerre csak egy rekord törölhetõ!", 0);
				if(db==1 ) {
					String kod=rtm.getValueAt(jel, 1).toString();
					rtm.removeRow(jel);
					if(abkez==0) FileManager.InsertCsv(rtm);
					else {
						dbm.Connect();
						dbm.DelData(kod);
						dbm.DisConnect();
						}
					dispose();
					c.SM("A rekord törölve!", 1);
				}
			}
			
		});
		btnAdatokTrlse.setBounds(10, 228, 124, 25);
		getContentPane().add(btnAdatokTrlse);
		TableRowSorter<RendelesTM> trs = (TableRowSorter<RendelesTM>)table.getRowSorter();
		trs.setSortable(0, false);
	}
}

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

public class RendelesList extends JDialog {

	private JTable table;
	private RendelesTM rtm;

//Lista panel létrehozása	
	
	public RendelesList(JFrame f, RendelesTM brtm) {
		super(f, "Rendelések listája", true);
		rtm=brtm;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton btnBezr = new JButton("Bez\u00E1r");
		btnBezr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnBezr.setBounds(167, 228, 97, 25);
		getContentPane().add(btnBezr);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 432, 225);
		getContentPane().add(scrollPane);

//táblázat létrehozása. oszlopszélesség megadása		
		
		table = new JTable(rtm);
		scrollPane.setViewportView(table);
		
		TableColumn tc=null;
		for(int i=0; i<8; i++)
		{
			tc=table.getColumnModel().getColumn(i);
			if(i==0) tc.setPreferredWidth(30);
			else if (i==1) tc.setPreferredWidth(50);
			else if (i==2) tc.setPreferredWidth(150);
			else {
				tc.setPreferredWidth(100);
			}
		}
		table.setAutoCreateRowSorter(true);
		TableRowSorter<RendelesTM> trs = (TableRowSorter<RendelesTM>)table.getRowSorter();
		trs.setSortable(0, false);
	}

}

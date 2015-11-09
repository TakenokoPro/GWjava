package graph;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class ClassTable {
	
	public static void main(String args[]) {
		Vector<Vector<String>> row = new Vector<Vector<String>>();
		Vector<String> column = new Vector<String>();

		//列名
		column.add("番号");
		column.add("パス");
		column.add("Internal数");
		column.add("パッケージ");
		
		for(int r = 0 ; r < 10 ; r++) {
			Vector<String> temp = new Vector<String>();
			temp.add("番号" + r);
			temp.add("パス" + r);
			temp.add("Internal数" + r);
			temp.add("パッケージ" + r);
			row.add(temp);
		}

		JTable table = new JTable(row , column);
		JScrollPane scrollPane = new JScrollPane(table);

		DefaultTableColumnModel columnModel
	      = (DefaultTableColumnModel)table.getColumnModel();
	    TableColumn col = null;
	    col = columnModel.getColumn(0);
	    col.setPreferredWidth(20);
	    col = columnModel.getColumn(2);
	    col.setPreferredWidth(20);
	    
		
		JFrame win = new JFrame();
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setBounds(10 , 10 , 400 , 300);
		win.getContentPane().add(scrollPane);
		win.show();
	}
}

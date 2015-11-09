package graph;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

import com.sun.rowset.internal.Row;

public class ClassTable {
	
	Vector<Vector<String>> row = new Vector<Vector<String>>();
	Vector<String> column = new Vector<String>();
	
	public ClassTable() {
		//列名
		column.add("番号");
		column.add("パス");
		column.add("Internal数");
		column.add("パッケージ");
	}
	
	public void Add(int i,String pass,int internal,String pac){
		Vector<String> temp = new Vector<String>();
		temp.add(String.valueOf(i));
		temp.add(pass);
		temp.add(String.valueOf(internal));
		temp.add(pac);
		row.add(temp);
	}
	
	public void DisplayTable(){
		JTable table = new JTable(row,column);	
		JScrollPane scrollPane = new JScrollPane(table);
		
		 // モデル作成
	    TableModel model = new TableModel(row);

	    //ソート機能（未実装）
	    /*
	    JTable table = new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(table);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel> (new TableModel(row));
		table.setRowSorter(sorter);*/
		
		// テーブル作成
		DefaultTableColumnModel columnModel
	      = (DefaultTableColumnModel)table.getColumnModel();
	    TableColumn col = null;
	    col = columnModel.getColumn(0);
	    col.setPreferredWidth(20);
	    col = columnModel.getColumn(2);
	    col.setPreferredWidth(20);
		
		JFrame win = new JFrame();
		win.setName("ClassTable");
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setBounds(10 , 10 , 400 , 800);
		win.getContentPane().add(scrollPane);
		win.show();
	}
}

class TableModel extends AbstractTableModel {
	  private int[] index;
	  private String[] pass;
	  private int[] Internal;
	  private String[] pac;

	  
	  public TableModel(Vector vector){
		  index =new int[vector.size()];
		  pass =new String[vector.size()];
		  Internal =new int[vector.size()];
		  pac =new String[vector.size()];
		  for(int i=0;i<vector.size();i++){
			  Vector vec = (Vector)vector.get(i);
			  System.out.println("+++"+vec);
			  index[i] = Integer.valueOf((String) vec.get(0));
			  pass[i] =   vec.get(1).toString();
			  Internal[i] = Integer.valueOf((String)vec.get(2));
			  pac[i] =  (String) vec.get(3);
		  }
		  
	  }
	  
	  // 列データのクラスを返す
	  public Class getColumnClass(int columnIndex) {
	    switch(columnIndex) {
		    case(0) : return String.class;
		    case(1) : return Integer.class;
	    }
	    return null;
	  }
	  public int getColumnCount() { return 4; } // 列数を返す
	  public String getColumnName(int column) {
	    switch(column) {
		    case(0) : return "番号";
		    case(1) : return "パス";
		    case(2) : return "Internal数";
		    case(3) : return "パッケージ";
	    }
	    return null;
	  }
	  public int getRowCount() { return index.length; } // 行数を返す
	  public Object getValueAt(int row, int column) {
	    switch(column) {
		    case(0) : return index[row];
		    case(1) : return pass[row];
		    case(2) : return Internal[row];
		    case(3) : return pac[row];
	    }
	    return null;

	  }
	}

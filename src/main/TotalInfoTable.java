package main;



import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

public class TotalInfoTable {
	
	/**=============================*/
	static Vector<Vector<String>> row = new Vector<Vector<String>>();
	Vector<String> column = new Vector<String>();
	Vector<Integer> color = new Vector<Integer>();
	
    TableModel model;// モデル作成
    JTable table;//ソート機能（未実装）
    /**=============================*/
	
	public TotalInfoTable() {
		//列名
		column.add("番号");
		column.add("クラス名");
		column.add("Internal数");
		column.add("パッケージ");
		column.add("class/interface");
		column.add("extend");
		column.add("implements");
		column.add("色");
	}	
	public void Add(
			int i,String name,int internal,String pac,String kind,String ext,String imp,int color){
		Vector<String> temp = new Vector<String>();
		temp.add(String.valueOf(i));
		temp.add(name);
		temp.add(String.valueOf(internal));
		temp.add(pac);
		temp.add(kind);
		temp.add(ext);
		temp.add(imp);
		temp.add(String.valueOf(color));
		row.add(temp);
		this.color.add(color);
	}
	public void DisplayTable(){
		
		 // モデル作成
	    model = new TableModel(row);
	    table = model.Table_init(model);

	    JScrollPane scrollPane = new JScrollPane(table);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel> (new TableModel(row));
		
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowSorter(sorter);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			    // 選択行の行番号を取得します
			    int row = table.getSelectedRow();
			    int col = table.getSelectedColumn();
			    System.out.println("行" + row + "::" + "列" + col);
			    table = model.Table_init(model);
			}
		});
	
		// テーブル作成
		DefaultTableColumnModel columnModel
	      = (DefaultTableColumnModel)table.getColumnModel();
	    TableColumn col = null;
	    col = columnModel.getColumn(0);
	    col.setPreferredWidth(20);
	    col = columnModel.getColumn(2);
	    col.setPreferredWidth(20);
	    col = columnModel.getColumn(4);
	    col.setPreferredWidth(30);
	    
		JFrame win = new JFrame();
		win.setTitle("ClassTable");
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setBounds(10 , 10 , 400 , 800);
		win.getContentPane().add(scrollPane);
		win.show();
	}
}

class TableModel extends AbstractTableModel {
	  private int[] index;
	  private String[] name;
	  private int[] internal;
	  private String[] pac;
	  private String[] kind;
	  private String[] ext;
	  private String[] imp;
	  private int[] colors;
	  private int colcount = 8;//列数

	  public TableModel(Vector<Vector<String>> vector){
		  
		  index =new int[vector.size()];
		  name =new String[vector.size()];
		  internal =new int[vector.size()];
		  pac =new String[vector.size()];
		  kind =new String[vector.size()];
		  ext =new String[vector.size()];
		  imp =new String[vector.size()];
		  colors =new int[vector.size()];
		  
		  for(int i=0;i<vector.size();i++){
			  Vector<String> vec = (Vector<String>)vector.get(i);
			  index[i] = Integer.valueOf((String) vec.get(0));
			  name[i] =   vec.get(1).toString();
			  internal[i] = Integer.valueOf((String)vec.get(2));
			  pac[i] = (String)vec.get(3);
			  kind[i] = (String)vec.get(4);
			  ext[i] =  (String)vec.get(5);
			  imp[i] = (String)vec.get(6);
			  colors[i] = Integer.valueOf((String)vec.get(7));
		  }	  
	  }
	  public JTable Table_init(TableModel model){
		   JTable table = new JTable(model) {
				@Override
		    	public Component prepareRenderer(TableCellRenderer tcr, int row, int column) {
			    	Component c = super.prepareRenderer(tcr, row, column);
			    	if(column==3){;
			    		c.setForeground(Color.WHITE);
				    	c.setBackground(new Color((Integer)getValueAt(row,7)));
			    	}
			    	else{
			    		c.setForeground(getForeground());
			    		c.setBackground(getBackground());
			    	}
			    	return c;
		    	}
		    };
		   return table;
	  }
	  
	  // 列データのクラスを返す
	  public Class getColumnClass(int columnIndex) {
	    switch(columnIndex) {
		    case(0) : return Integer.class;
		    case(1) : return String.class;
		    case(2) : return Integer.class;
		    case(3) : return String.class;
		    case(4) : return String.class;
		    case(5) : return String.class;
		    case(6) : return String.class;
		    case(7) : return Integer.class;
	    }
	    return null;
	  }
	  public String getColumnName(int column) {
	    switch(column) {
		    case(0) : return "番号";
		    case(1) : return "クラス名";
		    case(2) : return "Internal数";
		    case(3) : return "パッケージ";
		    case(4) : return "class/interface";
		    case(5) : return "extend";
		    case(6) : return "implements";
		    case(7) : return "色";
	    }
	    return null;
	  }
	  public int getRowCount() {return index.length;} // 行数を返す
	  public int getColumnCount() {return colcount;} // 列数を返す
	  public Object getValueAt(int row, int column) {
	    switch(column) {
		    case(0) : return index[row];
		    case(1) : return name[row];
		    case(2) : return internal[row];
		    case(3) : return pac[row];
		    case(4) : return kind[row];
		    case(5) : return ext[row];
		    case(6) : return imp[row];
		    case(7) : return colors[row];
	    }
	    return null;
	  }

}

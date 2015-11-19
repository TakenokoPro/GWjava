


import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

public class TotalInfoTable {
	
	/**=============================*/
	static Vector<Vector<String>> row = new Vector<Vector<String>>();
	Vector<String> column = new Vector<String>();
	
    TableModel model;// モデル作成
    JTable table;//ソート機能（未実装）
    /**=============================*/
	
	public TotalInfoTable() {
		//列名
		column.add("番号");
		column.add("クラス名");
		column.add("class/interface");
		column.add("extend");
		column.add("implements");
	}	
	public static void Add(int i,String name,String kind,String ext,String imp){
		Vector<String> temp = new Vector<String>();
		temp.add(String.valueOf(i));
		temp.add(name);
		temp.add(kind);
		temp.add(ext);
		temp.add(imp);
		row.add(temp);
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
	  private String[] kind;
	  private String[] ext;
	  private String[] imp;
	  private int colcount = 5;//列数

	  public TableModel(Vector<Vector<String>> vector){
		  
		  index =new int[vector.size()];
		  name =new String[vector.size()];
		  kind =new String[vector.size()];
		  ext =new String[vector.size()];
		  imp =new String[vector.size()];
		  
		  for(int i=0;i<vector.size();i++){
			  Vector<String> vec = (Vector<String>)vector.get(i);
			  index[i] = Integer.valueOf((String) vec.get(0));
			  name[i] =   vec.get(1).toString();
			  kind[i] = (String)vec.get(2);
			  ext[i] =  (String)vec.get(3);
			  imp[i] = (String)vec.get(4);
		  }	  
	  }
	  public JTable Table_init(TableModel model){
		   JTable table = new JTable(model) {/*
				@Override
		    	public Component prepareRenderer(TableCellRenderer tcr, int row, int column) {
			    	Component c = super.prepareRenderer(tcr, row, column);
			    	if(column==3){
			    		c.setForeground(Color.WHITE);
				    	c.setBackground(new Color(colors[(Integer)getValueAt(row,0)]));
			    	}
			    	else{
			    		c.setForeground(getForeground());
			    		c.setBackground(getBackground());
			    	}
			    	return c;
		    	}*/
		    };
		   return table;
	  }
	  
	  // 列データのクラスを返す
	  public Class getColumnClass(int columnIndex) {
	    switch(columnIndex) {
		    case(0) : return Integer.class;
		    case(1) : return String.class;
		    case(2) : return String.class;
		    case(3) : return String.class;
		    case(4) : return String.class;
	    }
	    return null;
	  }
	  public String getColumnName(int column) {
	    switch(column) {
		    case(0) : return "番号";
		    case(1) : return "クラス名";
		    case(2) : return "class/interface";
		    case(3) : return "extend";
		    case(4) : return "implements";
	    }
	    return null;
	  }
	  public int getRowCount() {return index.length;} // 行数を返す
	  public int getColumnCount() {return colcount;} // 列数を返す
	  public Object getValueAt(int row, int column) {
	    switch(column) {
		    case(0) : return index[row];
		    case(1) : return name[row];
		    case(2) : return kind[row];
		    case(3) : return ext[row];
		    case(4) : return imp[row];
	    }
	    return null;
	  }

}

package search;


import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableRowSorter;

public class ObjectInfoTable {
	
	/**=============================*/
	static Vector<Vector<String>> row = new Vector<Vector<String>>();
	Vector<String> column = new Vector<String>();
	
    ObjectTableModel model;// モデル作成
    JTable table;//ソート機能（未実装）
    /**=============================*/
	
	public ObjectInfoTable() {
		//列名
		column.add("種類");
		column.add("返り値");
		column.add("識別子");
		column.add("クラス");
	}	
	public static void Add(String kind,String returnObject,String identifier,String in_class){
		Vector<String> temp = new Vector<String>();
		temp.add(kind);
		temp.add(returnObject);
		temp.add(identifier);
		temp.add(in_class);
		row.add(temp);
	}
	public void DisplayTable(){
		
		 // モデル作成
	    model = new ObjectTableModel(row);
	    table = model.Table_init(model);

	    JScrollPane scrollPane = new JScrollPane(table);
		TableRowSorter<ObjectTableModel> sorter = new TableRowSorter<ObjectTableModel> (new ObjectTableModel(row));
		
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
		JFrame win = new JFrame();
		win.setTitle("ClassTable");
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setBounds(10 , 10 , 400 , 800);
		win.getContentPane().add(scrollPane);
		win.show();
	}
}

class ObjectTableModel extends AbstractTableModel {
	private String[] kind;
	private String[] returnObject;
	private String[] identifier;
	private String[] in_class;
	private int colcount = 4;//列数

	public ObjectTableModel(Vector<Vector<String>> vector){
	  
		kind =new String[vector.size()];
		returnObject =new String[vector.size()];
		identifier = new String[vector.size()];
		in_class = new String[vector.size()];
	  
		for(int i=0;i<vector.size();i++){
			Vector<String> vec = (Vector<String>)vector.get(i);
			kind[i] =  (String)vec.get(0);
			returnObject[i] = (String)vec.get(1);
			identifier[i] = (String)vec.get(2);
			in_class[i] = (String)vec.get(3);
		}	  
	}
	public JTable Table_init(ObjectTableModel model){
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
			case(0) : return String.class;
			case(1) : return String.class;
			case(2) : return String.class;
			case(3) : return String.class;
		}
		return null;
	}
	public String getColumnName(int column) {
	    switch(column) {
		    case(0) : return "種類";
		    case(1) : return "返り値";
		    case(2) : return "識別子";
		    case(3) : return "クラス";
	    }
	    return null;
  }
	public int getRowCount() {return kind.length;} // 行数を返す
	public int getColumnCount() {return colcount;} // 列数を返す
	public Object getValueAt(int row, int column) {
	    switch(column) {
		    case(0) : return kind[row];
		    case(1) : return returnObject[row];
		    case(2) : return identifier[row];
		    case(3) : return in_class[row];
	    }
	    return null;
	  }
}
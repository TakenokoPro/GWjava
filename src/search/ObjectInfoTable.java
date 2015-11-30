package search;


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
		column.add("生成元クラス");
		column.add("生成先クラス");
		column.add("行");
		row = new Vector<Vector<String>>();
		column = new Vector<String>();
	}	
	public static void Add(String kind,String returnObject,String identifier,String in_class,String out_class,int line){
		Vector<String> temp = new Vector<String>();
		temp.add(kind);
		temp.add(returnObject);
		temp.add(identifier);
		temp.add(in_class);
		temp.add(out_class);
		temp.add(String.valueOf(line));
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
	private String[] out_class;
	private int[] line;
	private int colcount = 6;//列数

	public ObjectTableModel(Vector<Vector<String>> vector){
	  
		kind =new String[vector.size()];
		returnObject =new String[vector.size()];
		identifier = new String[vector.size()];
		in_class = new String[vector.size()];
		out_class = new String[vector.size()];
		line = new int[vector.size()];
	  
		for(int i=0;i<vector.size();i++){
			Vector<String> vec = (Vector<String>)vector.get(i);
			kind[i] =  (String)vec.get(0);
			returnObject[i] = (String)vec.get(1);
			identifier[i] = (String)vec.get(2);
			in_class[i] = (String)vec.get(3);
			out_class[i] = (String)vec.get(4);
			line[i] = Integer.valueOf(vec.get(5));
		}	  
	}
	public JTable Table_init(ObjectTableModel model){
		JTable table = new JTable(model) {
			@Override
	    	public Component prepareRenderer(TableCellRenderer tcr, int row, int column) {
		    	Component c = super.prepareRenderer(tcr, row, column);
		    	if(column==0){
		    		c.setForeground(Color.WHITE);
		    		if(getValueAt(row,column).equals("オブジェクト生成"))c.setBackground(Color.red);
		    		if(getValueAt(row,column).equals("関数定義"))c.setBackground(Color.blue);
		    		if(getValueAt(row,column).equals("関数呼出"))c.setBackground(Color.green);
		    		if(getValueAt(row,column).equals("変数生成"))c.setBackground(Color.orange);
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
			case(0) : return String.class;
			case(1) : return String.class;
			case(2) : return String.class;
			case(3) : return String.class;
			case(4) : return String.class;
			case(5) : return Integer.class;
		}
		return null;
	}
	public String getColumnName(int column) {
	    switch(column) {
		    case(0) : return "種類";
		    case(1) : return "返り値";
		    case(2) : return "識別子";
		    case(3) : return "生成元クラス";
		    case(4) : return "生成先クラス";
		    case(5) : return "行";
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
		    case(4) : return out_class[row];
		    case(5) : return line[row];
	    }
	    return null;
	  }
}

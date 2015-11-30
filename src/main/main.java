package main;
import java.net.ConnectException;

import graph.ClassTable;
import graph.io.GraphIOmodel;
import graph.io.graphIO;
import search.class_info;
import search.new_class;
import search.io.SearchIOmodel;
import search.io.searchIO;


public class main {
	
	// TODO: 結合テスト（メイン）
	static searchIO sUi = new searchIO();
	static graphIO gUi = new graphIO();
	static ShowFocus showFocus = new ShowFocus();
	
	public static void main(String[] arg) {
		
		
		// TODO: 結合テスト（テーブル）
		//ClassTable classTable = gUi.get_springanim().get_inport_file().get_ClassTable();
		//classTable.DisplayTable();
		
		// TODO: 結合テスト（テーブル）
		GraphIOmodel graphIOmodel = gUi.get_springanim().get_inport_file().get_GraphIOmodel();
		SearchIOmodel searchIOmodel = sUi.get_searchIOmodel();
		
		TotalInfoTable totalInfoTable = new TotalInfoTable();
		
		for(int i=0;i<graphIOmodel.length();i++){
			for(int j=0;j<searchIOmodel.length();j++){
				String temp_g = str_encode(graphIOmodel.packagePath.get(i));
				String temp_s = str_encode(searchIOmodel.pack.get(j));
				if(graphIOmodel.name.get(i).equals(searchIOmodel.name.get(j))&&temp_g.equals(str_encode(temp_s))){
					showFocus.connect(j,i);
					totalInfoTable.Add(
						i,
						graphIOmodel.name.get(i),
						graphIOmodel.internal.get(i),
						graphIOmodel.packagePath.get(i),
						searchIOmodel.classinternal.get(j),
						searchIOmodel.extend.get(j),
						searchIOmodel.inplement.get(j),
						graphIOmodel.colorHex.get(i)
					);
				}
			}
		}
		totalInfoTable.DisplayTable();
		
		showFocus.show(sUi.get_classinfo());
		while(true){
			gUi.springAnim.repaint();
			if(gUi.springAnim.get_focus_flag())show(gUi.springAnim.get_focus());
		}
	}

	/*テーブルクリック部のテーブルを表示*/
	public static void show(int a){
		showFocus.show_f(sUi.get_classinfo(),a);
	}
	
	/*正規表現で無効になるメタ文字*/
	public static String str_encode(String str){
		str = str.replaceAll("\\.","DottttT");
		return str;
	}	
	public String str_decode(String str){
		str = str.replaceAll("DottttT","\\.");
		return str;
	}

}

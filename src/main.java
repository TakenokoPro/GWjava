import graph.ClassTable;
import graph.io.GraphIOmodel;
import graph.io.graphIO;
import search.io.SearchIOmodel;
import search.io.searchIO;


public class main {
	
	public static void main(String[] arg) {
		// TODO: 結合テスト（メイン）
		searchIO sUi = new searchIO();
		graphIO gUi = new graphIO();
		
		// TODO: 結合テスト（テーブル）
		ClassTable classTable = gUi.get_springanim().get_inport_file().get_ClassTable();
		classTable.DisplayTable();
		
		// TODO: 結合テスト（テーブル）
		TotalInfoTable totalInfoTable;
		GraphIOmodel graphIOmodel = gUi.get_springanim().get_inport_file().get_GraphIOmodel();
		SearchIOmodel searchIOmodel = sUi.get_searchIOmodel();
		totalInfoTable.DisplayTable();
		
		while(true)gUi.springAnim.repaint();
	}
	
}

import graph.ClassTable;
import graph.ui.graphUI;
import seach.new_class;
import seach.ui.seachUI;


public class main {
	
	public static void main(String[] arg) {
		// TODO: 結合テスト（メイン）
		seachUI sUi = new seachUI();
		graphUI gUi = new graphUI();
		
		// TODO: 結合テスト（テーブル）
		ClassTable classTable = gUi.get_springanim().get_inport_file().get_ClassTable();
		classTable.DisplayTable();
		
		while(true)gUi.springAnim.repaint();
	}
	
}

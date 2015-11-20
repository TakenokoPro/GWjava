import graph.ClassTable;
import graph.io.GraphIOmodel;
import graph.io.graphIO;
import search.new_class;
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
		GraphIOmodel graphIOmodel = gUi.get_springanim().get_inport_file().get_GraphIOmodel();
		SearchIOmodel searchIOmodel = sUi.get_searchIOmodel();
		
		TotalInfoTable totalInfoTable = new TotalInfoTable();/*
		for(int i=0;i<graphIOmodel.length();i++){
		for(int j=0;j<searchIOmodel.length();j++){
			if(graphIOmodel.name.get(i).equals(searchIOmodel.name.get(j)))
			System.out.println(graphIOmodel.name.get(i)+","+
					graphIOmodel.internal.get(i)+","+
					graphIOmodel.packagePath.get(i)+","+
					searchIOmodel.classinternal.get(j)+","+
					searchIOmodel.extend.get(j)+","+
					searchIOmodel.inplement.get(j)+","+
					graphIOmodel.colorHex.get(i)+","
			);
			}
		}*/
		
		for(int i=0;i<graphIOmodel.length();i++){
			for(int j=0;j<searchIOmodel.length();j++)
				if(graphIOmodel.name.get(i).equals(searchIOmodel.name.get(j)))
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
		totalInfoTable.DisplayTable();
		
		while(true)gUi.springAnim.repaint();
	}
	
}

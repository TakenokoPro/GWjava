package main;
import graph.io.GraphIOmodel;
import graph.io.graphIO;
import search.io.SearchIOmodel;
import search.io.searchIO;


public class main {
	
	/**=============================*/	
	// TODO: 結合テスト（メイン）
	static searchIO sIo;
	static graphIO gIo;
	static ShowFocus showFocus;
	static int connect[];
	/**=============================*/	
	
	public static void main(String[] arg) {
		
		String aaaa = "GWsampleProject\\src";
		//String SourcePath = "C:\\Dropbox\\GraduationWork\\ResearchResults\\jasmin\\jasmin-2.0\\src";
		String SourcePath = "C:\\pleiades\\workspace\\"+aaaa;
		//String SourcePath = "C:\\Dropbox\\GraduationWork\\ResearchResults\\jlgui\\jlgui2.2\\E_\\jlGui2.2\\src\\javazoom";
		//String SourcePath = "C:\\Dropbox\\GraduationWork\\ResearchResults\\jackcess\\1.2.10";
		String XmlPath = "cl_gwSamplejava.xml";
		/**=============================*/	
		// TODO: メイン実行
		sIo = new searchIO(SourcePath);
		gIo = new graphIO(XmlPath);
		showFocus = new ShowFocus();
		/**=============================*/
		
		
		
		
		// TODO: 結合テスト（テーブル）
		//ClassTable classTable = gIo.get_springanim().get_inport_file().get_ClassTable();
		//classTable.DisplayTable();
		
		// TODO: 結合テスト（テーブル）
		GraphIOmodel graphIOmodel = gIo.get_springanim().get_inport_file().get_GraphIOmodel();
		SearchIOmodel searchIOmodel = sIo.get_searchIOmodel();
		
		TotalInfoTable totalInfoTable = new TotalInfoTable();
		
		connect = new int[graphIOmodel.length()];
		for(int i=0;i<graphIOmodel.length();i++){
			connect[i]=0;
			boolean flag = false; 
			for(int j=0;j<searchIOmodel.length();j++){
				String temp_g = str_encode(graphIOmodel.packagePath.get(i));
				String temp_s = str_encode(searchIOmodel.pack.get(j));
				if(graphIOmodel.name.get(i).equals(searchIOmodel.name.get(j))&&temp_g.equals(temp_s)){
					showFocus.connect(j,i);
					connect[i] = j; 
					flag = true; 
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
			if(!flag)totalInfoTable.Add(i,graphIOmodel.name.get(i),graphIOmodel.internal.get(i),graphIOmodel.packagePath.get(i),"----","----","----",graphIOmodel.colorHex.get(i));
		}
		totalInfoTable.DisplayTable();
		
		//for(int i=0;i<connect.length;i++)System.out.println("connect["+i+"]="+connect[i]);
		
		//ノードの太さを変える
		int con[][] = sIo.usednternalCounter(connect);
		gIo.get_springanim().get_inport_file().get_graphCanvas().set_connect(con);		
		showFocus.show(sIo.get_classinfo());
		
		while(true){
			gIo.springAnim.repaint();
			if(gIo.springAnim.get_focus_flag())show(gIo.springAnim.get_focus());
		}
	}
	
	/**=============================*/	
	/*テーブルクリック部のテーブルを表示*/
	public static void show(int a){
		showFocus.show_f(sIo.get_classinfo(),a);
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

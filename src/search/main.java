package search;

import java.util.ArrayList;


public class main {
	
	private static ArrayList<class_info> class_infos;
	static ObjectInfoTable objectTable = new ObjectInfoTable();

	public static void main(String[] args) {
		//Clear_source clear_source = new Clear_source();
		Search_sourse search_sourse = new Search_sourse();
		//ReadFolder readFolder = new ReadFolder();
		
		//テーブルの表示
		class_infos = search_sourse.get_classinfo();
		for(int i=0;i<class_infos.size();i++){
			ArrayList<method_type> temp_m = class_infos.get(i).definition_get();
			for(int j=0;j<temp_m.size();j++){
				System.out.println(temp_m.get(j).returnType_get()+",==="+temp_m.get(j).identifier_get());
				ObjectInfoTable.Add("関数定義",temp_m.get(j).returnType_get(),temp_m.get(j).identifier_get());
			}
			ArrayList<String> temp_s = class_infos.get(i).callmethod_get();
			for(int j=0;j<temp_s.size();j++){
				System.out.println(temp_s.get(j));
				ObjectInfoTable.Add("関数呼出",temp_s.get(j),"");
			}
			ArrayList<new_class> temp_c = class_infos.get(i).new_class_get();
			for(int j=0;j<temp_c.size();j++){
				System.out.println(temp_c.get(j));
				ObjectInfoTable.Add("オブジェクト生成",temp_c.get(j).class_name,temp_c.get(j).identifier);
			}
		}
		objectTable.DisplayTable();
	}

}

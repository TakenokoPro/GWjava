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
			ArrayList<method_type> temp = class_infos.get(i).definition_get();
			for(int j=0;j<temp.size();j++){
				//System.out.println(temp.get(j).returnType_get()+",==="+temp.get(j).identifier_get());
				System.out.println(class_infos.get(i).name_get());
				ObjectInfoTable.Add("関数定義",temp.get(j).returnType_get(),temp.get(j).identifier_get(),class_infos.get(i).name_get());
			}
			ArrayList<String> temp1 = class_infos.get(i).callmethod_get();
			for(int j=0;j<temp1.size();j++){
				//System.out.println(temp1.get(j));
				int num = getNameForMethod(temp1.get(j));
				//System.out.println(num);
				String aString;
				if(num != -1){
					aString = class_infos.get(getNameForMethod(temp1.get(j))).name_get();	
				}
				else aString = "------";
				ObjectInfoTable.Add("関数呼出","",temp1.get(j),aString);
			}
		}
		objectTable.DisplayTable();
	}
	public static int getNameForMethod(String methodName){
		for(int i=0;i<class_infos.size();i++){
			if(class_infos.get(i).definition_search(methodName))return i;
			//System.out.println(class_infos.get(i).name_get());
		}
		return -1;
	}

}

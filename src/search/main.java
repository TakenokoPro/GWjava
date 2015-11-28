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
				ObjectInfoTable.Add(
						"関数定義",
						temp_m.get(j).returnType_get(),
						temp_m.get(j).identifier_get(),
						"",
						class_infos.get(i).name_get(),
						temp_m.get(j).line_get()
				);
			}
			ArrayList<method_type> temp_s = class_infos.get(i).callmethod_get();
			for(int j=0;j<temp_s.size();j++){
				int num = getNameForMethod(temp_s.get(j).identifier_get());
				String aString = null;
				if(num != -1){
					aString = class_infos.get(getNameForMethod(temp_s.get(j).identifier_get())).name_get();	
				}
				ObjectInfoTable.Add(
						"関数呼出",
						"",
						temp_s.get(j).identifier_get(),
						aString,
						class_infos.get(i).name_get(),
						temp_s.get(j).line
				);
			}
			ArrayList<new_class> temp_c = class_infos.get(i).new_class_get();
			for(int j=0;j<temp_c.size();j++){
				int num = getNameForNew(temp_c.get(j).class_name);
				String aString = null;
				if(num != -1){
					aString = class_infos.get(getNameForNew(temp_c.get(j).class_name)).name_get();	
				}
				ObjectInfoTable.Add(
						"オブジェクト生成",
						temp_c.get(j).class_name,
						temp_c.get(j).identifier,
						aString,
						class_infos.get(i).name_get(),
						temp_c.get(j).line
				);

			}
			ArrayList<object_type> temp_o = class_infos.get(i).object_types;
			for(int j=0;j<temp_o.size();j++){
				int num = getNameForNew(temp_o.get(j).type);
				String oString = null;
				if(num != -1){
					oString = class_infos.get(getNameForNew(temp_o.get(j).type)).name_get();	
				}
				ObjectInfoTable.Add(
						"変数生成",
						temp_o.get(j).type,temp_o.get(j).identifier,
						oString,
						class_infos.get(i).name_get(),
						temp_o.get(j).line
				);

			}
		}
		objectTable.DisplayTable();
	}
	
	//メソッド名からクラス名を取得
	public static int getNameForMethod(String methodName){
		for(int i=0;i<class_infos.size();i++){
			if(class_infos.get(i).definition_search(methodName))return i;
		}
		String temp[] = methodName.split("\\.");
		for(int i=0;i<class_infos.size();i++){
			for(int j=0;j<class_infos.get(i).new_class_get().size();j++)
				if(class_infos.get(i).new_class_search(temp[0])&&temp.length>1)return i;
		}
		return -1;
	}
	//オブジェクト生成時にクラス名を取得
	public static int getNameForNew(String className){
		for(int i=0;i<class_infos.size();i++){
			if(className.equals(class_infos.get(i).name_get()))return i;
			String[] temp = className.split("[<>]");
			for(int j=0;j<temp.length;j++)
				if(temp[j].equals(class_infos.get(i).name_get())){
					return i;
				}
		}
		return -1;
	}

}

package main;
import java.util.ArrayList;

import search.ObjectInfoTable;
import search.class_info;
import search.method_type;
import search.new_class;
import search.object_type;

public class ShowFocus {

	//テーブル情報
	ObjectInfoTable objectTable = new ObjectInfoTable();
	
	//searchとglaphの接続
	int connect[] = new int[1000];
	
	public ShowFocus(){
		
	}
	
	public void connect(int a,int b){
		connect[b] = a;
		System.out.println("["+b+"]="+connect[b]);
	}
	
	//テーブルの表示
	public void show(ArrayList<class_info> class_infos){
		objectTable = new ObjectInfoTable();
		for(int i=0;i<class_infos.size();i++){
			ArrayList<method_type> temp_m = class_infos.get(i).definition_get();
			for(int j=0;j<temp_m.size();j++){
				objectTable.Add(
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
				int num = getNameForMethod(class_infos,temp_s.get(j).identifier_get());
				String aString = null;
				if(num != -1){
					aString = class_infos.get(getNameForMethod(class_infos,temp_s.get(j).identifier_get())).name_get();	
				}
				objectTable.Add(
						"関数呼出",
						"",
						temp_s.get(j).identifier_get(),
						aString,
						class_infos.get(i).name_get(),
						temp_s.get(j).line_get()
				);
			}
			ArrayList<new_class> temp_c = class_infos.get(i).new_class_get();
			for(int j=0;j<temp_c.size();j++){
				int num = getNameForNew(class_infos,temp_c.get(j).name_get());
				String aString = null;
				if(num != -1){
					aString = class_infos.get(getNameForNew(class_infos,temp_c.get(j).name_get())).name_get();	
				}
				objectTable.Add(
						"オブジェクト生成",
						temp_c.get(j).name_get(),
						temp_c.get(j).identifier_get(),
						aString,
						class_infos.get(i).name_get(),
						temp_c.get(j).line_get()
				);

			}
			ArrayList<object_type> temp_o = class_infos.get(i).object_get();
			for(int j=0;j<temp_o.size();j++){
				int num = getNameForNew(class_infos,temp_o.get(j).type_get());
				String oString = null;
				if(num != -1){
					oString = class_infos.get(getNameForNew(class_infos,temp_o.get(j).type_get())).name_get();	
				}
				ObjectInfoTable.Add(
						"変数生成",
						temp_o.get(j).type_get(),temp_o.get(j).identifier_get(),
						oString,
						class_infos.get(i).name_get(),
						temp_o.get(j).line_get()
				);

			}
		}
		objectTable.DisplayTable();
	}
	
	//フォーカスのテーブルの表示
    public void show_f(ArrayList<class_info> class_infos,int a){
    	objectTable = new ObjectInfoTable();
    	System.out.println(class_infos.get(connect[a]).name_get()+"**"+objectTable);
    	for(int i=0;i<class_infos.size();i++){
		//for(int s=0;s<class_infos.size();s++)System.out.println(s+","+connect[s]+"::"+class_infos.get(s).name_get());
		
		/*ArrayList<method_type> temp_m = class_infos.get(i).definition_get();
		for(int j=0;j<temp_m.size();j++){
			if(class_infos.get(i).name_get().equals(class_infos.get(connect[a]).name_get()))
				objectTable.Add("関数定義",temp_m.get(j).returnType_get(),temp_m.get(j).identifier_get(),"",class_infos.get(i).name_get(),temp_m.get(j).line_get());
		}*/
		
		ArrayList<method_type> temp_s = class_infos.get(i).callmethod_get();
		for(int j=0;j<temp_s.size();j++){
			int num = getNameForMethod(class_infos,temp_s.get(j).identifier_get());
			String sString = "";
			if(num != -1){sString = class_infos.get(getNameForMethod(class_infos,temp_s.get(j).identifier_get())).name_get();}
			if((sString.equals(class_infos.get(connect[a]).name_get())|| class_infos.get(i).name_get().equals(class_infos.get(connect[a]).name_get()))){
				if((sString.equals(class_infos.get(connect[a]).name_get())|| class_infos.get(i).name_get().equals(class_infos.get(connect[a]).name_get())))continue;
				if(sString.equals(""))continue;
				objectTable.Add("関数呼出","",temp_s.get(j).identifier_get(),sString,class_infos.get(i).name_get(),temp_s.get(j).line_get());
			}
		}
		
		ArrayList<new_class> temp_c = class_infos.get(i).new_class_get();
		for(int j=0;j<temp_c.size();j++){
			int num = getNameForNew(class_infos,temp_c.get(j).name_get());
			String cString = "";
			if(num != -1){cString = class_infos.get(getNameForNew(class_infos,temp_c.get(j).name_get())).name_get();}
			if(cString.equals(class_infos.get(connect[a]).name_get()) || class_infos.get(i).name_get().equals(class_infos.get(connect[a]).name_get())){
				if(cString.equals(class_infos.get(connect[a]).name_get()) && class_infos.get(i).name_get().equals(class_infos.get(connect[a]).name_get()))continue;
				if(cString.equals(""))continue;
				objectTable.Add("オブジェクト生成",temp_c.get(j).name_get(),temp_c.get(j).identifier_get(),cString,class_infos.get(i).name_get(),temp_c.get(j).line_get());
			}
		}
		ArrayList<object_type> temp_o = class_infos.get(i).object_get();
		for(int j=0;j<temp_o.size();j++){
			int num = getNameForNew(class_infos,temp_o.get(j).type_get());
			String oString = "";
			if(num != -1){oString = class_infos.get(getNameForNew(class_infos,temp_o.get(j).type_get())).name_get();}
			if(oString.equals(class_infos.get(connect[a]).name_get()) || class_infos.get(i).name_get().equals(class_infos.get(connect[a]).name_get())){
				if(oString.equals(class_infos.get(connect[a]).name_get()) && class_infos.get(i).name_get().equals(class_infos.get(connect[a]).name_get()))continue;
				if(oString.equals(""))continue;
				objectTable.Add("変数生成",temp_o.get(j).type_get(),temp_o.get(j).identifier_get(),oString,class_infos.get(i).name_get(),temp_o.get(j).line_get());
			}
		}
    	}
		objectTable.DisplayTable();
    }
	
	//メソッド名からクラス名を取得
	public int getNameForMethod(ArrayList<class_info> class_infos,String methodName){
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
	public int getNameForNew(ArrayList<class_info> class_infos,String className){
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

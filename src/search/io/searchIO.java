package search.io;

import java.util.ArrayList;

import search.Clear_source;
import search.Search_sourse;
import search.class_info;
import search.method_type;
import search.new_class;
import search.object_type;

public class searchIO {
	
	private ArrayList<class_info> class_infos;
	SearchIOmodel searchIOmodel = new SearchIOmodel();

	public searchIO(String path){
		// TODO: UI（検索）
		Clear_source clear_source = new Clear_source(path);
		Search_sourse search_sourse = new Search_sourse();
		class_infos = search_sourse.get_classinfo();
	}
	
	public ArrayList<class_info> get_classinfo(){
		return class_infos;
	}

	public SearchIOmodel get_searchIOmodel() {
		SearchIOmodel searchIOmodel = new SearchIOmodel();
		for(int i=0;i<class_infos.size();i++)
			searchIOmodel.add(class_infos.get(i).name_get(),class_infos.get(i).pack_get(),class_infos.get(i).kind_get(),class_infos.get(i).extend_get(),class_infos.get(i).interface_get());
		return searchIOmodel;
	}
	
	public int[][] usednternalCounter(int[] connect){
		System.out.println("usedInternalCounter");
		int max = connect.length+1;
		for(int i=0;i<connect.length;i++){
			if(connect[i]>max)max =connect[i];
			System.out.print(connect[i]+",");
		}
		System.out.println("\nmax======"+max);
		if(connect.length>max)max=connect.length;
		int[][] returnnum = new int[max+1][max+1];
		for(int i=0;i<max+1;i++)for(int j=0;j<max+1;j++)returnnum[i][j]=-1;
		for(int i=0;i<class_infos.size();i++){
			/*************************/
			ArrayList<method_type> temp_s = class_infos.get(i).callmethod_get();
			for(int j=0;j<temp_s.size();j++){
				int num= getNameForMethod(temp_s.get(j).identifier_get());
				if(num == -1
					//	||get_connect(connect,num)==-1
					//	||get_connect(connect,i)==-1
				)continue;
				//if(i!=num)System.out.print(get_connect(connect,num)+",");
				//returnnum[connect[i]][connect[num]]++;
				//returnnum[connect[num]][connect[i]]++;
				returnnum[get_connect(connect,num)][get_connect(connect,i)]++;
				returnnum[get_connect(connect,i)][get_connect(connect,num)]++;
				//returnnum[i][num]++;
				//returnnum[num][i]++;
			}
			/*************************/
			ArrayList<new_class> temp_c = class_infos.get(i).new_class_get();
			for(int j=0;j<temp_c.size();j++){
				int num = getNameForNew(temp_c.get(j).name_get());
				if(num == -1
						//||get_connect(connect,num)==-1
						//||get_connect(connect,i)==-1
				)continue;
				//returnnum[connect[i]][connect[num]]++;
				//returnnum[connect[num]][connect[i]]++;
				//if(i!=num)System.out.print(num+",");
				returnnum[get_connect(connect,num)][get_connect(connect,i)]++;
				returnnum[get_connect(connect,i)][get_connect(connect,num)]++;
				//returnnum[i][num]++;
				//returnnum[num][i]++;
			}
			/*************************/
			ArrayList<object_type> temp_o = class_infos.get(i).object_get();
			for(int j=0;j<temp_o.size();j++){
				int num = getNameForNew(temp_o.get(j).type_get());
				if(num == -1
						//||get_connect(connect,num)==-1
						//||get_connect(connect,i)==-1
				)continue;
				//returnnum[connect[i]][connect[num]]++;
				//returnnum[connect[num]][connect[i]]++;
				//if(i!=num)System.out.print(num+",");
				returnnum[get_connect(connect,num)][get_connect(connect,i)]++;
				returnnum[get_connect(connect,i)][get_connect(connect,num)]++;
				//returnnum[i][num]++;
				//returnnum[num][i]++;
			}
		}
		return returnnum;
	}

	//メソッド名からクラス名を取得
	public int getNameForMethod(String methodName){
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
	public int getNameForNew(String className){
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

	//クラス名から数値を取得
	public int getNumforName(String str){
			for(int i=0;i<class_infos.size();i++){
				if(str.equals(class_infos.get(i).name_get()))return i;
				String[] temp = str.split("[<>]");
				for(int j=0;j<temp.length;j++)
					if(temp[j].equals(class_infos.get(i).name_get())){
						return i;
					}
			}
			return -1;
		}

	public int get_connect(int a[],int ans){
		for(int i=0;i<a.length;i++)if(a[i]==ans)return i;
		return -1;
	}
}
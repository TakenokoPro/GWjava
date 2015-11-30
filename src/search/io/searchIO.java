package search.io;

import java.util.ArrayList;

import search.Search_sourse;
import search.class_info;
import search.new_class;

public class searchIO {
	
	private ArrayList<class_info> class_infos;
	SearchIOmodel searchIOmodel = new SearchIOmodel();

	public searchIO(){
		// TODO: UI（検索）
		//Clear_source clear_source = new Clear_source();
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
	
	
}

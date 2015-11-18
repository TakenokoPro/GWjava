package seach.ui;

import java.util.ArrayList;

import seach.Clear_source;
import seach.Search_sourse;
import seach.class_info;

public class seachUI {
	
	private ArrayList<class_info> class_infos;

	public seachUI(){
		// TODO: UI（検索）
		Clear_source clear_source = new Clear_source();
		Search_sourse search_sourse = new Search_sourse();
		class_infos = search_sourse.get_classinfo();
	}

}

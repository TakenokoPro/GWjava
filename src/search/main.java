package search;

import java.util.ArrayList;

import main.ShowFocus;


public class main {
	public static void main(String[] args) {
		//Clear_source clear_source = new Clear_source();
		Search_sourse search_sourse = new Search_sourse();
		//ReadFolder readFolder = new ReadFolder();
		
		//テーブルの表示
		ShowFocus showFocus = new ShowFocus();
		showFocus.show(search_sourse.class_infos);
	}
}

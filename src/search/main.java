package search;

import main.ShowFocus;


public class main {
	public static void main(String[] args) {
		String string = "C:\\Dropbox\\GraduationWork\\ResearchResults\\jackcess\\1.2.10";
		Clear_source clear_source = new Clear_source(string);
		Search_sourse search_sourse = new Search_sourse();
		//ReadFolder readFolder = new ReadFolder();
		
		//テーブルの表示
		ShowFocus showFocus = new ShowFocus();
		showFocus.show(search_sourse.class_infos);
	}
}

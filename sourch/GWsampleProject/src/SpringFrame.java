import java.util.ArrayList;


public class SpringFrame {
	
	static ArrayList<ClassInfo> classInfo;
	
	public SpringFrame() {
		// TODO SpringFrame
		System.out.println("SpringFrame");
		InportFile inportFile = new InportFile();
		inportFile.run();
		inportFile.print();
	}

	public static void marge(SearchSource searchSource) {
		System.out.println("marge");
		classInfo = searchSource.classInfos;
	}
}

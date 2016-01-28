import java.util.ArrayList;


public class SearchSource {
	
	ArrayList<ClassInfo> classInfos = new ArrayList<ClassInfo>();

	public SearchSource() {
		// TODO SearchSourceコンストラクター
		System.out.println("SearchSource");
		SearchClass();
		SearchNew();
		SearchMethod();
		SearchObject();
	}

	private void SearchClass() {
		// TODO SearchClass
		System.out.println("SearchClass");
		classInfos.add(new ClassInfo());
	}

	private void SearchNew() {
		// TODO SearchNew
		System.out.println("SearchNew");
		classInfos.get(0).news.add(new NewM());
	}

	private void SearchMethod() {
		// TODO SearchMethod
		System.out.println("SearchMethod");
		classInfos.get(0).calls.add(new Call());
		classInfos.get(0).definitions.add(new Definition());
	}

	private void SearchObject() {
		// TODO SearchObject
		System.out.println("SearchObject");
		classInfos.get(0).objects.add(new ObjectT());
	}
}

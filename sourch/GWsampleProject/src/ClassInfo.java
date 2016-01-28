import java.util.ArrayList;


public class ClassInfo {
	
	String className;
	ArrayList<NewM> news = new ArrayList<NewM>();
	ArrayList<ObjectT> objects = new ArrayList<ObjectT>();
	ArrayList<Definition> definitions = new ArrayList<Definition>();
	ArrayList<Call> calls = new ArrayList<Call>();
	
	public ClassInfo() {
		System.out.println("ClassInfo");
		this.className = "";
	}
}

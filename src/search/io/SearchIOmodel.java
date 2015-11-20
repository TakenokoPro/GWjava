package search.io;

import java.util.Vector;

public class SearchIOmodel {
	public Vector<String> name = new Vector<String>();
	public Vector<String> classinternal = new Vector<String>();
	public Vector<String> extend = new Vector<String>();
	public Vector<String> inplement = new Vector<String>();
	
	public void add(String name,String classinternal,String extend,String inplement) {
		this.name.add(name);
		this.classinternal.add(classinternal);
		this.extend.add(extend);
		this.inplement.add(inplement);
	}
	
	public int length(){
		return name.size();
	}
}

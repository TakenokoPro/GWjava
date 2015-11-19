package search.io;

import java.util.Vector;

public class SearchIOmodel {
	Vector<String> name = new Vector<String>();
	Vector<Integer> internal = new Vector<Integer>();
	Vector<String> packagePath = new Vector<String>();
	Vector<Integer> colorHex = new Vector<Integer>();
	
	public void add(String name,int internal,String pac,int color) {
		this.name.add(name);
		this.internal.add(internal);
		this.packagePath.add(pac);
		this.colorHex.add(color);
	}
}

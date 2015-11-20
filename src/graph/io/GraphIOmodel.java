package graph.io;

import java.util.Vector;

public class GraphIOmodel {
	public Vector<String> name = new Vector<String>();
	public Vector<Integer> internal = new Vector<Integer>();
	public Vector<String> packagePath = new Vector<String>();
	public Vector<Integer> colorHex = new Vector<Integer>();
	
	public void add(String name,int internal,String pac,int color) {
		this.name.add(name);
		this.internal.add(internal);
		this.packagePath.add(pac);
		this.colorHex.add(color);
	}
	
	public int length(){
		return this.name.size();
	}
}

package search.io;

import java.net.ConnectException;
import java.util.Vector;

import search.new_class;

public class SearchIOmodel {
	public Vector<String> name = new Vector<String>();
	public Vector<String> pack = new Vector<String>();
	public Vector<String> classinternal = new Vector<String>();
	public Vector<String> extend = new Vector<String>();
	public Vector<String> inplement = new Vector<String>();
	public Vector<Integer> connect = new Vector<Integer>();
	
	public void add(String name,String pack,String classinternal,String extend,String inplement) {
		this.name.add(name);
		this.pack.add(pack);
		this.classinternal.add(classinternal);
		this.extend.add(extend);
		this.inplement.add(inplement);
		this.connect.add(0);
	}
	
	
	public void connect(int i,int j){
		connect.set(i,j);
	}
	
	public int length(){
		return name.size();
	}
}

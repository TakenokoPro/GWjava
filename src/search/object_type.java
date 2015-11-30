	package search;

public class object_type {
	String type;
	String identifier;
	int line;
	
	public object_type(String type,String identifier,int line) {
		this.type = type;
		this.identifier = identifier;
		this.line = line;
	}
	public String type_get(){
		return type;
	}
	public String identifier_get(){
		return identifier;
	}
	public int line_get(){
		return line;
	}
}

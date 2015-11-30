package search;

public class new_class {
	String class_name;
	String identifier;
	int line;
	
	public new_class(String name,String identifier,int line) {
		this.class_name = name;
		this.identifier = identifier;
		this.line = line;
	}
	
	public String name_get(){
		return class_name;
	}
	public String identifier_get(){
		return identifier;
	}
	public int line_get(){
		return line;
	}
}

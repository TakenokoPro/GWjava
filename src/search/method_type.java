	package search;


public class method_type {
	String returnType;
	String identifier;
	int line;
	
	public method_type(String type,String identifier,int line) {
		this.returnType = type;
		this.identifier = identifier;
		this.line = line;
	}
	public String returnType_get(){
		return returnType;
	}
	public String identifier_get(){
		return identifier;
	}
	public int line_get(){
		return line;
	}
}

	package search;


public class method_type {
	String returnType;
	String identifier;
	
	public method_type(String type,String identifier) {
		this.returnType = type;
		this.identifier = identifier;
	}
	public String returnType_get(){
		return returnType;
	}
	public String identifier_get(){
		return identifier;
	}
}

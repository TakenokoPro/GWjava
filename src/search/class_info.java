package search;
import java.util.ArrayList;


public class class_info {
	String class_name; 
	String kind;
	ArrayList<String> interfaceString = new ArrayList<String>();
	ArrayList<String> extendString = new ArrayList<String>();
	ArrayList<new_class> new_classes = new ArrayList<new_class>();
	ArrayList<object_type> object_types = new ArrayList<object_type>();
	ArrayList<method_type> definitionMethod = new ArrayList<method_type>();
	ArrayList<method_type> callMethod = new ArrayList<method_type>();
	
	//開始行
	int startLine;
	
	public class_info(String class_name,String kind,int startLine){
		this.class_name = class_name;
		this.kind = kind;
		this.startLine = startLine;
	}
	
	public void interface_add(String iString){
		this.interfaceString.add(iString);
	}
	public void extend_add(String eString){
		this.extendString.add(eString);
	}
	public void new_class_add(String name,String identifer,int line){
		this.new_classes.add(new new_class(name,identifer,line));
	}
	public void object_types_add(String type,String identifier,int line){
		this.object_types.add(new object_type(type,identifier,line));
	}
	public void definitionMethod_add(String type,String identifier,int line){
		this.definitionMethod.add(new method_type(type,identifier,line));
	}
	public void callMethod_add(String identifier,int line){
		this.callMethod.add(new method_type("",identifier,line));
	}
	
	/*getter*/
	public String name_get(){
		return class_name;
	}
	public String kind_get(){
		return kind;
	}
	public String interface_get(){
		String return_str = "";
		for(int i=0;i<interfaceString.size();i++){
			if(return_str != "")return_str += ",";
			return_str += interfaceString.get(i);
		}
		return return_str;
	}
	public String extend_get(){
		String return_str = "";
		for(int i=0;i<extendString.size();i++){
			if(return_str != "")return_str += ",";
			return_str += extendString.get(i);
		}
		return return_str;
	}
	public ArrayList<new_class> new_class_get(){
		return new_classes;
	}
	public ArrayList<object_type> object_get(){
		return object_types;
	}
	public ArrayList<method_type> definition_get(){
		return definitionMethod;
	}
	public ArrayList<method_type> callmethod_get(){
		return callMethod;
	}
	
	/*search*/
	public boolean definition_search(String search){
		for(int i=0;i<definitionMethod.size();i++)
			if(definitionMethod.get(i).identifier_get().equals(search))return true;
		return false;
	}
	public boolean new_class_search(String search){
		for(int i=0;i<new_classes.size();i++)
			if(new_classes.get(i).identifier_get().equals(search))return true;
		return false;
	}
	
	public int startline_get(){
		return startLine;
	}
}

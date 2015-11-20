package search;
import java.util.ArrayList;


public class class_info {
	String class_name; 
	String kind;
	ArrayList<String> interfaceString = new ArrayList<String>();
	ArrayList<String> extendString = new ArrayList<String>();
	ArrayList<new_class> new_classes = new ArrayList<new_class>();
	ArrayList<object_type> object_types = new ArrayList<object_type>();
	ArrayList<String> definitionMethod = new ArrayList<String>();
	ArrayList<String> callMethod = new ArrayList<String>();
	
	public class_info(String class_name,String kind){
		this.class_name = class_name;
		this.kind = kind;
		System.out.println(this.kind+" "+this.class_name);
	}
	
	public void interface_add(String iString){
		this.interfaceString.add(iString);
		//System.out.println(this.kind+" "+this.class_name+this.interfaceString.toString());
	}
	public void extend_add(String eString){
		this.extendString.add(eString);
		//System.out.println(this.kind+" "+this.class_name+this.extendString.toString());
	}
	public void new_class_add(int id,String name,String identifer){
		this.new_classes.add(new new_class(id,name,identifer));
	}
	public void object_types_add(String type,String identifier){
		this.object_types.add(new object_type(type,identifier));
	}
	public void definitionMethod_add(String dString){
		this.definitionMethod.add(dString);
	}
	public void callMethod_add(String cString){
		this.callMethod.add(cString);
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
}

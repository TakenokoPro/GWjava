import java.util.ArrayList;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.xml.internal.bind.v2.runtime.Name;


public class class_info {
	String class_name; 
	String kind;
	ArrayList<String> interfaceString;
	ArrayList<String> extendString;
	ArrayList<new_class> new_classes;
	ArrayList<object_type> object_types;
	ArrayList<String> definitionMethod;
	ArrayList<String> callMethod;
	
	public class_info(String class_name,String kind){
		this.class_name = class_name;
		this.kind = kind;
		System.out.println(this.kind+" "+this.class_name);
	}
	
	public void interface_add(String iString){
		this.interfaceString.add(iString);
	}
	public void extend_add(String eString){
		this.extendString.add(eString);
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
}

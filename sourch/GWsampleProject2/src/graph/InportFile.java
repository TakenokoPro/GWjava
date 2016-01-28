package graph;
import java.util.ArrayList;


public class InportFile {
	ArrayList<ClassNode> classNodes = new ArrayList<ClassNode>();
	FourceDirected fourceDirected;
	SpringCanvas springCanvas;
	
	public InportFile() {
		// TODO InportFile
		System.out.println("InportFile");
		classNodes.add(new ClassNode());
		fourceDirected = new FourceDirected();
		springCanvas = new SpringCanvas();
	}
	
	void run(){
		System.out.println("run");
		fourceDirected.blance();
	}
	
	void print(){
		System.out.println("print");
		springCanvas.print();
		
	}
}

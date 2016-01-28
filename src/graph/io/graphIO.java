package graph.io;

import graph.SpringAnim;

public class graphIO {
	
	public SpringAnim springAnim;
	
	public graphIO(String path){
		// TODO: UI（スプリングアルゴリズム）
		springAnim =new SpringAnim(path);
		springAnim.addMouseListener(springAnim);
		springAnim.addMouseMotionListener(springAnim);
		springAnim.setSize(1000,1000);
		springAnim.setVisible(true);
	    springAnim.addWindowListener(springAnim.new Adapter());
		springAnim.init();
		springAnim.setTitle("SpringGraph");
	}
	
	public SpringAnim get_springanim(){
		return springAnim;
	}
}

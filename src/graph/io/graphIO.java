package graph.io;

import graph.SpringAnim;

public class graphIO {
	
	public SpringAnim springAnim =new SpringAnim();
	
	public graphIO(){
		// TODO: UI（スプリングアルゴリズム）
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

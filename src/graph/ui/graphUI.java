package graph.ui;

import graph.SpringAnim;
import graph.SpringAnim.Adapter;

import java.util.ArrayList;

import seach.Clear_source;
import seach.Search_sourse;
import seach.class_info;

public class graphUI {
	
	public SpringAnim springAnim =new SpringAnim();
	
	public graphUI(){
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

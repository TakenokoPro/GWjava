package graph;

import java.awt.*;
import java.awt.event.*;

public class main extends Frame{
	
	public static void main(String[] args) {	    
		main f = new main();
		//フレーム作成
		f.setSize(600, 600);
		f.setVisible(true);
		//リスナー設定
		f.addWindowListener(new Adap());
	}
	public void paint(Graphics g){
		//線を表示
		g.drawLine(20,50, 90, 50);
	}
}
	
class Adap extends WindowAdapter{
	public void windowClosing(WindowEvent e){
		//×が押されたときの処理
		System.exit(0);
	}
}

package graph;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class SpringAnim extends Frame implements Runnable,MouseListener,MouseMotionListener{
	
	/**=============================*/
	//TODO:変数の宣言
	Dimension dimension;
	Graphics OSG;	//オフスクリーングラフィックス
	Image OSI;	//オフスクリーンイメージ
	Thread th = null;	//スレッドオブジェクト宣言
	int delay = 200;	//スレッドの待ち時間
	
	Inport_file inport_file = new Inport_file();
	static int click_x,click_y;//クリックされた座標
	static boolean click_act = false;//クリックされているか
	boolean Focus_flag = false;//フォーカスが当たったフラグ
	/**=============================*/
	
	//TODO:初期化処理
	public void init() {
		dimension = getSize();
		OSI = createImage(dimension.width,dimension.height);
		OSG = OSI.getGraphics();
		//OSG.setColor(Color.black);
	}
	
	//TODO:描画用
	public void paint(Graphics g) {
		OSG.clearRect(0,0,getSize().width,getSize().height); //オフスクリーンの初期化
		
		//TODO:プログラム
		inport_file.run();
		inport_file.paint(OSG);
		//ここまでプログラム
		
		g.drawImage(OSI,0,0,this);
	}
	public void update(Graphics g) {
		paint(g);
	}
	
	//TODO: アプレットが開始する時の処理
	public void start() {
		
		th = new Thread(this);
		th.start();
	}
	//TODO: アプレットが停止する時の処理
	public void stop() {
			th = null;
	}
	//TODO: スレッドに処理させるプログラム
	public void run() {
		Thread me = Thread.currentThread();
		while (th == me) {
			try{
				Thread.sleep(delay);
				
			}catch (InterruptedException e) {}
			//アプレットの場合はrepaint()をすることが多いです
			repaint();
		}
	}

	public class Adapter extends WindowAdapter{
	    public void windowClosing(WindowEvent close){
	    	System.exit(0);
	   }
	}

	//マウス動作
	public void mouseClicked(MouseEvent e) {
	
	}
	public void mousePressed(MouseEvent e) {
		click_x=e.getX();
		click_y=e.getY();
		ArrayList<ClassNode> nodes = inport_file.graph.classNodes;
		for(int i=0;i<nodes.size();i++){
			if(calcDistance(nodes.get(i).x,nodes.get(i).y,click_x,click_y)<10){
				inport_file.springGraph.click_focus = i;
				Focus_flag = true;
			}
		}
	}
	public void mouseReleased(MouseEvent e) {
		click_act = false;
	}
	public void mouseEntered(MouseEvent e) {
		
	}
	public void mouseExited(MouseEvent e) {
		click_act = false;
	}
	public void mouseDragged(MouseEvent e) {
		int x = click_x - e.getX();
		int y = click_y - e.getY();
		ArrayList<ClassNode> nodes = inport_file.graph.classNodes;
		for(int i=0;i<nodes.size();i++){
			inport_file.graph.classNodes.get(i).x = nodes.get(i).x-x;
			inport_file.graph.classNodes.get(i).y = nodes.get(i).y-y;
		}
		click_x=e.getX();
		click_y=e.getY();
	}
	public void mouseMoved(MouseEvent e) {
		
	}
	
	//二点間の距離を求める関数
	public int calcDistance(double x1,double y1,int x2,int y2) {
		double a, b, d;
		a = x1 - x2;
		b = y1 - y2;
		d = Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
		return (int)d;
	}

	//getter
	public Inport_file get_inport_file() {
		return inport_file;
	}
	public boolean get_focus_flag(){
		if(Focus_flag){
			Focus_flag = false;
			return true;
		}
		else return false;
	}
	public int get_focus(){
		return inport_file.springGraph.click_focus;
	}
}

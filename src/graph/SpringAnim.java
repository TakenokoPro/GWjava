package graph;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SpringAnim extends Frame implements Runnable{
	
	//TODO:変数の宣言
	Dimension dimension;
	Graphics OSG;	//オフスクリーングラフィックス
	Image OSI;	//オフスクリーンイメージ
	Thread th = null;	//スレッドオブジェクト宣言
	int delay = 200;	//スレッドの待ち時間
	
	Inport_file inport_file = new Inport_file();
	
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
}

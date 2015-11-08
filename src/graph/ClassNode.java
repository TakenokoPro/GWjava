package graph;

import java.util.ArrayList;

public class ClassNode implements GlobalValue{
	
	int id;
	String value;
	double x,y;//座標
	int r;
	int pac;//パッケージ番号
	int background;
	boolean exist;
	double vx=0,vy=0;//加速度
	boolean colorflag = true;
	
	//繋がっているノード
	ArrayList<ClassNode> connections = new ArrayList<ClassNode>();
	
	//Nodeのコンストラクタ
	public ClassNode(int value,double x,double y,int pac,int background){
	    this.value = String.valueOf(value);
	    this.x = x;
	    this.y = y;
		this.r = 10;
		this.pac = pac;//パッケージ番号
		this.background = background;
		this.exist = true;
		/*
	    var idx = ~~(Math.random() * colorPalette.length);//色の選択
	    if (!this.background) {
	      this.background = colorPalette[idx];
	    }*/
	}

	//繋がっているノードの追加
	public void connect(ClassNode node) {
		this.connections.add(node);
	}
	    
	//繋がっているノードのカウント
	public int connect_count(){
		return this.connections.size();
	}

	//端っこにあるかを判定
	public boolean edge() {
		if(
			this.x-this.r<=30||
			this.y-this.r<=30//||
			//WIN_W-30<=this.x+this.r||
			//WIN_H-30<=this.y+this.r
		)
			return true;
		else 
			return false;
	}
		
	//利用関係がなかったら存在させない
	public void not_use(){
		this.exist = false;
	}
		
	public void setX(double pointX){this.x=pointX;};
	public void setY(double pointY){this.y=pointY;};

}

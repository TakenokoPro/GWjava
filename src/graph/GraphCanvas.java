package graph;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;


public class GraphCanvas extends Canvas{
	
	/**=============================*/
	Image img;
	Graphics graphics;
	ArrayList<ForceDirectedGraph> elements = new ArrayList<ForceDirectedGraph>();
	public Object balance; 
	int click_focus=0;
	int connect[][] = null;
	/**=============================*/
	
	// TODO:graphのコンストラクタ
	public GraphCanvas() {
		
	}
	
	//エレメントの追加
	public void add(ForceDirectedGraph element){
		this.elements.add(element);
	}
	
	//グラフ全般の描写処理
	public void paint(Graphics g){
		graphics = g;
		clear();
		//this.drawaxis();
	    for (int element=0;element<this.elements.size();element++) {
    		NODE_CONNECT(element);
    		NODE_FOCUS(element);
    		//DRAWPOINT(element);
	    }
	}
	
	//クラスタリング時の出力
	/*
	public void clustering_draw(){
		int rgb_str;
		for (int k = 0; k < Cnodes.length; k++) {
			if(!Cnodes[k].init){
				if(Cnodes[k].num<max_num*1/4)rgb_str=0xCCCCCC;
				else if(Cnodes[k].num<max_num*2/4)rgb_str=0x999999;
				else if(Cnodes[k].num<max_num*3/4)rgb_str=0x666666;
				else if(Cnodes[k].num<max_num*4/4)rgb_str=0x333333;
				else rgb_str = 0xFF6666;
				this.circle(Cnodes[k].x, Cnodes[k].y, Cnodes[k].r,rgb_str);
				this.text(Cnodes[k].value, Cnodes[k].x, Cnodes[k].y);
			}
		}
	}
	*/
	
	//connected[a][b]で,node[a]とnode[b]が繋がっていたらtrue
	private void NODE_CONNECT(int element){
		boolean[][] connected = new boolean[elements.get(element).classNodes.size()][elements.get(element).classNodes.size()];
		for (int i=0;i<elements.get(element).classNodes.size();i++)
			for (int j=0;j<elements.get(element).classNodes.size();j++){
				connected[i][j] = false;
			}
		NODE_WEIGHT(element);
		for (int i=0;i<elements.get(element).classNodes.size();i++){
			ClassNode node = elements.get(element).classNodes.get(i);
			for (int j=0;j<node.connections.size();j++){
				if (connected[i][j] || connected[j][i])continue;
				connected[i][j] = true;
				connected[j][i] = true;
				line(node.x, node.y, node.connections.get(j).x, node.connections.get(j).y,0.5F,0xCCCCCC);
			}
		}
	}
	private void NODE_WEIGHT(int element){
		if(connect==null)return;
		for(int i=0;i<connect.length;i++){
			ClassNode node1 = elements.get(element).classNodes.get(i);
			for(int j=0;j<connect.length&&i!=j;j++){
				ClassNode node2 = elements.get(element).classNodes.get(j);
				if(connect!=null&&connect[i][j]>0){
					int weight;
					if(connect[i][j]>100)weight=10;else weight=(int)(connect[i][j]*1);
					//System.out.println("["+i+"]["+j+"]"+connect[i][j]);
					line(node1.x, node1.y, node2.x, node2.y,(float)weight,0xFFCCCC);
				}
			}
		}
	}
	
	//フォーカスで線に色付け
	private void NODE_FOCUS(int element){
		//マウスが接しているとき赤色
		for (int i=0;i<elements.get(element).classNodes.size();i++){
			ClassNode node = elements.get(element).classNodes.get(i);
			for (int j=0;j<elements.get(element).classNodes.size(); j++){
				//if((i==move_flag)&&move_flag!=0)
					//line(node.x, node.y, node.connections[j].x, node.connections[j].y,0xFFFF00);
			}
		}
		//パッケージを押したときは灰色ハイライトに
		for (int i=0;i<elements.get(element).classNodes.size();i++){
			ClassNode node = elements.get(element).classNodes.get(i);
			//if (node.isFocus)
				//_this.circle(node.x, node.y, node.r + 5, '#444');
			if(i==click_focus)
				circle(node.x, node.y, node.r+3, 0xFF0000);
			if(node.colorflag){
				circle(node.x, node.y, node.r, node.background);
			}else{
				circle(node.x, node.y, node.r, 0xCCCCCC);
			}
			text(node.value,node.x,node.y,0xFF0000);
			
		}
	}
	
	//座標情報の表示
	private void DRAWPOINT(){/*
		var centerX = parseInt(graph.nodes[0].x);
		var centerY = parseInt(graph.nodes[0].y);
		for ( i=0;i<graph.nodes.length;i++) {
			node = graph.nodes[i];
			if(point[i]==null)break;
			//表に座標を表示
			point[i].innerHTML = "("+parseInt(node.x-centerX)+","+parseInt(node.y-centerY)+")";
			//端っこは背景色を赤
			if(node.edge())
				point[i].style.backgroundColor ='#faa';
			else
				point[i].style.backgroundColor ='#eee';
		}*/
	}
	
	//軸の表示
	private void drawaxis() {
		
	}

	//ノードの太さをセット
	public void set_connect(int a[][]){
		connect = new int[a.length][a[0].length];
		connect = a;
		for(int i=0;i<connect.length;i++){
			for(int j=0;j<connect.length;j++)
				System.out.print("["+connect[i][j]+"]");
			System.out.print("\n");
		}
	}
	
	//円の描写(座標(x,y),半径r)
	private void circle(double x,double y,int r,int bg){
		graphics.setColor(new Color(bg));
		graphics.fillOval((int)(x-r),(int)(y-r), r*2, r*2);     
	}
		
	//長方形の描画(左上(x1,y1),右下(x2,y2))
	private void Rect(double x1,double y1,double x2,double y2,int bg){
		graphics.setColor(new Color(bg));
		graphics.fillRect((int)x1, (int)y1, (int)(x2-x1), (int)(y2-y1));
	}

	//線の描写(始点(fromX,fromY),終点(toX,toY))
	private void line(double fromX,double fromY,double toX,double toY,float w,int color) {
		Graphics2D g = (Graphics2D)graphics;
		BasicStroke wideStroke = new BasicStroke(w);
		//BasicStroke wideStroke = new BasicStroke(0.5f);
		g.setStroke(wideStroke);
	    g.setColor(new Color(color));
	    g.drawLine((int)fromX,(int)fromY,(int)toX,(int)toY);
	}

	//テキストの描画(文章txt,座標(x,y))
	private void text(String txt,double x,double y,int color) {
		
	    Graphics2D g2 = (Graphics2D)graphics;
	    Font font = new Font("Arial", Font.BOLD, 10);
	    g2.setFont(font);
	    g2.setColor(new Color(0xFFFFFF));
	    FontMetrics fm = g2.getFontMetrics();
		Rectangle rectText = fm.getStringBounds(txt,g2).getBounds();
		int str_x = (int) (rectText.width/2);
        int str_y = (int) (rectText.height/2-fm.getMaxAscent());
		g2.drawString(txt,(int)(x-str_x),(int)(y-str_y));
	}
	
	//背景色を白で塗りつぶす
	private void clear(){
		graphics.clearRect(0,0,1000,1000);
	}

}
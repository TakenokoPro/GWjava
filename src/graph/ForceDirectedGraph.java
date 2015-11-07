package graph;

import java.util.ArrayList;

public class ForceDirectedGraph {

	double BOUNCE = 0.03;//バネ定数(BOUNCE < 0.1[推奨])
	double ATTENUATION = 0.8;//減衰定数(ATTENUATION < 1[必須])
	double COULOMB = 30;//クーロン数
	ArrayList<ClassNode> classNodes;

	public ForceDirectedGraph(ArrayList<ClassNode> nodes) {
		classNodes = nodes;
	}

	//ノードの読み取り
	public void add(ClassNode node){
	    this.classNodes.add(node);
	}

	//繋がっているノードの追加(node[a].connection,node[b].connectionに追加)
	public void connect(int a,int b){
		//this.nodes[a].r--;
		//this.nodes[b].r++;
	    this.classNodes.get(a).connect(this.classNodes.get(b));
	    this.classNodes.get(b).connect(this.classNodes.get(a));
	}

	//力の演算
	public void balance(int max){
	    double distX, distY, fx, fy;
	    for(int number=0;number<this.classNodes.size();number++){
	    	//動くノード(targetNode)
	    	ClassNode targetNode = this.classNodes.get(number);
	    	//if (targetNode.isFocus)continue;
				
			//targetNodeに働く力
	    	fx = 0;
	    	fy = 0;	
			/*==============================================*/
			/*クーロン力*/
			/*==============================================*/
	    	for(int i=0;i<this.classNodes.size();i++) {
		        if (targetNode == this.classNodes.get(i) 
		        		/*|| this.nodes[i].edge()*/
		        		||!this.classNodes.get(i).exist
		        )continue;
					distX = targetNode.x - this.classNodes.get(i).x;
					distY = targetNode.y - this.classNodes.get(i).y;
					double rsq = distX * distX + distY * distY;
					fx += COULOMB * distX / rsq;
					fy += COULOMB * distY / rsq;
		      }
			/*==============================================*/
			/*バネ*/
			/*==============================================*/
	    	for (int i=0;i<targetNode.connections.size();i++) {
				if (/*this.nodes[i].edge()||*/!this.classNodes.get(i).exist)continue;
		        distX = targetNode.connections.get(i).x - targetNode.x;
		        distY = targetNode.connections.get(i).y - targetNode.y;
		        fx += BOUNCE * distX;
		        fy += BOUNCE * distY;
	    	}
			/*==============================================*/
			/*力(fx,fy)を速度に変換*/
			/*==============================================*/
		    targetNode.vx = (targetNode.vx + fx) * ATTENUATION;
		    targetNode.vy = (targetNode.vy + fy) * ATTENUATION;
					
			/*maxの場合は変化させない*/
		    if (number != max) {
		        targetNode.x += targetNode.vx;
		        targetNode.y += targetNode.vy;
				//後で端っこのは動かない処理
				//if(targetNode.x<0)targetNode.x = 0;
				//if(targetNode.y<0)targetNode.y = 0;
				//if(WIN_W<targetNode.x)targetNode.x = WIN_W;
				//if(WIN_H<targetNode.y)targetNode.y = WIN_H;
		   }
	    
		//バネ定数,クーロン力を表に出力
		//coulomb_str.innerHTML = COULOMB;
		//bounce_str.innerHTML = BOUNCE;
	  }
	 }

	//クーロン力の変化
	public double coulomb_add(boolean bool){
		 if(bool)
	    	return (COULOMB+1);
		 else if(COULOMB>1)
	    	return (COULOMB-1);
		 else
			 return COULOMB;
	 }

	//バネ力の変化
	public double bounce_add(boolean bool){
		BOUNCE *= 100;
		if(bool)
			BOUNCE += 1;
		else if(BOUNCE>1)
			BOUNCE -= 1;
		return BOUNCE = Math.round(BOUNCE)/ 100;
	}
}

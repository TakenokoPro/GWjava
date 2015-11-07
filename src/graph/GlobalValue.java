package graph;

public class GlobalValue {
	String[][] colorPalettes = new String[12][5];
	/*
	colorPalettes[0] = ['#f39800','#f2a218','#f2ab30','#f2b449','#f2bd61'];//橙
	colorPalettes[1] = ['#e6002d','#e6002e','#e62e53','#e64565','#e65c77'];//赤
	colorPalettes[2] = ['#00b350','#009944','#009948','#1f9956','#2e995e'];//緑
	colorPalettes[3] = ['#36318f','#07008f','#150e8f','#302b8f','#3d398f'];//青
	colorPalettes[4] = ['#801e6c','#74085e','#800d69','#803370','#804073'];//紫
	colorPalettes[5] = ['#00e6da','#00ccc2','#009e96','#109e97','#3f9e99'];//水
	colorPalettes[6] = ['#5d310c','#5c2900','#5c3312','#5c381c','#5c3d25'];//茶
	colorPalettes[7] = ['#ff6666','#f07070','#f29696','#f08b8b','#f59494'];//ピンク
	colorPalettes[8] = ['#88e02e','#92e043','#96db50','#b2d63c','#ccf058'];//ライム
	colorPalettes[9] = ['#07194b','#0f2257','#12296a','#17213e','#0f296f'];//藍
	colorPalettes[10] = ['#339933','#38ac38','#4fac4f','#469546','#42b742'];//緑
	colorPalettes[11] = ['#3978f5','#417cf4','#6699ff','#5d8ae2','#2c65d6'];//青*/
	
	//2点間の距離
	double calcDistance(double x1,double y1,double x2,double y2) {
		double a, b, d;
		a = x1 - x2;
		b = y1 - y2;
		d = Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
		return d;
	}
	
	//ランダム数字の出力
	double random(int max){
	  return Math.random() * max;
	}

}

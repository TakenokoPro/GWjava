package graph;


public class main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		SpringAnim springAnim =new SpringAnim();
		springAnim.setSize(1000,1000);
		springAnim.setVisible(true);
	    springAnim.addWindowListener(springAnim.new Adapter());
		springAnim.init();
		while(true)springAnim.repaint();
	}
}

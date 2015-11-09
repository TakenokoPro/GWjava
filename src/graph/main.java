package graph;


public class main {

	public static void main(String[] args) {
		// TODO: メイン
		SpringAnim springAnim =new SpringAnim();
		springAnim.setTitle("SpringGraph");
		springAnim.setSize(1000,1000);
		springAnim.setVisible(true);
	    springAnim.addWindowListener(springAnim.new Adapter());
		springAnim.init();
		while(true)springAnim.repaint();
	}
}

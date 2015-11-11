package graph;


public class main {

	public static void main(String[] args) {
		// TODO: メイン
		SpringAnim springAnim =new SpringAnim();
		springAnim.addMouseListener(springAnim);
		springAnim.addMouseMotionListener(springAnim);
		springAnim.setSize(1000,1000);
		springAnim.setVisible(true);
	    springAnim.addWindowListener(springAnim.new Adapter());
		springAnim.init();
		springAnim.setTitle("SpringGraph");
		while(true)springAnim.repaint();
	}
}

package graph;


public class main {

	public static void main(String[] args) {
		// TODO: 単体テスト（スプリングアルゴリズム）
		String XmlPath = "cl_gwSamplejava2.xml";
		SpringAnim springAnim =new SpringAnim(XmlPath);
		springAnim.addMouseListener(springAnim);
		springAnim.addMouseMotionListener(springAnim);
		springAnim.setSize(1000,1000);
		springAnim.setVisible(true);
	    springAnim.addWindowListener(springAnim.new Adapter());
		springAnim.init();
		springAnim.setTitle("SpringGraph");
		
		// TODO: 単体テスト（テーブル出力）
		ClassTable classTable = springAnim.get_inport_file().get_ClassTable();
		System.out.print(classTable);
		classTable.DisplayTable();
		
		while(true)springAnim.repaint();
	}
}

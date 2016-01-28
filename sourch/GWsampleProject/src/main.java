
public class main {

	public static void main(String[] args) {
		// TODO メイン
		System.out.println("mainSTART");
		new ClearSource();
		System.out.println("main1");
		SearchSource searchSource =  new SearchSource();
		System.out.println("main2");
		SpringFrame springFrame = new SpringFrame();
		System.out.println("mainEND");
		SpringFrame.marge(searchSource);
	}

}

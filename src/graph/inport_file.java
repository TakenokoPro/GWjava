package graph;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class inport_file {
	/**=============================*/	
	/**object**/
	static String in_Path = "xml\\classycle.xml";//""内に分析したいフォルダを(\は２連続で)
	int count = 0;
	ArrayList<String> split_str;
	
	//inport_file method
	ArrayList<String> list_xml = new ArrayList<String>(); //XMLに存在するクラスのリスト
	ArrayList<String> node_Name = new ArrayList<String>();//ノードの設定
	ArrayList<String> node_branch = new ArrayList<String>();//アークの設定
	String[][] usedInternal = new String[1000][];//usedInternalの設定
	
	int internal_max=0;		//最大のinternalのクラス数
	int max_class=0;		//最大のクラス番号
	ArrayList<Boolean> no_used = new ArrayList<Boolean>();	//利用関係の無いクラスの計算
	
	//divide_package method
	ArrayList<String> classgroup = new ArrayList<String>();//パッケージ名
	ArrayList<Integer> classgroup_number = new ArrayList<Integer>();//ノードごとのパッケージの番号
	ArrayList<Integer> classgroup_internal = new ArrayList<Integer>();//パッケージが下の階層であるなら上の階層の配列番号が入る
	ArrayList<Integer> classgroup_color1 = new ArrayList<Integer>();//一番親のパッケージ番号.何層目にあるか
	ArrayList<Integer> classgroup_color2 = new ArrayList<Integer>();//一番親のパッケージ番号.何層目にあるか
	ArrayList<Integer>parent_package = new ArrayList<Integer>();//親パッケージを挿入
	
	/**=============================*/
	/**main*/
	public static void main(String[] args){
		new inport_file();
	}
	
	/**inport_file=============================*/
	public inport_file(){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = factory.newDocumentBuilder();
		}
		catch(ParserConfigurationException e){e.printStackTrace();}
		
		Document document = null;
		
		try {
			document = documentBuilder.parse(in_Path);
		}
		catch(SAXException e){e.printStackTrace();}
		catch(IOException e){e.printStackTrace();}
	
		Element root = document.getDocumentElement();
		
		//ルート要素のノード名を取得する
		System.out.println("ノード名：" +root.getNodeName());

		//ルート要素の子ノードを取得する
		NodeList rootChildren = root.getChildNodes();

		System.out.println("子要素の数：" + rootChildren.getLength());
		System.out.println("------------------"); 
		
		for(int i=0; i < rootChildren.getLength(); i++) {
			Node node = rootChildren.item(i);
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element)node;
				// 関係ないので<cycles><packageCycles><packages>を削除
				if(!(element.getNodeName()=="classes"))continue;			
				NodeList pChildren = node.getChildNodes();
				
				System.out.println("子要素の数：" + pChildren.getLength());
				System.out.println("------------------"); 
				
				for (int j=0;j<pChildren.getLength();j++) {
					Node Pnode = pChildren.item(j);
					NamedNodeMap pAtr = Pnode.getAttributes();
					ArrayList<String> usedInternal_temp = new ArrayList<String>(); 
					if (pAtr!=null) {
						System.out.println(pAtr.getNamedItem("name"));
						// innerClass="true"の<class>を削除 
						if(pAtr.getNamedItem("innerClass").getNodeValue().equals("true")){
							System.out.println("\\"+pAtr.getNamedItem("innerClass"));
							continue;
						}
						
						// XMLに存在するクラスのリストを作成
						list_xml.add(pAtr.getNamedItem("name").getNodeValue());	
						node_Name.add(pAtr.getNamedItem("name").getNodeValue());
						if(pAtr.getNamedItem("usesInternal")!=null){					
							node_branch.add(pAtr.getNamedItem("usesInternal").getNodeValue());
							System.out.println("==="+pAtr.getNamedItem("usesInternal").getNodeValue()); 
						}
					}
							
					// nameに$を含む<classRef>を削除
					NodeList ppChildren = Pnode.getChildNodes();
					
					System.out.println("子要素の数：" + rootChildren.getLength());
					System.out.println("------------------"); 
					
					for (int k=0;k<ppChildren.getLength();k++) {
						Node personNode = ppChildren.item(k);
						NamedNodeMap ppAtr = personNode.getAttributes();
						//if(personNode.getNodeName()){}
						if (ppAtr!=null) {
							if(ppAtr.getNamedItem("name").getNodeValue().matches(".*\\$.*")){
								//System.out.println("\\\\"+ppAtr.getNamedItem("name").getNodeValue());
								continue;
							}
							if(ppAtr.getNamedItem("type").getNodeValue().matches("usedBy")
									||ppAtr.getNamedItem("type").getNodeValue().matches("usesExternal")){
								//System.out.println("\\\\"+ppAtr.getNamedItem("type"));
								continue;
							}
							System.out.println("["+j+"]"+ppAtr.getNamedItem("type").getNodeValue()+":::"+ppAtr.getNamedItem("name").getNodeValue()); 
							usedInternal_temp.add(ppAtr.getNamedItem("name").getNodeValue());
						}
					}
					usedInternal[j]=(String[])usedInternal_temp.toArray(new String[0]);
				}
				
				//////////////////////////////////////////////////////////////////////////
				//未実装
				System.out.println("[["+usedInternal.length+"]]");
				for(int j=0;j<usedInternal.length;j++){
					System.out.println("[["+usedInternal[j].length+"]]");
					for(int k=0;k<usedInternal[j].length;k++)
						System.out.println("["+j+"]["+k+"]"+usedInternal[j][k]);
				}
				System.out.println("------------------");
				//////////////////////////////////////////////////////////////////////////
			}
		}
	/**inport_file=============================*/
		
	/** ----------------------------------------------------
	// 変数リストの作成
	// ---------------------------------------------------*/
		//XMLに存在するクラスのリスト
		System.out.println("------------------");
		for(int i=0;i<list_xml.size();i++){
			//System.out.println("["+i+"]"+list_xml.get(i));
		}
		//ノードの設定
		System.out.println("------------------");
		for(int i=0;i<node_Name.size();i++){
			//System.out.println("["+i+"]"+node_Name.get(i));
		}
		//アークの設定
		System.out.println("------------------");
		for(int i=0;i<node_branch.size();i++){
			//System.out.print("["+i+"]"+node_branch.get(i));
		}
		
		//一番internalの多いものを探す
		for(int i=0;i<node_branch.size();i++)
		    if(internal_max < Integer.parseInt(node_branch.get(i))){
		        internal_max = Integer.parseInt(node_branch.get(i));
		        max_class = i;
		    }
		//System.out.println("\n--max----------------");
		//System.out.print("["+node_Name.get(max_class)+"]"+internal_max+"\n");
		
		//利用関係のないクラスの計算
		for(int i=0;i<node_Name.size();i++)no_used.add(true);
		for(int i=0;i<node_Name.size();i++){
		  for(int k=i;k<node_Name.size();k++){
		    String connect = node_Name.get(k);
		    int key = node_Name.indexOf(connect);
		    no_used.set(i,false);
			no_used.set(key,false);
		  }
		}
		//System.out.println("\n--max----------------");
		for(int i=0;i<node_Name.size();i++){
			//System.out.print("["+i+"]"+no_used.get(i)+"\n");
		}
		divide_package();
	}

	/**パッケージごとに分ける=============================*/
	public void divide_package(){
		//System.out.print("==DIVIDE============\n");
		//パッケージ名を抽出
		for(int i =0;i < node_Name.size();i++){
		    String temp = "";
		    String[] str_group = node_Name.get(i).split("\\.",0);
		    for(int j=0;j<str_group.length-1;j++){
		    	temp = temp+"."+str_group[j];
		    }
		    boolean group_flag = false; 
		    
		    //classgroupに存在すればtrueに
		    for(int j=0;j<classgroup.size();j++){
		        if(temp.equals(classgroup.get(j))){
		            classgroup_number.add(i,j);
		            group_flag = true; 
		        }
		    }
		    //classgroupに存在していなければ追加
		    if(!group_flag){
		         classgroup.add(temp);
		         classgroup_number.add(classgroup.size()-1);
		    }
		}
		
		//パッケージを階層で分類
		for(int i=0;i<classgroup.size();i++){
		    int stl_len = 0;
		    String str1 = classgroup.get(i).replaceAll("\\.","Dooooooooott");
		    classgroup_internal.add(0);
		    for(int j=0;j<classgroup.size();j++){
		        String str2 = classgroup.get(j).replaceAll("\\.","Dooooooooott");
		        str2 += "Dooooooooott";
		        if(str1.indexOf(str2)!=-1){//.のままでは検索できない
		            if(classgroup.get(j).length()>stl_len){
		                classgroup_internal.set(i,j);
		                stl_len = classgroup.get(j).length();
		            }
		        }
		    }
		}
		
		//一番多く上の階層になっているものを探す。
		int max_pac = 0; //一番上の階層
		/*
		$count_num = array();
		for($i =0;$i < count($classgroup_internal);$i++){
		    $count_num[$classgroup_internal[$i]]++;
		}
		$max_pac = 0; //一番上の階層
		for($i =0;$i < count($count_num);$i++){
		    if($count_num[$max_pac]<$count_num[$i]){
		        //$max_pac = i;
		    }
		}*/
		
		//パッケージのカラーパレットを計算しておく
		int color_num = 0;
		int package_num = 0;
		/**------------------------------------------------*/
		for(int i=0;i<classgroup_internal.size();i++){
		    if(classgroup_internal.get(i)== max_pac){
		        classgroup_color1.add(color_num);
		        classgroup_color2.add(0);
		        color_num++;
		        if(color_num>=12)color_num=0;//色の数で変動
		        parent_package.add(package_num);
		        package_num++;
		        //System.out.println(color_num);
		    }
		    else{
		    	classgroup_color1.add(0);
		        classgroup_color2.add(0);
		        parent_package.add(0);
		    }
		}
		
		for(int i=0;i<classgroup_internal.size();i++){
		    if(classgroup_internal.get(i)!=max_pac){
		        classgroup_color1.add(i,classgroup_color1.get(classgroup_internal.get(i)));
		        classgroup_color2.add(i,classgroup_color2.get(classgroup_internal.get(i))+1);
		        parent_package.set(i,parent_package.get(classgroup_internal.get(i)));
		    }
		}	
		
		//System.out.print("==DIVIDE============\n");
		for(int i=0;i<classgroup_color1.size();i++){
			//System.out.println(node_Name.get(i));
			//System.out.println("[0]["+i+"]"+classgroup_color1.get(i));
			//System.out.println("[1]["+i+"]"+classgroup_color2.get(i)+"\n");
		}
		//System.out.print("\n==DIVIDE============\n");
		for(int i=0;i<parent_package.size();i++){
			//System.out.println("["+i+"]"+parent_package.get(i));
		}
	}
	/**ノード=============================*/
	public void graph_draw() {
		ArrayList<ClassNode> nodes = new ArrayList<ClassNode>();
		
		//ノードの定義
		for(int i=0;i<node_Name.size();i++){
			if(i==max_class){
				//nodes.add(new ClassNode(i,center.x,center.y,classgroup_number.get(i)));
			}else{
				//nodes.add(new ClassNode(i,center.x-250+(int)(Math.random()*500),center.y-250+(int)(Math.random()*500),classgroup_number.get(i));
			}
		}
		
		//利用関係が無いノードを消す
		for(int i=0;i<nodes.size();i++){
			if(no_used.get(i))nodes.get(i).not_use();
		}
		
		//演算クラスにノードを代入
		ForceDirectedGraph graph = new ForceDirectedGraph(nodes);
		
		//アークの定義
		for(int i=0;i<node_branch.size();i++){
			for(int k=0;k<node_branch.size();k++){
				//connect = (string)$xml->classes->class[$i]->classRef[$k]->attributes()->name;
				//key = array_search(connect, node);
				//graph.connect(i,key);
			}
		}
	}
}

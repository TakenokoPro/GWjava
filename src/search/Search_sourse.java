package search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Search_sourse {
	/**=============================*/	
	/**object**/
	static String in_Path = "result\\sourse.txt";//""内に分析したいフォルダを(\は２連続で)
	static String out_path = "result\\out.csv";
	int count = 0;
	ArrayList<String> split_str;
	ArrayList<class_info> class_infos = new ArrayList<class_info>();
	String path = "";//パッケージ
	int readline = 1;//読み取り行
	
	//paint method
	ClassInfoTable classTable = new ClassInfoTable();
	/**=============================*/
	/**main**/
	public Search_sourse() {
		String_Clear();
		execute();
	}
	/**=============================*/
	/**method**/
	// 探索処理
	public void execute() {
		/**ファイル名の取得*/
		String filePath = in_Path;
		String search_str="";
		
		//メインメソッドの抽出
		//search_main(filePath);
		
		//クラス
		search_class(filePath);
		
		//new ...を抽出()
		search_new(filePath);
		
		//メソッド呼び出し()
		search_method(filePath);
		
		//変数 ...を抽出()
		search_object(filePath);
		
		//テーブルの表示
		for(int i=0;i<class_infos.size();i++){
			class_info temp = class_infos.get(i);
			String ext_get = temp.extend_get();
			String int_get = temp.interface_get();
			ClassInfoTable.Add(i,temp.class_name,temp.kind,ext_get,int_get);
		}
		//classTable.DisplayTable();
	}
	public ArrayList<String> split_class(String str) {
		String[] return_str = str.split("(\\s)|(,)|(\\{)", 0);
		ArrayList<String> result = new ArrayList<String>();
		for(int i=0;i<return_str.length;i++){
			if(!return_str[i].matches("(^\\s*$)|(\\{)"))
				result.add(return_str[i]);	
		}
		return result;
	}
	public ArrayList<String> split_token(String str) {
		String[] return_str = str.split("" + token_lure(), 0);
		ArrayList<String> result = new ArrayList<String>();
		for(int i=0;i<return_str.length;i++){
			if(return_str[i].matches("/")&&return_str[i+1].matches("/"))break;
			if(return_str[i].matches("\"")){
				String temp="";
				result.add("\"");
				while(return_str[i].matches("\"")){
					temp += return_str[i];
					i++;
				}
				result.add(temp);
				result.add("\"");
			}
			if(!return_str[i].matches("(^\\s*$)")){
				result.add(return_str[i]);
			}
		}
		return result;
	}
	public String token_lure() {
		return ""
				+ "(\\s)"
				+ "|(?<=\\=)|(?=\\=)"
				//+ "|(?<=,)|(?=,)"
				+ "|(?<=;)|(?=;)"
				+ "|(?<=\\{)|(?=\\{)"
				+ "|(?<=\\})|(?=\\})"
				+ "|(?<=\\()|(?=\\()"
				+ "|(?<=\\))|(?=\\))"
				//+ "|(?<=\\[)|(?=\\[)"
				//+ "|(?<=\\])|(?=\\])"
				+ "|(?<=\\+)|(?=\\+)"
				+ "|(?<=-)|(?=-)"
				+ "|(?<=\\*)|(?=\\*)"
				+ "|(?<=/)|(?=/)"
				//+ "|(?<=DottttT)|(?=DottttT)"
				+ "|(?<=(!))|(?=(!))"
				+ "|(?<=\\|)|(?=\\|)";
	}

	//探索関数
	public void search_main(String filePath){
		try{
			File javaFile = new File(filePath);
			BufferedReader br = new BufferedReader(new FileReader(javaFile));
			String str = br.readLine();
			while(str != null){
				str = str_encode(str);
				if(str.matches("(.*\\s(void main).*)")){System.out.print(str);} 
				str = br.readLine();
			}
			br.close();
		}
		catch(FileNotFoundException e){System.out.println(e);}
		catch(IOException e){System.out.println(e);}
	}
	public void search_class(String filePath){
		try{
			File javaFile = new File(filePath);
			BufferedReader br = new BufferedReader(new FileReader(javaFile),1024 * 1024);
			String str = br.readLine();
			readline=1;
			while(str != null){
				str = str_encode(str);
				if(str.matches("\\s*(package)\\s.*;")){
					String[] temp = str.split("[\\s;]");
					for(int i=0;i<temp.length;i++)
						if(temp[i].equals("package"))path = str_decode("."+temp[i+1]);
				}
				if(str.matches("(.*\\s(class)\\s.*(\\{))|(.*\\s(interface)\\s.*)")){
					if(split_class(str)!=null){
						split_str = split_class(str);
						for(int i=0;i<split_str.size();i++)System.out.print("||"+split_str.get(i));
						System.out.print("\n");
						OutputFunction(split_str,count++);
					}
				}
				readline++;
				str = br.readLine();
			}
			br.close();
		}
		catch(FileNotFoundException e){System.out.println(e);}
		catch(IOException e){System.out.println(e);}
	}
	public void search_method(String filePath){
		try{
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String str = br.readLine();
			readline = 1;//読む行
			while(str != null){
				str = str_encode(str);
				//定義文
				if(str.matches(".*\\s*[A-Za-z_][0-9A-Za-z_<>]*(\\()[^\\)]*(\\))\\s*(\\{).*")){
					split_str = split_token(str);
					String temp_str = "";
					String type_str = "";
					for(int i=1;i<split_str.size();i++){
						if(split_str.get(i).equals("(")&&!reserved_word(split_str.get(i-1))){
							temp_str = split_str.get(i-1);
							if(i>1){
								type_str = split_str.get(i-2);
								if(split_str.get(i-2).equals("]"))
									type_str = split_str.get(i-4)+split_str.get(i-3)+split_str.get(i-2);
									String_Write(readline+",method,"//+filePath
											+",definition,"+temp_str+","+str_decode(str)+"\n");////抽出
						}
							class_infos.get(returnClassNumToLine(readline)).definitionMethod_add(type_str,str_decode(temp_str),readline);
						}
					}
				}
				//呼び出し文
				else if(str.matches(".*\\s*[A-Za-z_][0-9A-Za-z_<>]*(\\().*(\\)).*")){
					split_str = split_token(str);
					String temp_str = "";
					temp_str = "";
					for(int i=1;i<split_str.size();i++){
						if(split_str.get(i).equals("(")&&!reserved_word(split_str.get(i-1))){
							if(!split_str.get(i-1).matches("[A-Za-z_][0-9A-Za-z_]*"))continue;
							temp_str = split_str.get(i-1);
							//if(i==1)continue;
							String_Write(readline+",method,"//+filePath+""
									+ ",call,"+temp_str+","+str+"\n");////抽出
							class_infos.get(returnClassNumToLine(readline)).callMethod_add(str_decode(temp_str),readline);
						}
					}
				}
				str = br.readLine();
				readline++;
			}
			br.close();
		}
		catch(FileNotFoundException e){System.out.println(e);}
		catch(IOException e){System.out.println(e);}
	}
	public void search_object(String filePath){
		try{
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String str = br.readLine();
			readline = 1;//読む行
			
			//予約語に含まれる型
			List<String> str_identifier = str_identifier();
			List<String> reserved_word = reserved_word();
			
			while(str != null){
				str = str_encode(str);
				if(str.matches(".*[A-Za-z_][0-9A-Za-z_<>\\[\\]]*\\s+[A-Za-z_][0-9A-Za-z_\\[\\],]*\\s*(=|;).*"
						+ "")){
					split_str = split_token(str);
					for(int i=2;i<split_str.size()+1;i++)
						if(split_str.get(i-2).matches("[A-Za-z_][0-9A-Za-z_<>\\[\\]]*")
							&& split_str.get(i-1).matches("[A-Za-z_][0-9A-Za-z_<>\\[\\],]*")
							&& split_str.get(i).matches("(=|;)")
							){
							boolean reserved_word_flag = false;
							for(int ide=0;ide<reserved_word.size();ide++)
								if(split_str.get(i-2).matches(reserved_word.get(ide))){
									reserved_word_flag = true;
								}
							if(!reserved_word_flag)
								class_infos.get(returnClassNumToLine(readline)).object_types_add(
									str_decode(split_str.get(i-2)),str_decode(split_str.get(i-1)),readline);
						}
				}
				readline++;
				str = br.readLine();
			}
			br.close();
		}
		catch(FileNotFoundException e){System.out.println(e);}
		catch(IOException e){System.out.println(e);}
	}
	public void search_new(String filePath){
		try{
			File javaFile = new File(filePath);
			BufferedReader br = new BufferedReader(new FileReader(javaFile));
			String str = br.readLine();
			int readline = 1;//読む行
			while(str != null){
				str = str_encode(str);
				if(str.matches(".*\\s*[A-Za-z_][0-9A-Za-z_]*\\s*=\\s(new)\\s[A-Za-z_][0-9A-Za-z_]*.*")){
					split_str = split_token(str);
					String temp_str = "";
					for(int i=0;i<split_str.size();i++)
						if(split_str.get(i).equals("new")){
							class_infos.get(returnClassNumToLine(readline)).new_class_add(split_str.get(i+1),split_str.get(i-2),readline);
							temp_str += str_decode(split_str.get(i))+" ";
							String_Write(readline+",new,"+temp_str+"\n");
					}
				}
				readline++;
				str = br.readLine();
			}
			br.close();
		}
		catch(FileNotFoundException e){System.out.println(e);}
		catch(IOException e){System.out.println(e);}
	}
	public void search_obj(String filePath,String search_str){
		try{
			File javaFile = new File(filePath);
			BufferedReader br = new BufferedReader(new FileReader(javaFile));
			String str = br.readLine();
			while(str != null){
				str = str_encode(str);
				if(str.matches(search_str)){
					split_str = split_token(str);
					String temp_str = "";
					for(int i=0;i<split_str.size();i++)
						temp_str += str_decode(split_str.get(i))+" ";
					String_Write(temp_str+"\n");
				}
				str = br.readLine();
			}
			br.close();
		}
		catch(FileNotFoundException e){System.out.println(e);}
		catch(IOException e){System.out.println(e);}
	}
	
	// クラスの出力処理  
	public void OutputFunction(ArrayList<String> str,int num){
		int i=0;
		String Modifier = "";
		String ClassKind = "";
		String ClassName = "";
		String Stringkind = "";
		System.out.println(str.size()+","+i+","+num+"::"+class_infos.size());
		
		if(str.get(i).equals("static")){
			Modifier += ","+str.get(i);
			i++;
		} else {Modifier += ",";}
		
		if(str.get(i).equals("public")||str.get(i).equals("protected")||str.get(i).equals("private")){
			Modifier += str.get(i);
			i++;
		} 
		
		if(str.get(i).equals("abstract")||str.get(i).equals("final")){
			Modifier += ","+str.get(i);
			i++;
		} else {Modifier += ",";}
		
		if(str.get(i).equals("static")){
			Modifier += ","+str.get(i);
			i++;
		} else {Modifier += ",";}
		
		if(str.get(i).equals("class")){
			ClassKind = "class";i++;
			ClassName += str.get(i);i++;
			class_infos.add(new class_info(ClassName,ClassKind,path,readline));
			path="";
		}
		else if(str.get(i).equals("interface")){
			ClassKind = "interface";i++;
			ClassName += str.get(i);i++;
			class_infos.add(new class_info(ClassName,ClassKind,path,readline));
			path="";
		}
		else {
			class_infos.add(new class_info("","","",readline));
		}
		
		//クラスの代入
		while(i<str.size()){
			if(str.get(i).equals("extends")){
				Stringkind = "extends";
			} else if(str.get(i).equals("implements")){
				Stringkind = "implements";
			} else{
				if(Stringkind.equals("extends"))
					(class_infos.get(num)).extendString.add(str_decode(str.get(i)));
				else if(Stringkind.equals("implements"))
					(class_infos.get(num)).interfaceString.add(str_decode(str.get(i)));
			}
			i++;
		}
		return;
	}
	
	/*正規表現で無効になるメタ文字*/
	public String str_encode(String str){
		str = str.replaceAll("\\.","DottttT");
		return str;
	}	
	public String str_decode(String str){
		str = str.replaceAll("DottttT","\\.");
		return str;
	}

	//変数の型
	public List<String> str_identifier(){
		List<String> str = new ArrayList<String>();
		str.add("byte");
		str.add("short");
		str.add("int");
		str.add("long");
		str.add("float");
		str.add("double");
		str.add("char");
		str.add("boolean");
		str.add("string");
		return str;
	}

	//予約語の一覧	
	public List<String> reserved_word(){
		List<String> str = new ArrayList<String>();
		//A
		str.add("abstract");str.add("assert");
		//B
		/*str.add("doolean");*/str.add("break");/*str.add("byte");*/
		//C
		str.add("case");str.add("catch");str.add("char");
		str.add("class");str.add("const");str.add("continue");
		//D
		str.add("default");str.add("do");
		/*str.add("double");*/
		//E
		str.add("else");str.add("extends");
		//F
		str.add("final");str.add("finally ");str.add("float");str.add("for");
		//G
		str.add("goto");
		//I
		str.add("if");str.add("implements");str.add("import");str.add("instanceof");
		/*str.add("int");*/str.add("interface");
		//L
		/*str.add("long");*/
		//N
		str.add("native");str.add("new");
		//P
		str.add("package");str.add("private");str.add("protected");str.add("public");
		//R
		str.add("return");
		//S
		str.add("short");str.add("static");str.add("strictfp");str.add("super");
		str.add("switch");str.add("synchrnized");
		//T
		str.add("this");str.add("throw");str.add("throws");str.add("transient");str.add("try");
		//V
		str.add("void");str.add("volatile ");
		//W
		str.add("while");	
		return str;
	}
	public boolean reserved_word(String str){
		List<String> list = reserved_word();
		for(int i=0;i<list.size();i++){
			if(list.get(i).equals(str))return true;
		}
		return false;
	}
	
	/*文章の出力*/
	void String_Write(String str){
		try {
			FileWriter fw = new FileWriter(out_path, true);
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			pw.print(str);
			pw.close();
		} catch (IOException e) {e.printStackTrace();}
	}
	static void String_Clear(){
		/*try {
			FileWriter fw = new FileWriter(out_path, false);
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			pw.println("");
			pw.close();
		} catch (IOException e) {e.printStackTrace();}*/
	}
	
	//該当する行のクラスを返す
	public String returnClassToLine(int line){
		int ans = 0;
		for(int i=0;i<class_infos.size();i++){
			if(line>=class_infos.get(i).startline_get())
				ans = i;
			else 
				break;
		}
		return class_infos.get(ans).name_get();
	}
	public int returnClassNumToLine(int line){
		int ans = 0;
		for(int i=0;i<class_infos.size();i++){
			if(line>=class_infos.get(i).startline_get())
				ans = i;
			else 
				break;
		}
		return ans;
	}
	
	//getter	
	public ArrayList<class_info> get_classinfo(){
		return class_infos;
	}
}

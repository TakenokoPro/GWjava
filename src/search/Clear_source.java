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


public class Clear_source {

	/**=============================*/	
	/**object**/
	static String path_str = "Z:\\Dropbox\\";
	//path_str = "C:\\"; 
	//static String in_Path = path_str+"Dropbox\\GraduationWork\\ResearchResults\\GeoAPI\\1.0.0\\sources\\org\\opengis";//""内に分析したいフォルダを(\は２連続で)
	//static String in_Path = "C:\\pleiades\\workspace\\GWjava\\src";//""内に分析したいフォルダを(\は２連続で)
	static String in_Path = "Z:\\Eclipse\\GWjava\\src";
	static String out_path = "result\\sourse.txt";
	//static String out_path= "C:\\pleiades\\workspace\\ReadFolder\\out\\out.txt";
	int count = 0;
	ArrayList<String> split_str;
	String temp_str="";
	//comment
	boolean comment_flag = false; //単一コメント
	boolean block_comment_flag = false; //ブロックコメント
	/**=============================*/
	/**main**/
	public Clear_source(){
		String_Clear();
		File readfolder = new File(in_Path);
		readFolder(readfolder);
	}
	/**=============================*/
	/**method**/
	//フォルダ読み込み
	public void readFolder( File dir ) {
	File[] files = dir.listFiles();
	if( files == null )return;
		for( File file : files ) {
			if( !file.exists() ){
				continue;
			}else if( file.isDirectory() ){
				readFolder( file );
			}else if( file.isFile() ){
				execute( file );
			}
		}
	}
	
	// 探索処理
	public void execute( File file ) {
		/**ファイル名の取得*/
		String filePath = file.getPath();
		String parentDirectory = in_Path.replaceAll("\\\\","\\\\\\\\");
		String AbsolutePath = filePath.replaceFirst(parentDirectory,"");
		if(AbsolutePath.substring(0,1).equals("\\")){
			AbsolutePath = AbsolutePath.replaceFirst("\\\\","");
		}
		
		/**ファイルの拡張子を取得*/
		String[] splitPath = AbsolutePath.split("\\.");
		String extension = splitPath[splitPath.length-1];
		String search_str="";
		
		/**javaファイルの時の処理*/
		if(extension.equals("java")){
			System.out.println(splitPath[0]);
			replace_n(filePath);
		}
	}	
	public void replace_n(String filePath){
		try{
			File javaFile = new File(filePath);
			BufferedReader br = new BufferedReader(new FileReader(javaFile));
			String str = br.readLine();
			while(str != null){
				//System.out.println("\n\n="+str+"=");
				//str += str_encode(temp_str+str);
				String[] str_split = str.split( (""
						+ "(?<=;)|(?<=\\{)|(?<=\\})"
						+ "|(?<=\\s(/)(\\*))|(?=\\s(/)(\\*))"
						+ "|(?<=(\\*)(/))|(?=(\\*)(/))"
						+ "|(?<=(//))|(?=(//))"
						+ "|(?<=(@))|(?=(@))"
						),0);
				for(int i=0;i<str_split.length;i++){
					//System.out.print("["+str_split[i]+"]");
					str_split[i] = str_split[i].replaceAll("\t","");
					if(str_split[i].equals("\\s*"))break;
					if(str_split[i].equals(""))break;
					if(str_split[i].matches("//")){
						comment_flag = true;
						break;
					}
					if(str_split[i].matches((".*(/)(\\*).*"))){
						block_comment_flag = true;
					}
					/*if(str_split[i].matches("@")){
						i++;
					}*/
					if(!block_comment_flag){
						if(str_split[i].matches(".*;")
								||str_split[i].matches(".*\\{")
								||str_split[i].matches(".*\\}")){
							String_Write(str_split[i]+"\n");
						} else {
							String_Write(str_split[i]);
						}
					}
					if(block_comment_flag && str_split[i].matches("\\*/")){
						block_comment_flag = false;
					}
					
				}
				//if(!block_comment_flag&&!comment_flag)
					//temp_str = str_split[str_split.length-1];
				comment_flag = false;
				//block_comment_flag = false;
				//String_Write(str);
				str = br.readLine();
			}
			br.close();
		}
		catch(FileNotFoundException e){System.out.println(e);}
		catch(IOException e){System.out.println(e);}
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

	/*文章の出力*/
	void String_Write(String str){
		try {
			FileWriter fw = new FileWriter(out_path, true);
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			pw.print(str);
			//System.out.print(str);
			pw.close();
		} catch (IOException e) {e.printStackTrace();}
	}
	static void String_Clear(){
		try {
			FileWriter fw = new FileWriter(out_path, false);
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			pw.println("");
			pw.close();
		} catch (IOException e) {e.printStackTrace();}
	}
}

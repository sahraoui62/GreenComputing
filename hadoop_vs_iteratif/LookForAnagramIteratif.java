package iteratif;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class LookForAnagramIteratif {

	private static String dico = "dico/dico.txt";
	private static Map<String,String> map = new HashMap<String,String>();
	
	public static String getIndex(String word){
		char[] data = word.toCharArray();
		Arrays.sort(data);
		return new String(data);
	}
	
	public static void createAnagramList(){
		try{
			InputStream ips=new FileInputStream(dico); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String word;
			while ((word=br.readLine())!=null){
				String index = getIndex(word);
				if(!map.containsKey(index)){
					map.put(index,word);
				}else{
					map.put(index,map.get(index) + " " + word);
				}
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	public static void showMap(Map<String, String> map) throws IOException{
		Set<String> listKeys=map.keySet(); 
		Iterator<String> iterateur=listKeys.iterator();
		while(iterateur.hasNext()){
			Object key= iterateur.next();
			//System.out.println( key + "\t" + map.get(key));
		    PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter (new File("output.txt"))));
		    pw.println( key + "\t" + map.get(key));
		}
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("PID : " +ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
		System.out.println("Start ?");
		Scanner sc = new Scanner(System.in);
		if(sc.nextLine().equals("yes")){
			createAnagramList();
			showMap(map);
		}
	}
}

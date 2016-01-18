import java.lang.management.ManagementFactory;
import java.util.Random;
import java.util.Scanner;

public class Main {

	private static String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	private static String passwordGenerated;
	
	private static String generateAN(int size){
		String res = "";
		Random r = new Random();
		for(int i = 0 ; i < size ; i++){
			int randomIndex = r.nextInt(alphaNumeric.length()); // nombre dans [0;size[
			res += alphaNumeric.charAt(randomIndex);
		}
		passwordGenerated = res;
		return res;
	}
	
	private static String retrievePassword(){
		String res = "";
		for(int i = 0 ; i < passwordGenerated.length() ; i++){
			for(int j = 0 ; j < alphaNumeric.length() ; j++){
				if(alphaNumeric.charAt(j) == passwordGenerated.charAt(i)){
					res += alphaNumeric.charAt(j);
				}
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println("PID : " +ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
		System.out.println("Start ?");
		generateAN(75000);
		Scanner sc = new Scanner(System.in);
		if(sc.nextLine().equals("yes")){	
			System.out.println("*** MOT DE PASSE RETROUVE ***");
			System.out.println(retrievePassword());
		}
	}
}
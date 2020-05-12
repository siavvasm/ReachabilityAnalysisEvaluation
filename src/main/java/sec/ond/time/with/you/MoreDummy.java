package sec.ond.time.with.you;

import java.io.IOException;
import java.util.Scanner;

public class MoreDummy {

	private void executeThisMoreDummyCode() throws IOException {
		
		// 1. Retrieve data from the console
		Scanner scanner = new Scanner(System.in);
		String str1 = Integer.toString(scanner.nextInt()); /* TAINTED */
		
		// 2. Pass the tainted data to another method
		printStrMoreDummy(str1);
	}
	
	public static void printStrMoreDummy(String str) {
		System.out.println(str);
	}
	
	public static void fun2(String str1, String str2) {
		System.out.println(str2);
	}
	
	public static void fun3(String str1, String str2, String str3) {
		System.out.println(str3);
	}
	
	public static void fun4(String str1, String str2, String str3) {
		System.out.println(str1);
		fun2(str2,str3);
		
	}


}
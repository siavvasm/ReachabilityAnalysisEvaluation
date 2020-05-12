package utils;

import java.util.Scanner;

import evaluation.Dummy;
import sec.ond.time.with.you.MoreDummy;

public class MainUtils {
	
	public static String echoString(String str) {
		// Just return the String that was received
		return str;
	}
	
	public static String updateString(String str) {
		return str.replace("a", "b");
	}
	
	public static boolean isEmpty(String str) {
		if (str.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void broadcastString(String str) {
		String temp = "";
		int x = 1;
		
		temp = str;
		
		MoreDummy.printStrMoreDummy(str);
		MoreDummy.printStrMoreDummy(temp);
//		Dummy.printStr(str);
//		System.out.println(str);
		
		// 1. Get an integer from the console
//		Scanner scanner = new Scanner(System.in);
//		int retrievedInt = scanner.nextInt(); /* TAINTED */
//		System.out.println(retrievedInt);
//		
	}

}

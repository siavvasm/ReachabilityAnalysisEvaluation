package utils;

import java.util.Scanner;

import com.model.Monkey;

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
		
		/*
		 * BUG: When the "str" parameter contains a tainted variable, the produced report contains only 
		 *      the path of the last method that receives it as an input (i.e., the println() method in 
		 *      the given example below)
		 *      
		 * BUG: When the "str" parameter contains a tainted variable, its aliases are not considered
		 *      at all. In other words, in the present example the "temp" variable is not considered
		 *      tainted, whereas its path is not traced (i.e., method call: MoreDummy.printStrMoreDummy(temp).
		 */
		MoreDummy.printStrMoreDummy(str);
		MoreDummy.printStrMoreDummy(temp);
		Dummy.printStr(str);
		System.out.println(str);
		
		// 1. Get an integer from the console
		Scanner scanner = new Scanner(System.in);
		int retrievedInt = scanner.nextInt(); /* TAINTED */
		System.out.println(retrievedInt);
//		
	}
	
	public static void printMonkeyName(Monkey monkey) {
		System.out.println(monkey.getName());
	}

}

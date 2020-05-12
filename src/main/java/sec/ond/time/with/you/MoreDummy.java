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

}
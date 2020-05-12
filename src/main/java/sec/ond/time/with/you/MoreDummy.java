package sec.ond.time.with.you;

import java.io.IOException;
import java.util.Scanner;

public class MoreDummy {

	private void executeThisMoreDummyCode() throws IOException {
		Scanner scanner = new Scanner(System.in);
		String str1 = Integer.toString(scanner.nextInt());
		printStrMoreDummy(str1);
	}
	public static void printStrMoreDummy(String str) {
		System.out.println(str);
	}

}
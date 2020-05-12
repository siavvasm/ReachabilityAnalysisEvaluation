package evaluation;

import java.io.IOException;
import java.util.Scanner;
import sec.ond.time.with.you.MoreDummy;

public class Dummy {

	private void executeThisCode() throws IOException {
		
		// 1. Retrieve data from the console
		Scanner scanner = new Scanner(System.in);
		
		// TAINTED!
		String str = Integer.toString(scanner.nextInt()); /* TAINTED */
		
		printStr(str); /* SINK */
		
		
		String x = str; /* TAINTED */
		String y = str + ""; /* TAINTED */
		
		MoreDummy moreDummy = new MoreDummy();
		
		
		moreDummy.printStrMoreDummy(x); 
		MoreDummy.printStrMoreDummy(y);
	}
	private static void printStr(String str) {
		System.out.println(str);
	}

}
package evaluation;

import java.io.BufferedReader;
import java.io.FileReader;
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
		
		/*
		 * Read Content from files
		 */
		
		// 1. Create the appropriate readers
		
		try {
			FileReader fr = new FileReader("path/to/file");
			BufferedReader br = new BufferedReader(fr);
			
			// 2. Read a String from the selected file
			String fileString = br.readLine(); /* TAINTED */
			
			// 3. Feed the String to different methods
			
			// 3.1 Print the String to the console
			System.out.println("The received String is: " + fileString);
			
			// 3.2 Print the String using a local method
			printStr(fileString);
			
			// 3.3 Print the String using a internal method that belongs to the same project
			MoreDummy.printStrMoreDummy(fileString);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private static void printStr(String str) {
		System.out.println(str);
	}

}
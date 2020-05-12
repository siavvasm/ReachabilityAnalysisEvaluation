package evaluation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import sec.ond.time.with.you.MoreDummy;
import utils.MainUtils;

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
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader("path/to/file");
			br = new BufferedReader(fr);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		// 2. Read a String from the selected file
		String fileString = br.readLine(); /* TAINTED */
		
		// 3. Feed the String to different methods
		
		// 3.1 Print the String to the console
		System.out.println("The received String is: " + fileString);
		
		// 3.2 Print the String using a local method
		printStr(fileString);
		
		// 3.3 Print the String using a internal method that belongs to the same project
		MoreDummy.printStrMoreDummy(fileString);

/*
 * 
 * 
 * PROBLEMATIC CODE FRAGMENT
 * 
 * 
 * 
 */
		// 4. Create an alias
//		String sith = "Non bad String";
//		
//		String fileString2 = fileString + sith; /* TAINTED */
//		String fileString3 = sith + fileString; /* TAINTED */
//		String fileString4 = fileString + y; /* TAINTED */
//		
//		// 5. Call different methods
//		MoreDummy.printStrMoreDummy(fileString2 + "");
//		printStr(fileString3);
//		printStr(fileString4);
		
		
		/*
		 * Check the return value
		 */
		
		// COMMENT: The tool is able to mark this variable as tainted. 
		// COMMENT: However, this wasn't caused by internal check (semantics of the called function). 
		// COMMENT: We have decided that if a tainted value is passed as a parameter to a method, then if the method returns a value, it may probably be tainted. 
		// TODO: Check if we can check the body of the method and verify whether the method returns an actually tainted value (HARD)
		// TODO: Just check the type of the variable. If it is BOOLEAN don't mark it as tainted (EASY)
		String s = returnString(str); /* TAINTED */
		MoreDummy.printStrMoreDummy(s);
		String s1 = MainUtils.echoString(s);
		String s2 = MainUtils.updateString(s);
		boolean flag = MainUtils.isEmpty(s);
		
		String s3 = s.replace("a", "b");
		
		MainUtils.broadcastString(s3);
		

		
	}
	
	public static void printStr(String str) {
		System.out.println(str);
	}
	
	public String returnString(String str) {
		return str;
	}

}
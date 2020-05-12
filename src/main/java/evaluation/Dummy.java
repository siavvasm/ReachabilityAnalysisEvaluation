package evaluation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
 * BUG: The tool is unable to parse these import statements 
 * ERROR MESSAGE: Semantic Error: no visible type named org.jdom.Document
 */
//import org.jdom.Document;
//import org.jdom.Element;
//import org.jdom.JDOMException;
//import org.jdom.input.SAXBuilder;

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
		/*
		 * BUG: The tool things that the concatenation is the invocation of the append() method. 
		 * 		So it creates a path based on this method call.
		 * 		In addition to this, it neglects the actual method call (i.e., the path is not 
		 *      included in the report)
		 *      For example, in the call below, the tool reports a path to the append() method,
		 *      whereas it does not report a path to the System.out.println() method. 
		 */
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
		
		/*
		 * BUG: The tool things that the concatenation is the invocation of the append() method. 
		 * 		So it creates a path based on this method call.
		 *      These paths MUST not be reported. 
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
		
		// COMMENT: The tool misses the tainted variables and paths created by aliases of the s3 variable!
		MainUtils.broadcastString(s3);
		
		
		/*
		 * MULTIPLE PARAMETERS...
		 */
		String s4 = s3;
		String s5 = s3;
		String s6 = s3;
		String s7 = s3;
		String s8 = s3;
		String s9 = s3;
		String s10 = s3;
		String s11 = s3;
		String s12 = s3;
		
		MoreDummy.fun2(s3,"");
		MoreDummy.fun2("",s3);
		MoreDummy.fun3(s3,"","");
		MoreDummy.fun3("",s3,"");
		MoreDummy.fun3("","",s3);
		MoreDummy.fun2("",s3);
		MoreDummy.fun3("",s3,"");
		MoreDummy.fun3(s3,s11,s12);
		MoreDummy.fun4(s11,s12,s3);
		
		/*
		 * Another dangerous function...
		 * Import String data from an XML file... 
		 */
		
		/*
		 * BUG: The tool is unable to parse these import statements 
		 * ERROR MESSAGE: Semantic Error: no visible type named org.jdom.Document
		 */

//		SAXBuilder builder = new SAXBuilder();
//		Document doc = null;
//		try {
//			doc = builder.build(new File(""));
//		} catch (JDOMException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Element root = (Element) doc.getRootElement();
//		
//		String s13 = root.getAttributeValue("name");
		
		/*
		 * METHOD CALL AS A PARAMETER
		 */
		
		/*
		 * BUG: Similarly to the String concatenation, the tool detects that the tainted 
		 *      variable s3 executes the replace() method. Then it creates a path for this call:
		 *      
		 *      {
                    "variable_name": "s3",
                    "function_name": "executeThisCode",
                    "dangerous_function_name": "java.util.Scanner.nextInt",
                    "path": [
                        {
                            "function_name": "executeThisCode",
                            "class_name": "evaluation.Dummy",
                            "parameter": -1
                        },
                        {
                            "function_name": "replace",
                            "class_name": " java.lang.String",
                            "parameter": -1
                        }
                    ]
                },

		 *	 This path MUST not be created
		 * 	In addition to this, the actual path (i.e., the call of the method MainUtils.echoString()) is 
		 * 	not reported.
		 *      
		 */
		MainUtils.echoString(s3.replace("a", "b"));
		
		/*
		 * OBJECT...
		 */
		Monkey monkey = new Monkey();
		
		monkey.setX(1);
		monkey.setY(2);
		monkey.setName(s3);
		
		MainUtils.printMonkeyName(monkey);

		
	}
	
	public static void printStr(String str) {
		System.out.println(str);
	}
	
	public String returnString(String str) {
		return str;
	}

}
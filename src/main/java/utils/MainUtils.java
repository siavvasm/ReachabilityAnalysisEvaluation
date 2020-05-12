package utils;

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

}

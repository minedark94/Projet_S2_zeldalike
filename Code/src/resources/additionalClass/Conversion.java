package resources.additionalClass;

public class Conversion {
	public final static char toChar(String s) {
		if(s.length() == 1)
			return s.charAt(0);
		else
			throw new IllegalArgumentException("PROBLEM WHILE CONVERTING TO CHAR AND THE ERROR IS IN : " + s);
	}

	public final static int toInt(String s) {
		int result ;
		try {
			result = Integer.parseInt(s);
		}catch(NumberFormatException e) {
			throw new IllegalArgumentException ("PROBLEM OCCURED WHILE CONVERTING TO INT OF : " + s);
		}
		
		return result;
	}
}

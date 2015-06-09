package raindrop.global;

public class Utils {
	
	public static String assembleLogDebugTag(String clsName, String methodName){
		StringBuffer buffer = new StringBuffer(clsName);
		buffer.append("::");
		buffer.append(methodName);
		return buffer.toString();
	}
}

package telran.performance;

public class JoinStringsImpl implements JoinStrings {

	@Override
	public String join(String[] strings, String delimiter) {
		String strRes = strings[0];
		for (int i = 1; i < strings.length; i++) {
			strRes += delimiter + strings[i];
		}
		return strRes.toString();
	}

}

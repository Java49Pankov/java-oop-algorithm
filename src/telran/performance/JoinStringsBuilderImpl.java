package telran.performance;

public class JoinStringsBuilderImpl implements JoinStrings {

	@Override
	public String join(String[] strings, String delimiter) {
		StringBuilder strBuilder = new StringBuilder(strings[0]);
		for (int i = 1; i < strings.length; i++) {
			strBuilder.append(delimiter).append(strings[i]);
		}
		return strBuilder.toString();
	}
}

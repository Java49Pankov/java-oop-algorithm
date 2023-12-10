package telran.strings;

public class JoinStringStandardImpl implements JoinStrings {

	@Override
	public String join(String[] strings, String delimiter) {

		return String.join(delimiter, strings);
	}

}

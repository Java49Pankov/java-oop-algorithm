package telran.strings.test;

import telran.strings.*;

import org.junit.jupiter.api.Test;

public class StringTests {
	private static final int N_STRINGS = 1000;
	private static final int N_RUNS = 1000;
	String[] strings = { "Hello", "Hello", "Hello" };
	String expected = "Hello#Hello#Hello";

	@Test
	void joinBuilderTest() {
		JoinStrings js = new JoinStringsBuilderImpl();
		String[] strings = getStrings();
		runTest(js, strings);
	}

	@Test
	void joinStringsTest() {
		JoinStrings js = new JoinStringsImpl();
		String[] strings = getStrings();
		runTest(js, strings);
	}

	private void runTest(JoinStrings js, String[] strings) {
		for (int i = 0; i < N_RUNS; i++) {
			js.join(strings, "#");
		}
	}

	private String[] getStrings() {
		String[] res = new String[N_STRINGS];
		for (int i = 0; i < N_STRINGS; i++) {
			res[i] = "Hello";
		}
		return res;
	}
}

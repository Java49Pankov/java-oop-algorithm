package telran.performance;

import java.util.Arrays;

import telran.strings.*;

public class JoinStringsPerformanceAppl {

	private static final int N_STRINGS = 1000;
	private static final int N_RUNS = 1000;

	public static void main(String[] args) {
		String[] strings = getStrings();
		String testNameString = getTestName("JoinStringsImpl");
		String testNameStringBuilder = getTestName("JoinStringsBuilderImpl");
		JoinStringsImpl jsi = new JoinStringsImpl();
		JoinStringsBuilderImpl jsbi = new JoinStringsBuilderImpl();
		JoinStringsPerformanceTest testStringBuilder = new JoinStringsPerformanceTest(testNameStringBuilder, N_RUNS,
				strings, jsbi);
		JoinStringsPerformanceTest testString = new JoinStringsPerformanceTest(testNameString, N_RUNS, strings, jsi);
		testStringBuilder.run();
		testString.run();

	}

	private static String getTestName(String className) {

		return String.format("%s; Number of the strings is %d", className, N_STRINGS);
	}

	private static String[] getStrings() {
		String[] res = new String[N_STRINGS];
		Arrays.fill(res, "string");
		return res;
	}

}
package telran.performance;

public class JoinStringsPerformanceAppl {
	private static int N_STRINGS = 1000;

	public static void main(String[] args) {
		JoinStrings joinString = new JoinStringsBuilderImpl();
		JoinStrings js = new JoinStringsImpl();
		String[] string = getString();
		JoinStringsPerformanceTest test = new JoinStringsPerformanceTest("joinStringsImpl", 1000, string, js);
		test.run();
		JoinStringsPerformanceTest test1 = new JoinStringsPerformanceTest("joinStringsImpl", 100, string, js);
		test1.run();
		JoinStringsPerformanceTest test2 = new JoinStringsPerformanceTest("JoinStringsBuilderImpl", 1000, string,
				joinString);
		test2.run();
		JoinStringsPerformanceTest test3 = new JoinStringsPerformanceTest("JoinStringsBuilderImpl", 100, string,
				joinString);
		test3.run();

	}

	private static String[] getString() {
		String[] arrStrings = new String[N_STRINGS];
		for (int i = 0; i < N_STRINGS; i++) {
			arrStrings[i] = "Vasay";
		}
		return arrStrings;
	}

}
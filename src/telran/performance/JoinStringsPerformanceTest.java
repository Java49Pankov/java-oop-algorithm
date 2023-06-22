package telran.performance;

public class JoinStringsPerformanceTest extends PerformanceTest {
	String[] strings;
	JoinStrings joinStrings;

	public JoinStringsPerformanceTest(String testName, int nRuns, String[] strings, JoinStrings joinStrings) {
		super(testName, nRuns);
		this.strings = strings;
		this.joinStrings = joinStrings;
	}

	@Override
	protected void runTest() {
		joinStrings.join(strings, "-");
	}

}

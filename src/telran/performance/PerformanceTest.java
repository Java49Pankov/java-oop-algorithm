package telran.performance;

abstract public class PerformanceTest {
	private String testName;
	private int nRuns;

	public PerformanceTest(String testName, int nRuns) {
		super();
		this.testName = testName;
		this.nRuns = nRuns;
	}

	protected abstract void runTest();

	public void run() {
		long timestampStart = System.currentTimeMillis();
		for (int i = 0; i < nRuns; i++) {
			runTest();
		}
		long timestampEnd = System.currentTimeMillis();
		long runTime = timestampEnd - timestampStart;
		System.out.println(
				"Value of nRuns: " + nRuns + " Test name: " + testName + " Running time:" + runTime + " milliseconds");
	}
}

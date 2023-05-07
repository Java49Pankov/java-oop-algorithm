package telran.algorithm;

public class MemoryService {
	public static int getMaxAvailableSize() {
		int rightMemory = Integer.MAX_VALUE;
		int leftMemory = 0;
		int middleMemory = rightMemory / 2;
		int maxSize = 0;
		while (leftMemory <= rightMemory) {
			try {
				byte[] array = new byte[middleMemory];
				maxSize = middleMemory;
				leftMemory = middleMemory + 1;
			} catch (OutOfMemoryError e) {
				rightMemory = middleMemory - 1;
			}
			middleMemory = rightMemory / 2 + leftMemory / 2;
		}
		return maxSize;
	}

}
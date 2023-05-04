package telran.algorithm;

public class MemoryService {
	public static int getMaxAvailableSize() {
		int left = 1;
		long right = Integer.MAX_VALUE;
		int middle = (int) right / 2;
		while (left <= right) {
			try {
				byte[] array = new byte[middle];
				left = middle + 1;
			} catch (OutOfMemoryError e) {
				right = middle - 1;
			}
			middle = (int) ((left + right) / 2);
		}
		return (int) right;
	}
}

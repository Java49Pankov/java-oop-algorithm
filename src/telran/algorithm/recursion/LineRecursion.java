package telran.algorithm.recursion;

public class LineRecursion {
	public static long factorial(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Cannot be negative value");
		}
		long res = 1;
		if (n > 1) {
			res = n * factorial(n - 1);
		}
		return res;
	}

	public static long pow(int a, int b) {
		if (b < 0) {
			throw new IllegalArgumentException("Cannot be negative value");
		}
		long res = 1;
		if (b > 0) {
			res = power(a, pow(a, b - 1));
		}
		return res;
	}

	private static long power(int a, long i) {
		long res = 0;
		if (i < 0) {
			a = -a;
			i = -i;
		}
		if (i > 0) {
			res = a + power(a, i - 1);
		}
		return res;
	}

	public static long sum(int[] array) {
		return sum(0, array);
	}

	private static long sum(int firstInd, int[] array) {
		long sum = 0;
		if (firstInd < array.length) {
			sum = array[firstInd] + sum(firstInd + 1, array);
		}
		return sum;
	}

	public static int[] reverse(int[] array) {
		return reverse(array, 0, array.length - 1);
	}

	private static int[] reverse(int[] array, int left, int right) {
		int[] res = array;
		if (left < right) {
			array[left] = array[left] + array[right];
			array[right] = array[left] - array[right];
			array[left] = array[left] - array[right];
			reverse(array, left + 1, right - 1);
		}
		return res;
	}

	public static long square(int x) {
		long res = 0;
		if (x < 0) {
			x = -x;
		}
		if (x > 0) {
			res = x + x - 1 + square(x - 1);
		}
		return res;
	}

	public static long square1(int x) {
		if (x == 1) {
			return 1;
		}
		return x < 0 ? square1(-x) : x + x - 1 + square1(x - 1);
	}

	public static boolean isSubstring(String string, String substr) {
		boolean res = false;
		if (string.length() == 0 && substr.length() != 0) {
			res = false;
		} else if (string.length() != 0 && substr.length() == 0) {
			res = true;
		} else if (string.length() >= substr.length()) {
			res = string.charAt(0) == substr.charAt(0) ? isSubstr(string, substr, 1, 1)
					: isSubstring(string.substring(1), substr);
		}
		return res;
	}

	private static boolean isSubstr(String string, String substr, int strIndex, int substrIndex) {
		boolean res = false;
		if (substrIndex == substr.length()) {
			res = true;
		} else if (string.charAt(strIndex) == substr.charAt(substrIndex)) {
			res = isSubstr(string, substr, strIndex + 1, substrIndex + 1);
		}
		return res;
	}
}

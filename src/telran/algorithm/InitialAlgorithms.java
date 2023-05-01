package telran.algorithm;

import java.util.Comparator;

public class InitialAlgorithms {

	public static void sortShortPositive(short[] array) {
		int[] helper = new int[Short.MAX_VALUE];
		for (int i = 0; i < array.length; i++) {
			helper[array[i]]++;
		}
		int index = 0;
		for (int i = 0; i < helper.length; i++) {
			for (int j = 0; j < helper[i]; j++) {
				array[index++] = (short) i;
			}
		}
	}

	public static boolean isSum2(short[] array, short sum) {
		int helperSize = sum < 0 ? Short.MAX_VALUE + 1 : sum + 1;
		boolean[] helper = new boolean[helperSize];
		boolean res = false;
		int index = 0;
		while (index < array.length && !res) {
			short value = array[index];
			short secondValue = (short) (sum - value);
			if (secondValue >= 0) {
				if (helper[secondValue]) {
					res = true;
				} else {
					helper[value] = true;
				}
			}
			index++;
		}
		return res;
	}

	public static short getMaxPositiveWithNegativeReflect(short[] array) {
		short res = -1;
		byte[] helper = new byte[Short.MAX_VALUE];
		short candidate = -1;
		for (int i = 0; i < array.length; i++) {
			candidate = (short) Math.abs(array[i]);
			if (array[i] < 0) {
				res = getRes(res, helper, candidate, 1);
			} else {
				res = getRes(res, helper, candidate, -1);
			}
		}
		return res;
	}

	private static short getRes(short res, byte[] helper, short candidate, int sign) {
		if (helper[candidate] == 1 * sign && candidate > res) {
			res = candidate;
		} else if (helper[candidate] == 0) {
			helper[candidate] = (byte) (-1 * sign);
		}
		return res;
	}

	public static void bubbleSort(short[] array) {
		int size = array.length;
		boolean flUnsorted = false;
		do {
			size--;
			flUnsorted = false;
			for (int i = 0; i < size; i++) {
				if (array[i] > array[i + 1]) {
					short tmp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = tmp;
					flUnsorted = true;
				}
			}
		} while (flUnsorted);
	}

	public static <T> int binarySearch(T[] array, T key, Comparator<T> comp) {
		int leftIndex = 0;
		int rightIndex = array.length - 1;
		int middleIndex = rightIndex / 2;
		int compRes = 0;
		while (leftIndex <= rightIndex && (compRes = comp.compare(key, array[middleIndex])) != 0) {
			if (compRes > 0) {
				// move to right part of the array;
				leftIndex = middleIndex + 1;
			} else {
				rightIndex = middleIndex - 1;
			}
			middleIndex = (leftIndex + rightIndex) / 2;
		}
		return leftIndex > rightIndex ? -1 : middleIndex; 
	}

}

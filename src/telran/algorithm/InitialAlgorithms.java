package telran.algorithm;

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

	public static boolean issum(short[] array, short sum) {
		boolean res = false;
		for (int i = 0; i < array.length; i++) {
			for (int j = 1; j < array.length; j++) {
				if (array[i] + array[j] == sum) {
					res = true;
				}
			}
		}
		return res;
	}

	public static short getMaxPositiveWithNegativeReflect(short[] array) {
		short copyNumber = array[0];
		for (int ind = 1; ind < array.length; ind++) {
			if (copyNumber < array[ind]) {
				copyNumber = array[ind];
			}
		}
		return copyNumber > 0 ? copyNumber : -1;
	}

	public static void bubbleSort(short[] arr) {
		boolean swap;
		do {
			swap = false;
			for (int i = 0; i < arr.length-1; i++) {
				if (arr[i] > arr[i + 1]) {
					short temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
					swap = true;
				}
			}
		} while (swap);
	}

}

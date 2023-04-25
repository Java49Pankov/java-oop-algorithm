package telran.algorithm.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static telran.algorithm.InitialAlgorithms.*;

class AlgorithmsTest {

	private boolean runSortedArrayTest(short[] array) {
		boolean resFlag = true;
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] > array[i + 1]) {
				resFlag = false;
			}
		}
		return resFlag;
	}

	@Test
	void testSortShortPositive() {
		short[] randomNums = new short[100000];
		for (int i = 0; i < randomNums.length; i++) {
			randomNums[i] = (short) (Math.random() * Short.MAX_VALUE);
		}
		sortShortPositive(randomNums);
		assertTrue(runSortedArrayTest(randomNums));
	}

	@Test
	void testBubbleSort() {
		short[] randomNum = new short[1000];
		for (int i = 0; i < randomNum.length; i++) {
			randomNum[i] = (short) (Math.random() * Short.MAX_VALUE);
			bubbleSort(randomNum);
			assertTrue(runSortedArrayTest(randomNum));
		}
	}

	@Test
	void testIssum() {
		short[] numbers = { 1, 3, 5, 2, 7, 4 };
		assertTrue(issum(numbers, (short) 7));
		assertFalse(issum(numbers, (short) 25));
	}

	@Test
	void testGetMaxPositiveWithNegativeReflect() {
		short[] numbers = { 1, -1, 10, -10, 100, -100 };
		short[] numbersNegativ = { -3, -1, -8, -10, -120, -100 };
		assertEquals(100, getMaxPositiveWithNegativeReflect(numbers));
		assertEquals(-1, getMaxPositiveWithNegativeReflect(numbersNegativ));
	}

}

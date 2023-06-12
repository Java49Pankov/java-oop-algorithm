package telran.util.stream.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static telran.util.stream.PrimitiveStreams.*;

import java.util.Arrays;

import telran.util.*;
import telran.util.test.CollectionTest;

class PrimitiveStreamTest {

	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 2_000_000_000;
	private static final int N_NUMBERS = 1_000;
	private static final int N_RUNS = 100;

	@Test
	void shuffleTest() {
		int[] array = CollectionTest.getRandomArray(20);
		int[] arrNext = shuffler(array);
		runUniqueTest(arrNext);
		runArrayNotEqualTest(array, arrNext);
	}

	@Test
	void uniqueRandomTest() {
		int[] arrayPrev = randomUnique(N_NUMBERS, MIN_NUMBER, MAX_NUMBER);
		runUniqueTest(arrayPrev);
		for (int i = 0; i < N_RUNS; i++) {
			int[] arrayNext = randomUnique(N_NUMBERS, MIN_NUMBER, MAX_NUMBER);
			runUniqueTest(arrayNext);
			runArrayNotEqualTest(arrayPrev, arrayNext);
			arrayPrev = arrayNext;
		}
	}

	private void runArrayNotEqualTest(int[] arrayPrev, int[] arrayNext) {
		int index = 0;
		if (arrayPrev.length == arrayNext.length) {
			while (index < arrayPrev.length && arrayPrev[index] == arrayNext[index]) {
				index++;
			}
		}
		assertTrue(index < arrayPrev.length);
	}

	private void runUniqueTest(int[] array) {
		HashSet<Integer> set = new HashSet<>();
		for (int num : array) {
			set.add(num);
		}
		assertEquals(array.length, set.size());
	}

}

package telran.util.test;

import telran.util.TreeSet;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Set;

public class TreeSetTest extends SortedSetTest {
	TreeSet<Integer> treeSet;

	@BeforeEach
	@Override
	void setUp() {
		super.setUp();
		treeSet = (TreeSet<Integer>) set;
	}

	@Override
	protected <T> Set<T> getSet() {
		return new TreeSet<>();
	}

//	@Override
//	@Test
//	void clearPerformance() {
//	}

	@Test
	void displayTree() {
		treeSet.setInitialLevel(5);
		treeSet.setSpacesPerLevel(3);
		treeSet.displayRotated();
	}

	@Test
	void widthTest() {
		assertEquals(3, treeSet.width());
	}

	@Test
	void heightTest() {
		assertEquals(3, treeSet.height());
	}

	@Test
	void balanceTest() {
		TreeSet<Integer> treeBalanced = new TreeSet<>();
		int[] array = getRandomArray(255);
		fillCollection(treeBalanced, array);
		treeBalanced.balance();
		assertEquals(8, treeBalanced.height());
		assertEquals(128, treeBalanced.width());
	}

	@Test
	void balanceTestFromSorted() {
		int height = 20;
		int nNumbers = (int) Math.pow(2, height);
		int[] array = new int[nNumbers - 1];
		for (int i = 0; i < array.length; i++) {
			array[i] = i;
		}
		TreeSet<Integer> treeBalanced = new TreeSet<>();
		balanceOrder(array);
		fillCollection(treeBalanced, array);
		assertEquals(height, treeBalanced.height());
		assertEquals(nNumbers / 2, treeBalanced.width());
	}

	private void balanceOrder(int[] array) {
		int[] arrCopy = Arrays.copyOf(array, array.length);
		balanceArrayOrder(array, arrCopy, 0, 0, array.length - 1);
	}

	private void balanceArrayOrder(int[] array, int[] arrCopy, int index, int left, int right) {
		if (left <= right) {
			int rootMiddle = (left + right) / 2;
			array[index++] = arrCopy[rootMiddle];
			balanceArrayOrder(array, arrCopy, index, left, rootMiddle - 1);
			balanceArrayOrder(array, arrCopy, index + (rootMiddle - left), rootMiddle + 1, right);
		}
	}

	@Test
	void simpleBalanceTest() {
		int[] array = { 1, 2, 3, 4, 5, 6, 7 };
		System.out.println("Before: " + Arrays.toString(array));
		balanceOrder(array);
		System.out.println("After: " + Arrays.toString(array));
		int[] expected = { 4, 2, 1, 3, 6, 5, 7 };
		assertArrayEquals(expected, array);
	}

	// { 10, -20, 7, 50, 100, 30 };
	@Test
	void inversionTreeTest() {
		Integer[] expected = { 100, 50, 30, 10, 7, -20 };
		treeSet.inversion();
		assertArrayEquals(expected, treeSet.toArray(new Integer[0]));
		assertTrue(treeSet.contains(100));
	}

}
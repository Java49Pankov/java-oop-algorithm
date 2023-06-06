package telran.algorithm.recursion.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.algorithm.recursion.LineRecursion;

class LineRecursionTest {

	@Disabled
	@Test
	void test() {
		f(6);
	}

	private void f(int a) {
		if (a > 0) {
			System.out.println(a);
			f(a - 1);
		}
	}

	@Test
	void factorialTest() {
		assertEquals(6, LineRecursion.factorial(3));
		assertEquals(24, LineRecursion.factorial(4));
		assertEquals(1, LineRecursion.factorial(0));
	}

	@Test
	void powTest() {
		assertEquals(100, LineRecursion.pow(10, 2));
		assertEquals(100, LineRecursion.pow(-10, 2));
		assertEquals(1000, LineRecursion.pow(10, 3));
		assertEquals(-1000, LineRecursion.pow(-10, 3));
	}

	@Test
	void sumTest() {
		assertEquals(21, LineRecursion.sum(new int[] { 1, 2, 3, 4, 5, 6 }));
	}

	@Test
	void reverseTest() {
		int array[] = { 1, 2, 3, 4, 5, 6 };
		int array1[] = { 1, 2, 3, 4, 5, 6, 7 };
		int expected[] = { 6, 5, 4, 3, 2, 1 };
		int expected1[] = { 7, 6, 5, 4, 3, 2, 1 };
		assertArrayEquals(expected, LineRecursion.reverse(array));
		assertArrayEquals(expected1, LineRecursion.reverse(array1));
	}

	@Test
	void squareTest() {
		assertEquals(4, LineRecursion.square(2));
		assertEquals(9, LineRecursion.square(3));
		assertEquals(25, LineRecursion.square(-5));
		assertEquals(1, LineRecursion.square(1));
		assertEquals(0, LineRecursion.square(0));
		assertEquals(9, LineRecursion.square1(-3));
		assertEquals(16, LineRecursion.square1(4));
		assertEquals(0, LineRecursion.square1(0));
	}

	@Test
	void isSubstringTest() {
		assertTrue(LineRecursion.isSubstring("javaLearn", "ja"));
		assertFalse(LineRecursion.isSubstring("javaLearn", "alen"));
		assertTrue(LineRecursion.isSubstring("java", "va"));
		assertFalse(LineRecursion.isSubstring("java", "jovo"));
		assertFalse(LineRecursion.isSubstring("java", "jc"));
		assertTrue(LineRecursion.isSubstring("java", ""));
		assertFalse(LineRecursion.isSubstring("", "java"));

	}

}

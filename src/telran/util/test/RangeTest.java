package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import telran.util.Range;

class RangeTest {
	Range range = new Range(10, 14);

	@Test
	void constructorExceptionTest() {
		assertThrowsExactly(IllegalArgumentException.class, () -> new Range(10, 10));
	}
	
	@Test
	void toArrayTest() {
		Integer[] expected = { 10, 11, 12, 13 };
		assertArrayEquals(expected, range.toArray());
	}

	@Test
	void iteratorTest() {
		Iterator<Integer> it1 = range.iterator();
		Iterator<Integer> it2 = range.iterator();
		it2.next();
		it2.next();
		it2.next();
		it2.next();
		assertEquals(10, it1.next());
		assertThrows(NoSuchElementException.class, () -> it2.next());
	}
	
	@Test
	void iteratorRemove() {
		Iterator<Integer> it = range.iterator();
		Integer[] expectedFirst = { 11, 12, 13 };
		Integer[] expectedLast = { 11, 12 };
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
		it.next();
		it.remove();
		assertArrayEquals(expectedFirst, range.toArray());
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
		while (it.hasNext()) {
			it.next();
		}
		it.remove();
//		assertArrayEquals(expectedLast, range.toArray());
	}

	@Test
	void removeIfTest() {
		Range range1 = new Range(1, 4);
		Integer[] expected = { 2 };
		range1.removeIf(num -> num % 2 != 0);
		assertArrayEquals(expected, range1.toArray());
	}
}

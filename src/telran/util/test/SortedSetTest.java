package telran.util.test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.SortedSet;

public abstract class SortedSetTest extends SetTest {
	SortedSet<Integer> sortedSet;

	@BeforeEach
	@Override
	void setUp() {
		super.setUp();
		sortedSet = (SortedSet<Integer>) set;
	}

	@Override
	protected Integer[] getActual(Integer[] array, int size) {
//		System.out.println("Sorted test");
		return array;
	}

	@Test
	void firstTest() {
		assertEquals(-20, sortedSet.first());
		sortedSet.clear();
		assertThrowsExactly(NoSuchElementException.class, () -> sortedSet.first());
	}

	@Test
	void lastTest() {
		assertEquals(100, sortedSet.last());
		sortedSet.clear();
		assertThrowsExactly(NoSuchElementException.class, () -> sortedSet.last());
	}

	@Test
	void ceilingTest() {
		runTestForExisted(sortedSet, true);
		assertEquals(50, sortedSet.ceiling(35));
		assertEquals(-20, sortedSet.ceiling(-40));
		assertNull(sortedSet.ceiling(101));
	}

	@Test
	void floorTest() {
		runTestForExisted(sortedSet, false);
		assertEquals(50, sortedSet.floor(55));
		assertEquals(100, sortedSet.floor(101));
		assertNull(sortedSet.floor(-40));
	}

	private void runTestForExisted(SortedSet<Integer> sortedSet, boolean isCeiling) {
		assertEquals(-20, isCeiling ? sortedSet.ceiling(-20) : sortedSet.floor(-20));
		assertEquals(50, isCeiling ? sortedSet.ceiling(50) : sortedSet.floor(50));
		assertEquals(100, isCeiling ? sortedSet.ceiling(100) : sortedSet.floor(100));
	}
}

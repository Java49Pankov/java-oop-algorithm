package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Map;

abstract class MapTest {
	String[] keys = { "lmn", "abc", "ab", "a" };
	Integer[] values = { 3, 2, 2, 1 };
	protected Map<String, Integer> map;

	@BeforeEach
	void setUp() {
		for (int i = 0; i < keys.length; i++) {
			map.put(keys[i], values[i]);
		}
	}

	@Test
	void getTest() {
		for (int i = 0; i < keys.length; i++) {
			assertEquals(values[i], map.get(keys[i]));
		}

	}

}

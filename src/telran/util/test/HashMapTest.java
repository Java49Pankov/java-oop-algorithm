package telran.util.test;

import telran.util.HashMap;
import org.junit.jupiter.api.BeforeEach;

public class HashMapTest extends MapTest {
	@BeforeEach
	@Override
	void setUp() {
		map = new HashMap<>();
		super.setUp();
	}
}

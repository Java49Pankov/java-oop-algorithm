package telran.util;

import java.util.Arrays;
import java.util.function.Predicate;

public interface Collection<T> extends Iterable<T> {
	boolean add(T obj);

	int size();

	boolean remove(T pattern);

	boolean removeIf(Predicate<T> predicate);

	boolean contains(T pattern);

	default T[] toArray(T[] array) {
		int size = size();
		int index = 0;
		if (array.length < size) {
			array = Arrays.copyOf(array, size);
		}
		for (T it : this) {
			array[index++] = it;
		}
		if (array.length > size) {
			array[size] = null;
		}
		return array;
	}

	default boolean isEqual(T object, T pattern) {
		return pattern == null ? object == pattern : pattern.equals(object);
	}

}
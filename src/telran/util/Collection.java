package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

public interface Collection<T> extends Iterable<T> {
	boolean add(T obj);

	int size();

	boolean remove(T pattern);

	boolean contains(T pattern);

	default T[] toArray(T[] array) {
		int size = size();
		int index = 0;
		if (array.length < size) {
			array = Arrays.copyOf(array, size);
		}
		for (T obj : this) {
			array[index++] = obj;
		}
		if (array.length > size) {
			array[size] = null;
		}
		return array;
	}

	default boolean removeIf(Predicate<T> predicate) {
		int oldSize = size();
		Iterator<T> itr = iterator();
		while (itr.hasNext()) {
			T obj = itr.next();
			if (predicate.test(obj)) {
				itr.remove();
			}
		}
		return oldSize > size();
	}

	default void clear() {
		removeIf(element -> true);
	}

	default boolean isEqual(T object, T pattern) {
		return pattern == null ? object == pattern : pattern.equals(object);
	}

}
package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSet<T> implements Set<T> {
	private static final int DEFAULT_HASH_TABLE_SIZE = 16;
	private LinkedList<T>[] hashTable;
	private int size;

	private class HashSetIterator implements Iterator<T> {
		Iterator<T> current;
		Iterator<T> prev;
		int indexTable = 0;
		boolean flNext = false;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			if (current == null || !hasNext()) {
				Iterator<T> itr = null;
				while (itr == null) {
					LinkedList<T> list = getLinkedList();
					itr = list.iterator();
//					indexTable++;
				}
				current = itr;
			}
			T currentNumber = current.next();
			prev = current;
			flNext = true;
			return currentNumber;
		}

		private LinkedList<T> getLinkedList() {
			while (indexTable < hashTable.length && hashTable[indexTable] == null) {
				indexTable++;
			}
			return hashTable[indexTable];
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			prev.remove();
			flNext = false;
			size--;
		}
	}

	@SuppressWarnings("unchecked")
	public HashSet(int hashTableSize) {
		hashTable = new LinkedList[hashTableSize];
	}

	public HashSet() {
		this(DEFAULT_HASH_TABLE_SIZE);
	}

	@Override
	public Iterator<T> iterator() {
		return new HashSetIterator();
	}

	@Override
	public boolean add(T obj) {
		boolean res = false;
		if (size >= 0.75 * hashTable.length) {
			recreation();
		}
		int index = getHashTableIndex(obj);
		if (hashTable[index] == null) {
			hashTable[index] = new LinkedList<>();
		}
		if (!hashTable[index].contains(obj)) {
			hashTable[index].add(obj);
			size++;
			res = true;
		}
		return res;
	}

	private int getHashTableIndex(T obj) {
		return Math.abs(obj.hashCode()) % hashTable.length;
	}

	private void recreation() {
		HashSet<T> tmp = new HashSet<>(hashTable.length * 2);
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null) {
				for (T obj : hashTable[i]) {
					tmp.add(obj);
				}
			}
		}
		this.hashTable = tmp.hashTable;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		int index = getHashTableIndex(pattern);
		if (hashTable[index] != null) {
			res = hashTable[index].remove(pattern);
			size--;
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		int index = getHashTableIndex(pattern);
		return hashTable[index] != null && hashTable[index].contains(pattern);
	}
}
//	@Override
	// FIXME method should be removed after writing iterator
//	public T[] toArray(T[] arr) {
//		int size = size();
//		if (arr.length < size) {
//			arr = Arrays.copyOf(arr, size);
//		}
//		int index = 0;
//		for (int i = 0; i < hashTable.length; i++) {
//			LinkedList<T> list = hashTable[i];
//			if (list != null) {
//				for (T obj : list) {
//					arr[index++] = obj;
//				}
//			}
//			if (arr.length > size) {
//				arr[size] = null;
//			}
//		}
//		return arr;
//
//	}
//
//}

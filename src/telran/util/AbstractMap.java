package telran.util;

public abstract class AbstractMap<K, V> implements Map<K, V> {
	protected Set<Entry<K, V>> set;

	@Override
	public V get(K key) {
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		return entry == null ? null : entry.getValue();
	}

	@Override
	public V put(K key, V value) {
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		V res = null;
		if (entry != null) {
			res = entry.getValue();
			entry.setValue(value);
		} else {
			set.add(new Entry<>(key, value));
		}
		return res;
	}

	@Override
	public V remove(K key) {
		V res = get(key);
		if (res != null) {
			set.remove(new Entry<>(key, null));
		}
		return res;
	}

	@Override
	public boolean containsKey(K key) {
		return set.contains(new Entry<>(key, null));
	}

	@Override
	public boolean containsValue(V value) {
		boolean res = false;
		for (Entry<K, V> val : set) {
			if (val.getValue().equals(value)) {
				res = true;
			}
		}
		return res;
	}

	@Override
	public Set<K> keySet() {
		Set<K> res = getKeySet();
		set.stream().map(elem -> elem.getKey()).forEach(key -> res.add(key));
		return res;
	}

	abstract protected Set<K> getKeySet();

	@Override
	public Collection<V> values() {
		List<V> list = new ArrayList<>();
		set.forEach(elem -> list.add(elem.getValue()));
		return list;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return set;
	}

}

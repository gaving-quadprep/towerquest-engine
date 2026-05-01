package net.towerquest.system;

import java.util.HashMap;
import java.util.Map;

public class BiMap<K, V> {
	private Map<K, V> map = new HashMap<K, V>();
	private Map<V, K> mapReverse = new HashMap<V, K>();
	public void put(K k, V v) {
		map.put(k, v);
		mapReverse.put(v, k);
	}
	public V get(K k) {
		return map.get(k);
	}
	public K getReverse(V v) {
		return mapReverse.get(v);
	}
	// TODO remove function
}

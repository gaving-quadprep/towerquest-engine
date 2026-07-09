package net.towerquest.towerquest.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class UniqueBiHashMap<K,V> implements UniqueBiMap<K,V> {
	protected Map<K, V> map;
	protected Map<V, K> mapReverse;
	public UniqueBiHashMap() {
		this(false);
	}
	public UniqueBiHashMap(boolean ordered) {
		map = ordered ? new LinkedHashMap<>() : new HashMap<>();
		mapReverse = ordered ? new LinkedHashMap<>() : new HashMap<>();
	}

	@Override
	public int size() {
		assert map.size() == mapReverse.size() : "Map size mismatch";
		return map.size();
	}
	@Override
	public void put(K k, V v) {
		// ensure uniqueness
		map.remove(k);
		mapReverse.remove(v);

		map.put(k, v);
		mapReverse.put(v, k);
	}
	@Override
	public V get(K k) {
		return map.get(k);
	}
	@Override
	public K getReverse(V v) {
		return mapReverse.get(v);
	}
	@Override
	public V remove(K k) {
		V v = map.remove(k);
		mapReverse.remove(v);
		return v;
	}
	@Override
	public K removeReverse(V v) {
		K k = mapReverse.remove(v);
		map.remove(k);
		return k;
	}
}

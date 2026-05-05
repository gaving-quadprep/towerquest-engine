package net.towerquest.util;

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
		map = ordered ? new LinkedHashMap<K, V>() :new HashMap<K, V>();
		mapReverse = ordered ? new LinkedHashMap<V, K>() :new HashMap<V, K>();
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
	// TODO finish
}

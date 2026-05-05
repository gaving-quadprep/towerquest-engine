package net.towerquest.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class BiHashMap<K, V> implements BiMap<K, V> {
	// TODO also finish
	private Map<K, V> map;
	private Map<V, K> mapReverse;
	public BiHashMap() {
		this(false);
	}
	public BiHashMap(boolean ordered) {
		map = ordered ? new LinkedHashMap<K, V>() :new HashMap<K, V>();
		mapReverse = ordered ? new LinkedHashMap<V, K>() :new HashMap<V, K>();
	}
	@Override
	public int size() {
		return map.size();
	}
	@Override
	public void put(K k, V v) {
		map.put(k, v);
		mapReverse.put(v, k);
	}
	@Override
	public V get(K k) {
		return map.get(k);
	}
	@Override
	public Set<K> getAllReverse(V v) {
		// TODO Auto-generated method stub
		return null;
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

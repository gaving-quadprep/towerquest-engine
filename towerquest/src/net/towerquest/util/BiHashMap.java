package net.towerquest.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class BiHashMap<K, V> implements BiMap<K, V> {
	// TODO also finish
	private Map<K, V> map;
	private Map<V, Set<K>> mapReverse;
	public BiHashMap() {
		this(false);
	}
	public BiHashMap(boolean ordered) {
		map = ordered ? new LinkedHashMap<>() : new HashMap<>();
		mapReverse = ordered ? new LinkedHashMap<>() : new HashMap<>();
	}
	@Override
	public int size() {
		return map.size();
	}
	@Override
	public void put(K k, V v) {
		map.put(k, v);
		Set<K> set = mapReverse.get(v);
		if (set == null) {
			set = new HashSet<>();
			mapReverse.put(v, set);
		}
		set.add(k);
	}
	@Override
	public V get(K k) {
		return map.get(k);
	}
	@Override
	public K getFirstReverse(V v) {
		// you have to iterate over a set to get elements
		for(K k : mapReverse.get(v))
			if (k != null)
				return k;
		return null;
	}
	@Override
	public Set<K> getAllReverse(V v) {
		return mapReverse.get(v);
	}
	@Override
	public V remove(K k) {
		V v = map.remove(k);
		mapReverse.remove(v);
		return v;
	}
	@Override
	public Set<K> removeAllReverse(V v) {
		Set<K> ks = mapReverse.remove(v);
		for (K k : ks) {
			map.remove(k);
		}
		return ks;
	}
}

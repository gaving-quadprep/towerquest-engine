package net.towerquest.util;

import java.util.Set;

public interface UniqueBiMap<K, V> {
	public int size();
	public void put(K k, V v);
	public V get(K k);
	public K getReverse(V v);
	public V remove(K k);
	public K removeReverse(V v);
}

package net.towerquest.util;

public class Registry<T> {
	private final BiMap<String, T> map;
	
	public Registry(boolean ordered) {
		map = new BiHashMap<>(ordered);
	}
	
	public Registry() {
		this(false);
	}
	
	public void addMapping(T t, String name) {
		map.put(name, t);
	}
	public T get(String name) {
		return map.get(name);
	}
	public String getName(T t) {
		return map.getFirstReverse(t);
	}
	// TODO implement
	/*
	public Collection<String> getNames() {
		return map.keySet();
	}
	public Collection<T> getValues() {
		return map.values();
	}
	public Collection<Map.Entry<String, T>> getPairs() {
		return map.entrySet();
	}
	*/
}

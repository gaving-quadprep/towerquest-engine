package net.towerquest.engine.util;

import java.util.HashSet;
import java.util.Set;

import net.towerquest.engine.util.NFunction.Consumer;

public class WatchableObject<T> {
	private T object;
	// no way the watchers from the pezut games
	private Set<Consumer<WatchableObject<T>>> watchers = new HashSet<>();
	public WatchableObject(T object) {
		this.object = object;
	}
	
	public T get() {
		return object;
	}
	
	public void set(T object) {
		this.object = object;
		for (Consumer<WatchableObject<T>> watcher : watchers) {
			watcher.exec(this);
		}
	}
	
	public void watch(Consumer<WatchableObject<T>> watcher) {
		this.watchers.add(watcher);
	}
	
	public void unwatch(Consumer<WatchableObject<T>> watcher) {
		this.watchers.remove(watcher);
	}
}

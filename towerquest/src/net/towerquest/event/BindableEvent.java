package net.towerquest.event;

import java.util.HashSet;
import java.util.Set;

import net.towerquest.serialization.PointerTo;
import net.towerquest.serialization.Serializable;
import net.towerquest.util.NFunction;
import net.towerquest.util.NFunction.*;

public class BindableEvent<FN extends NConsumer> implements Serializable {
	private Set<PointerTo<FN>> fns = new HashSet<>();
	public void bind(FN fn) {
		fns.add(new PointerTo<>(fn));
	}
	public void unbind(FN fn) {
		fns.remove(new PointerTo<>(fn)); // works because hashset uses equals()
	}
	public void unbindAll() {
		fns.clear();
	}
	public void fire(Object... args) {
		for(PointerTo<FN> fn : fns)
			NFunction.exec(fn.value, args);
	}
}

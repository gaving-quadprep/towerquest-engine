package net.towerquest.towerquest.event;

import net.towerquest.serialization.Pointer;
import net.towerquest.serialization.Serializable;
import net.towerquest.towerquest.util.NFunction;

public class BindableFunction<R, FN extends NFunction<R>> implements Serializable {
	@Pointer
	private FN fn;
	public void bind(FN fn) {
		this.fn = fn;
	}
	public void unbind(FN fn) {
		this.fn = null;
	}
	public R execute(Object... args) {
		return NFunction.exec(fn, args);
	}
}

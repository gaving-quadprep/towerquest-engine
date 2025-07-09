package net.towerquest.event;

import java.util.function.Consumer;
import java.util.function.Function;

import net.towerquest.entity.Component;

public abstract class Event<T> {
	private Component parent;
	public Consumer<T> fn;
	public Function<T, Boolean> condition;
	
	public Event(Consumer<T> fn, Function<T, Boolean> condition) {
		this.fn = fn;
		this.condition = condition;
	}

	public void setParent(Component c) {
		this.parent = c;
	}
	public Component getParent() {
		 return this.parent;
	}
	//public static enum 
	
	public void fire(T arg) {
		if(this.condition.apply(arg)) {
			//Logger.instance.log("Event fired! (" + this.getClass().getSimpleName() + ", " + this.parent.getClass().getSimpleName());
			fn.accept(arg);
		} else {
			
		}
	}
}

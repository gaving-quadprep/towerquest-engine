package event;

import java.util.function.Consumer;
import java.util.function.Function;

import entity.Component;
import render.WorldRenderer;

public class RenderEvent extends Event<WorldRenderer> {
	static {
		//TODO registry
	}
	
	public RenderEvent(Consumer<WorldRenderer> fn, Function<WorldRenderer, Boolean> shouldFire) {
		super(fn, shouldFire);
		// TODO Auto-generated constructor stub
	}
}

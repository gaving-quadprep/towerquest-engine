package net.towerquest.event;

import java.util.function.Consumer;
import java.util.function.Function;

public class UpdateEvent extends Event<Double> {

	public UpdateEvent(Consumer<Double> fn, Function<Double, Boolean> shouldFire) {
		super(fn, shouldFire);
		// TODO Auto-generated constructor stub
	}

}

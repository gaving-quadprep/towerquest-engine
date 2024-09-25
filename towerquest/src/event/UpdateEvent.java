package event;

import java.util.function.Consumer;
import java.util.function.Function;

public class UpdateEvent extends Event<Void> {

	public UpdateEvent(Consumer<Void> fn, Function<Void, Boolean> shouldFire) {
		super(fn, shouldFire);
		// TODO Auto-generated constructor stub
	}

}

package net.towerquest.util;

public interface NFunction<R> {
	public static interface NConsumer extends NFunction<Void> {}
	
	@SuppressWarnings("unchecked")
	public static <R> R exec(NFunction<R> nf, Object... args) {
		if (nf instanceof Producer)
			return ((Producer<R>)nf).exec();
		if (nf instanceof Function)
			return ((Function<Object,R>)nf).exec(args[0]);
		if (nf instanceof BiFunction)
			return ((BiFunction<Object,Object,R>)nf).exec(args[0], args[1]);
		if (nf instanceof TriFunction)
			return ((TriFunction<Object,Object,Object,R>)nf)
					.exec(args[0], args[1], args[2]);
		if (nf instanceof QuadFunction)
			return ((QuadFunction<Object,Object,Object,Object,R>)nf)
					.exec(args[0], args[1], args[2], args[3]);
		return null;
	}
	
	@FunctionalInterface
	public static interface Producer<R> extends NFunction<R> {
		public R exec();
	}
	
	// 
	@FunctionalInterface
	public static interface Runnable extends Producer<Void>, NConsumer {
		@Override
		public default Void exec() {
			exec1();
			return null;
		};
		public void exec1();
	}
	
	
	@FunctionalInterface
	public static interface Function<T, R> extends NFunction<R> {
		public R exec(T t);
	}

	@FunctionalInterface
	public static interface Consumer<T> extends Function<T,Void>, NConsumer {
		@Override
		public default Void exec(T t) {
			exec1(t);
			return null;
		};
		public void exec1(T t);
	}
	
	
	@FunctionalInterface
	public static interface BiFunction<T,U,R> extends NFunction<R> {
		public R exec(T t, U u);
	}
	
	@FunctionalInterface
	public static interface BiConsumer<T,U> extends BiFunction<T,U,Void>,
		NConsumer {
		@Override
		public default Void exec(T t, U u) {
			exec1(t, u);
			return null;
		};
		public void exec1(T t, U u);
	}
	
	
	@FunctionalInterface
	public static interface TriFunction<T,U,V,R> extends NFunction<R> {
		public R exec(T t, U u, V v);
	}
	
	@FunctionalInterface
	public static interface TriConsumer<T,U,V> extends TriFunction<T,U,V,Void>,
		NConsumer {
		@Override
		public default Void exec(T t, U u, V v) {
			exec1(t, u, v);
			return null;
		};
		public void exec1(T t, U u, V v);
	}
	
	
	@FunctionalInterface
	public static interface QuadFunction<T,U,V,W,R> extends NFunction<R> {
		public R exec(T t, U u, V v, W w);
	}
	
	@FunctionalInterface
	public static interface QuadConsumer<T,U,V,W> extends QuadFunction<T,U,V,W,Void>,
		NConsumer {

		@Override
		public default Void exec(T t, U u, V v, W w) {
			exec1(t, u, v, w);
			return null;
		};
		public void exec1(T t, U u, V v, W w);
	}
}

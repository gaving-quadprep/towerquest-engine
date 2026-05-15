package net.towerquest.util;

public interface NFunction<R> {
	public interface NConsumer extends NFunction<Void> {}
	
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
	public interface Producer<R> extends NFunction<R> {
		public R exec();
	}
	
	@FunctionalInterface
	public interface Runnable extends Producer<Void>, NConsumer {}
	
	
	@FunctionalInterface
	public interface Function<T, R> extends NFunction<R> {
		public R exec(T t);
	}

	@FunctionalInterface
	public interface Consumer<T> extends Function<T,Void>, NConsumer {}
	
	
	@FunctionalInterface
	public interface BiFunction<T,U,R> extends NFunction<R> {
		public R exec(T t, U u);
	}
	
	@FunctionalInterface
	public interface BiConsumer<T,U> extends BiFunction<T,U,Void>,
		NConsumer {}
	
	
	@FunctionalInterface
	public interface TriFunction<T,U,V,R> extends NFunction<R> {
		public R exec(T t, U u, V v);
	}
	
	@FunctionalInterface
	public interface TriConsumer<T,U,V> extends TriFunction<T,U,V,Void>,
		NConsumer {}
	
	
	@FunctionalInterface
	public interface QuadFunction<T,U,V,W,R> extends NFunction<R> {
		public R exec(T t, U u, V v, W w);
	}
	
	@FunctionalInterface
	public interface QuadConsumer<T,U,V,W> extends QuadFunction<T,U,V,W,Void>,
		NConsumer {}
}

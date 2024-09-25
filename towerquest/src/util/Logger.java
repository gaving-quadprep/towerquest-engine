package util;

//* worst logger ever

public class Logger {
	private final long startTime;
	public static final Logger instance = new Logger();
	public Logger() {
		startTime = System.nanoTime();
		log("Logger started", 2);
	}
	public void log(String s) {
		log(s, 2);
	}
	public void log(String s, int depth) {
		System.out.println("[" + String.format("%.7f", (double)(System.nanoTime() - startTime) / 1000000000) + "] " + s + " @ " + String.valueOf(Thread.currentThread().getStackTrace()[depth + 1].toString()));
	}
	/*public static void staticLog(String s) {
		instance.log(s, 2);
	}*/
}

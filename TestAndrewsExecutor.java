/* 2 Text loops re-executed
Modify the code of the exercise “Text Loops” from last day to use o
ne of the
Executor
instead of plain threads
*/
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ArrayBlockingQueue;

public class TestAndrewsExecutor implements Runnable {
	public static final int COUNT = 10;
	private final String name;

	public TestAndrewsExecutor(String name) {
		this.name = name;
	}
	@Override
	public void run() {
		for (int i = 0; i < COUNT; i++) {
			System.out.println("Loop:" + name + ", iteration:" + i + ".");
		}
	}
	public static void main(String args[]) {
		if (args.length < 1 || (!args[0].equals("0") && !args[0].equals("1"))) {
			System.out.println("USAGE: java TextLoopExecutor <mode>");
			System.out.println(" mode 0: without threads");
			System.out.println(" mode 1: with threads");
		} else if (args[0].equals("0")) {
			for (int i = 0; i < 10; i++) {
				Runnable r = new TextLoopExecutor("Thread " + i);
				r.run();
			}
		} else {
			for (int i = 0; i < 10; i++) {
				Runnable r = new TextLoopExecutor("Thread " + i);
				Executor e = new AndrewsExecutor(2);
				e.execute(r);
			}
		}
	}
}
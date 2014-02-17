/* 3.1 Implement Executor
Create your own implementation of
Executor. Look at the documentation of the API of
Executor for ideas. Your implementation must be able to execute one
Runnable at a time (but you can make it run several tasks in paralel,
see also Exercise 6) and must have a queue of pending tasks.
Try your implementation by exchanging the class that you used in your
solution for the former exercise by yournew implementation of Executor.
*/
import java.util.concurrent.Executor;
import java.util.Queue;
import java.util.ArrayDeque;

public class AndrewsExecutor implements Executor {
	final Queue<Runnable> tasks = new ArrayDeque<Runnable>();
	static int maxThreads;
	static int threadCount = 0;
	Runnable active;

	public AndrewsExecutor(int maxThreads){
		this.maxThreads = maxThreads;
	}
	/** Executes the given command at some time in the future. The
	*   command may execute in a new thread, in a pooled thread,
	*	or in the calling thread, at the discretion of the Executor implementation.
	*/
	public synchronized void execute(Runnable command){
		tasks.offer(command);
		if(active == null){
			if((active = tasks.poll()) != null){
				if(threadCount < maxThreads){
					new Thread(command).start();
					threadCount++;
				} else {
					tasks.offer(command);
				}
			while(threadCount >= maxThreads){
				try {
					wait();
				} catch (interruptedException ex){
					//Nothing to do in this case, just wait less...
				}
			}
			if((active = tasks.poll()) != null){

			}
	}
}
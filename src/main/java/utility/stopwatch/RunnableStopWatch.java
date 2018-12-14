package utility.stopwatch;

public class RunnableStopWatch 
{
	private Runnable runnable;
	
	public RunnableStopWatch ( Runnable runnable )
	{
		this.runnable = runnable;
	}
	
	public long run()
	{
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		runnable.run();
		stopWatch.stop();
		
		return stopWatch.finalElapsedTime();
	}
}

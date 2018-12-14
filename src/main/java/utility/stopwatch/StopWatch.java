package utility.stopwatch;

import java.util.Arrays;

public class StopWatch 
{
	private static final int DEFAULT_MAX_LAPS = 100;
	
	private int     lapIndex;
	private long[]  lapTimes;
	private boolean running;
	
	public StopWatch()
	{
		this ( DEFAULT_MAX_LAPS );
	}
	
	public StopWatch ( int maxLaps )
	{
		if ( maxLaps < 1 )
			throw new IllegalArgumentException ( "StopWatch Max Laps must be >=1" );
		
		lapTimes = new long[ maxLaps+2 ];
		
		reset();
	}
	
	public void reset()
	{
		running = false;
		lapIndex  = 0;
		
		Arrays.fill ( lapTimes, -1 );
	}
	
	public void start()
	{
		long time = System.currentTimeMillis();
		
		if ( !isReset() )
			throw new IllegalStateException ( "StopWatch is not Ready To Start" );
		
		running = true;
		
		pushTime ( time );
	}
	
	public void stop()
	{
		long time = System.currentTimeMillis();
		
		if ( !isRunning() )
			throw new IllegalStateException ( "StopWatch is not Running." );
		
		pushTime ( time );
		
		running = false;
	}
	
	public void lap()
	{
		long time = System.currentTimeMillis();
		
		if ( !isRunning() )
			throw new IllegalStateException ( "StopWatch is not Running." );
		
		if ( lapIndex >= lapTimes.length-1 )
			throw new ArrayIndexOutOfBoundsException ( String.format ( "StopWatch Configured for a Maximum of %d Laps", lapTimes.length-2 ) );
		
		pushTime ( time );
	}
	
	private void pushTime ( long time )
	{
		if ( lapIndex >= lapTimes.length )
			throw new ArrayIndexOutOfBoundsException ( String.format ( "Time Array Bounds Exceeded" ) );
		
		lapTimes[ lapIndex++ ] = time;
	}
	
	public long startTime()
	{
		if ( isReset() )
			throw new IllegalStateException ( "StopWatch as not been Started" );
		
		return lapTimes[ 0 ];
	}
	
	public long endTime()
	{
		if ( !isStopped() )
			throw new IllegalStateException ( "StopWatch has not been Stopped" );
		
		return lapTimes[ lapIndex-1 ];
	}
	
	public long[] laps()
	{
		if ( isReset() )
			throw new IllegalStateException ( "StopWatch as not been Started" );

		return Arrays.copyOf ( lapTimes, lapIndex );
	}
	
	public long[] spans()
	{
		if ( isReset() )
			throw new IllegalStateException ( "StopWatch as not been Started" );

		long[] spans = new long[ lapIndex-1 ];
		
		for ( int i = 1; i < lapIndex; i++ )
			spans[ i-1 ] = lapTimes[ i ] - lapTimes[ i-1 ];
		
		return spans;
	}
	
	public long finalElapsedTime()
	{
		return endTime() - startTime();
	}
	
	public long currentElapsedTime()
	{
		return System.currentTimeMillis() - startTime();
	}
	
	public boolean isReset()
	{
		return lapIndex == 0;
	}
	
	public boolean isRunning()
	{
		return running;
	}
	
	public boolean isStopped()
	{
		return !isReset() && !isRunning();
	}
}

package utility.stopwatch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StopWatchTest
{
	private class TimeCheck extends BaseMatcher<Long>
	{
		private static final long MAX_DELTA = 9;
	
		private long expectedTime;
		
		public TimeCheck ( long time )
		{
			expectedTime = time;
		}
		
		@Override
		public boolean matches ( Object item )
		{
			Long time = ( Long ) item;
			
			return Math.abs ( time - expectedTime ) <= MAX_DELTA;
		}

		@Override
		public void describeTo ( Description description )
		{
			description.appendText ( "Within " + MAX_DELTA + " of " + expectedTime );
		}
	}
	
	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testStopWatch() throws InterruptedException
	{
		long[] laps = null;
		
		final StopWatch s = new StopWatch ( 3 );
		assertTrue ( s.isReset() );
		assertFalse ( s.isRunning() );
		assertFalse ( s.isStopped() );
		illegalState ( new Runnable() { public void run() { s.laps(); } } );
		illegalState ( new Runnable() { public void run() { s.lap(); } } );
		illegalState ( new Runnable() { public void run() { s.stop(); } } );
		illegalState ( new Runnable() { public void run() { s.startTime(); } } );
		illegalState ( new Runnable() { public void run() { s.endTime(); } } );
		illegalState ( new Runnable() { public void run() { s.currentElapsedTime(); } } );
		illegalState ( new Runnable() { public void run() { s.finalElapsedTime(); } } );
		
		s.start();
		assertFalse ( s.isReset() );
		assertTrue ( s.isRunning() );
		assertFalse ( s.isStopped() );
		illegalState ( new Runnable() { public void run() { s.start(); } } );
		illegalState ( new Runnable() { public void run() { s.finalElapsedTime(); } } );
		
		long st = s.startTime();

		Thread.sleep ( 1000 );
		
		long ct = s.currentElapsedTime();
		assertThat ( ct, new TimeCheck ( 1000 ) );
		
		s.lap();
		Thread.sleep ( 1000 );
		s.lap();
		Thread.sleep ( 1000 );
		s.lap();
		arrayBounds ( new Runnable() { public void run() { s.lap(); } } );
		
		Thread.sleep ( 1000 );
		
		s.stop();
		assertFalse ( s.isReset() );
		assertFalse ( s.isRunning() );
		assertTrue ( s.isStopped() );
		assertEquals ( 5, s.laps().length );
		illegalState ( new Runnable() { public void run() { s.start(); } } );
		illegalState ( new Runnable() { public void run() { s.stop(); } } );
		illegalState ( new Runnable() { public void run() { s.lap(); } } );
		
		long et = s.endTime();
		long ft = s.finalElapsedTime();
		assertThat ( ft, new TimeCheck ( 4000 ) );
		
		laps = s.laps();
		
		assertEquals ( st, laps[ 0 ] );
		assertEquals ( et, laps[ 4 ] );
		
		long[] spans = s.spans();
		
		assertEquals ( 4, spans.length );
		assertThat ( spans[ 0 ], new TimeCheck ( 1000 ) );
		assertThat ( spans[ 1 ], new TimeCheck ( 1000 ) );
		assertThat ( spans[ 2 ], new TimeCheck ( 1000 ) );
		assertThat ( spans[ 3 ], new TimeCheck ( 1000 ) );
	}
	
	@Test
	public void testRunnableStopWatch()
	{
		Runnable runnable = new Runnable()
		{
			@Override
			public void run()
			{
				try 
				{
					Thread.sleep ( 2345 );
				} 
				catch ( InterruptedException e )
				{
					e.printStackTrace();
				}
			}
		};
		
		RunnableStopWatch r = new RunnableStopWatch ( runnable );
		
		assertThat ( "A", r.run(), new TimeCheck ( 2345 ) );
		assertThat ( "B", r.run(), new TimeCheck ( 2345 ) );
		assertThat ( "C", r.run(), new TimeCheck ( 2345 ) );
		assertThat ( "D", r.run(), new TimeCheck ( 2345 ) );
	}
	
	private void illegalState ( Runnable runnable )
	{
		boolean fail = true;
		
		try
		{
			runnable.run();
		}
		catch ( IllegalStateException e )
		{
			fail = false;
		}
		catch ( Exception e )
		{
			fail ( "Unexpected Exception: " + e.getMessage() );
		}
		
		if ( fail )
			fail ( "Expected IllegalStateException" );
	}
	
	private void arrayBounds ( Runnable runnable )
	{
		boolean fail = true;
		
		try
		{
			runnable.run();
		}
		catch ( ArrayIndexOutOfBoundsException e )
		{
			fail = false;
		}
		catch ( Exception e )
		{
			fail ( "Unexpected Exception: " + e.getMessage() );
		}
		
		if ( fail )
			fail ( "Expected ArrayIndexOutOfBoundsException" );
	}
}

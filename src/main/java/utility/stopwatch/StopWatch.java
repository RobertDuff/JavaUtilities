package utility.stopwatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.LongSupplier;

/**
 * This class represents a StopWatch Timer
 * 
 * It is capable of marking timepoints, and calculating durations between marked
 * timepoints.
 * 
 * It is also able time the execution of a {@link Runnable} object. It can also
 * act as a {@link Runnable} or {@link Callable} wrrapper around code for timing
 * parallel execution.
 * 
 * @author Rob Duff
 */
public class StopWatch implements Runnable, Callable<Long>
{
    /**
     * Supplier of the current time point.
     */
	private LongSupplier timeSupplier;
	
    /**
     * Runnable Code to time.
     */
	private Runnable runnable;
	
    /**
     * List of Marked Time Points.
     */
	private List<Long>  marks = new ArrayList<>();
    
    /**
     * Create a new {@code StopWatch} object.
     * <p></p>
     * Uses {@code System}.{@code currentTimeMillis}() as the time provider.
     * 
     * @see java.lang.System#currentTimeMillis()
     */
    public StopWatch()
    {
        this ( System::currentTimeMillis );
    }
    
    /**
     * Create a new {@code StopWatch} object.
     *
     * @param timeSupplier
     *            The time point supplier. There is no assumption on the
     *            time-resolution.
     */
    public StopWatch ( LongSupplier timeSupplier )
    {
        this ( null, timeSupplier );        
    }
    
    /**
     * Create a new {@code StopWatch} object capable of acting as a {@link Runnable}
     * or {@link Callable} object that can measure the duration of the supplied
     * {@code Runnable}.
     * <p></p>
     * Uses {@code System}.{@code currentTimeMillis}() as the time provider.
     * 
     * @param runnable
     *            The {@link Runnable} object to time.
     * 
     * @see java.lang.System#currentTimeMillis()
     */
    public StopWatch ( Runnable runnable )
    {
        this ( runnable, System::currentTimeMillis );
    }
    
    /**
     * Create a new {@code StopWatch} object capable of acting as a {@link Runnable}
     * or {@link Callable} object that can measure the duration of the supplied
     * {@code Runnable}.
     * 
     * @param runnable
     *            The {@link Runnable} object to time.
     * @param timeSupplier
     *            The time point supplier. There is no assumption on the
     *            time-resolution.
     */
    public StopWatch ( Runnable runnable, LongSupplier timeSupplier )
    {
        this.runnable = runnable;
        this.timeSupplier = timeSupplier;        
    }
    
    /**
     * Clears all marked Time Points.
     */
    public void reset()
    {
        marks.clear ();
    }
    
    /**
     * Records the current time point.
     */
	public void mark()
	{
		marks.add ( timeSupplier.getAsLong() );
	}
	
    /**
     * @return The first marked Time Point, or {@code Long.MIN_VALUE} if no time
     *         points have been marked.
     */
	public long start()
	{
	    if ( marks.isEmpty () )
	        return Long.MIN_VALUE;
	    
	    return marks.get ( 0 );
	}
	
    /**
     * @return The last marked Time Point, or {@code Long.MAX_VALUE} if no time
     *         points have been marked.
     */
	public long end()
	{
        if ( marks.isEmpty () )
            return Long.MAX_VALUE;
        
        return marks.get ( marks.size ()-1 );
	}

    /**
     * @return The difference between the first and last marked Time Points. If only
     *         one Time Point has been marked, then returns 0. If no Time Points
     *         have been marked, then returns {@code Long.MIN_VALUE}
     */
	public long duration()
	{
	    return duration ( marks.size ()-1 );
	}
	
    /**
     * Returns the duration between the first marked Time Point and the specified
     * Time Point.
     * 
     * @param endMark
     *            The Zero-Based Index of the end Time Point
     * @return The duration. If the {@code endMark} is beyond the last marked Time
     *         Point, returns {@code Long.MAX_VALUE}.
     */
	public long duration ( int endMark )
	{
	    return duration ( 0, endMark );
	}
	
    /**
     * Returns the duration between two marked Time Points.
     * 
     * @param startMark
     *            The Zero-Based Index of the first marked Time Point.
     * @param endMark
     *            The Zero-Based Index of the second marked Time Point.
     * @return The difference between the two Time Points. If either
     *         {@code startMark} or {@code endMark} are less than Zero, return
     *         {@code Long.MIN_VALUE}. If either is greater than the last marked
     *         Time Point, returns {@code Long.MAX_VALUE}.
     */
	public long duration ( int startMark, int endMark )
	{
	    if ( marks.isEmpty () )
	        return Long.MIN_VALUE;
	    
	    if ( startMark < 0 || endMark < 0 )
	        return Long.MIN_VALUE;
	    
	    if ( startMark >= marks.size () || endMark >= marks.size () )
	        return Long.MAX_VALUE;
	    
	    int start = Math.min ( startMark, endMark );
	    int end = Math.max ( startMark, endMark );
	    
	    return marks.get ( end ) - marks.get ( start );
	}

    /**
     * @return The {@link List} of marked Time Points.
     */
	public List<Long> marks()
	{
	    return Collections.unmodifiableList ( marks );
	}
	
    /**
     * @return The duration between the first marked Time Point and the current Time
     *         Point (as returned by the timeSupplier). If there are no marked Time
     *         Points, return {@code Long.MAX_VALUE}.
     */
	public long currentElapsedTime()
	{
	    if ( marks.isEmpty () )
	        return Long.MAX_VALUE;
	    
		return timeSupplier.getAsLong() - start();
	}

    @Override
    public Long call () throws Exception
    {
        return run ( runnable );
    }

    @Override
    public void run ()
    {
        run ( runnable );
    }
    
    /**
     * Records the duration of a code segment.
     * 
     * @param runnable
     *            The code to run.
     * @return The time the code took to run.
     */
    public long run ( Runnable runnable )
    {
        marks.clear ();
        mark();

        try
        {
            runnable.run ();
        }
        catch ( Throwable e )
        {
            e.printStackTrace();
        }
        
        mark();
        
        return duration ();
    }
}

package utility.geometry;

/**
 * This class represents a 2 dimensional line segment 
 * 
 * @author Robert Duff
 */
public class LineSegment
{
	/**
	 * The first {@link Point} of the segment.  This point is considered the <i>starting</i> point.
	 */
	private Point a;
	
	/**
	 * The second {@link Point} of the segment.  This point is considered the <i>ending</i> point.
	 */
	private Point b;
	
	/**
	 * Construct a LineSegment from 2 pairs of x/y coordinates
	 * 
	 * @param x1 The X coordinate of the starting point.
	 * @param y1 The Y coordinate of the starting point.
	 * @param x2 The X coordinate of the ending point.
	 * @param y2 The Y coordinate of the ending point.
	 */
	public LineSegment ( double x1, double y1, double x2, double y2 )
	{
		this ( new Point ( x1, y1 ), new Point ( x2, y2 ) );
	}
	
	/**
	 * Construct a LineSegment from 2 {@link Point}s.
	 * 
	 * @param a The starting point.
	 * @param b The ending point.
	 */
	public LineSegment ( Point a, Point b )
	{
		if ( a == null || b == null )
			throw new NullPointerException ( "Neither Point can be null" );
		
		this.a = a;
		this.b = b;
	}
	
	/**
	 * Copy Constructor.
	 * 
	 * @param l A LineSegment to copy.
	 */
	public LineSegment ( LineSegment l )
	{
		this ( l.a, l.b );
	}

	/**
	 * @return The starting point of the LineSegment.
	 */
	public Point a()
	{
		return a;
	}
	
	/**
	 * @return The ending point of the LineSegment.
	 */
	public Point b()
	{
		return b;
	}
	
	/**
	 * @return A {@link Line} object corresponding to this LineSegment.
	 */
	public Line line()
	{
		return LineBuilder.between ( a, b );
	}
	
	/**
	 * @return The length of the LineSegment
	 */
	public double length()
	{
			return a.distanceTo ( b );
	}
	
	/**
	 * Returns the theta angle of the {@link LineSegment}
	 * @return The angle in radians.
	 */
	public double theta()
	{
		return line().theta ();
	}
	
	/**
	 * Returns the midpoint of a {@link LineSegment}
	 * @return The midpoint
	 */
	public Point midPoint()
	{
		return PointBuilder.midpoint ( a, b );
	}
	
	/**
	 * Returns a {@link Point} along the {@link LineSegment}.  The ratio argument specifies the distance to travel, in units of the length of the LineSegment.<br>
	 * <br>
	 * If ratio is Zero, then point "a" will be returned.<br>
	 * If ratio is One, then point "b" will be returned.
	 * 
	 * @param ratio The ratio of the length to travel.  This value may be negative, or greater than one to get points outside the limits of the LineSegment.
	 * @return The point.
	 */
	public Point along ( double ratio )
	{
		return PointBuilder.polarOffset ( a, line().theta (), length () * ratio );
	}
	
	@Override
	public boolean equals ( Object obj )
	{
		if ( this == obj ) return true;
		if ( obj == null ) return false;
		if ( getClass () != obj.getClass () ) return false;
		LineSegment other = (LineSegment) obj;
		if ( a == null )
		{
			if ( other.a != null ) return false;
		}
		else if ( !a.equals ( other.a ) ) return false;
		if ( b == null )
		{
			if ( other.b != null ) return false;
		}
		else if ( !b.equals ( other.b ) ) return false;
		return true;
	}

	@Override
	public int hashCode ()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( a == null ) ? 0 : a.hashCode () );
		result = prime * result + ( ( b == null ) ? 0 : b.hashCode () );
		return result;
	}
	
	@Override
	public String toString()
	{
		return "LineSegment(" + a + "," + b + ")";
	}
}

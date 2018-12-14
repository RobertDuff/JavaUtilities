package utility.geometry;

/**
 * This class represents a 2-Dimensional Point.
 * 
 * @author Rob Duff
 */
public class Point
{
	/**
	 * The X Axis Coordinate of the Point.
	 */
	private double x;

	/**
	 * The Y Axis Coordinate of the Point.
	 */
	private double y;
	
	/**
	 * Creates a new Point at the specified Coordinates.
	 * 
	 * @param x The X Axis Coordinate of the New Point.
	 * @param y The Y Axis Coordinate of the New Point.
	 */
	public Point ( double x, double y )
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Creates a new Point at the same location as a specified Point.
	 * 
	 * @param a The <code>Point</code> to copy.
	 */
	public Point ( Point a )
	{
		if ( a == null )
			throw new NullPointerException ( "Point cannot be null" );
		
		x = a.x;
		y = a.y;
	}

	/**
	 * Returns the X Axis Coordinate of the Point.
	 * 
	 * @return The X Axis Coordinate of the Point.
	 */
	public double x()
	{
		return x;
	}
	
	/**
	 * Returns the Y Axis Coordinate of the Point.
	 * 
	 * @return The Y Axis Coordinate of the Point.
	 */
	public double y()
	{
		return y;
	}
	
	/**
	 * Calculates the straight line distance between this Point and another Point.
	 * 
	 * @param a Another <code>Point</code>.
	 * @return The straight line distances between the Points.
	 */
	public double distanceTo ( Point a )
	{
		return Math.sqrt ( Math.pow ( x - a.x, 2 ) + Math.pow ( y - a.y, 2 ) );
	}
	
	/**
	 * Calculates the angle from this Point to another Point
	 * @param a Another {@code Point}
	 * @return The angle in Radians
	 */
	public double directionTo ( Point a )
	{
		return Math.atan2 ( a.y - y, a.x - x );
	}

	@Override
	public boolean equals ( Object obj )
	{
		if ( this == obj ) return true;
		if ( obj == null ) return false;
		if ( getClass () != obj.getClass () ) return false;
		Point other = (Point) obj;
		if ( Double.doubleToLongBits ( x ) != Double.doubleToLongBits ( other.x ) ) return false;
		if ( Double.doubleToLongBits ( y ) != Double.doubleToLongBits ( other.y ) ) return false;
		return true;
	}

	@Override
	public int hashCode ()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits ( x );
		result = prime * result + (int) ( temp ^ ( temp >>> 32 ) );
		temp = Double.doubleToLongBits ( y );
		result = prime * result + (int) ( temp ^ ( temp >>> 32 ) );
		return result;
	}
	
	@Override
	public String toString()
	{
		return "Point(" + x + "," + y + ")";
	}
}

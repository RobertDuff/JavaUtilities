package utility.geometry;

/**
 * Non-Instantiatable Builder Class to Create <code>Point</code> Objects.
 * 
 * @author Rob Duff
 */
public class PointBuilder
{
	/**
	 * This Class cannot be instantiated.
	 */
	private PointBuilder()
	{}
	
	/**
	 * Calculates the midpoint between two Points.
	 * 
	 * @param a The First <code>Point</code>.
	 * @param b The Second <code>Point</code>.
	 * @return A <code>Point</code> located at the midpoint between the 2 points.
	 */
	public static Point midpoint ( Point a, Point b )
	{
		return midpoint ( a.x(), a.y (), b.x (), b.y() );
	}
	
	/**
	 * Calculates the midpoint between two Points.
	 * 
	 * @param x1 The X Axis Coordinate of the First Point.
	 * @param y1 The Y Axis Coordinate of the First Point.
	 * @param x2 The X Axis Coordinate of the Second Point.
	 * @param y2 The Y Axis Coordinate of the Second Point.
	 * @return A <code>Point</code> located at the midpoint between the 2 points.
	 */
	public static Point midpoint ( double x1, double y1, double x2, double y2 )
	{
		return new Point ( ( ( x1 + x2 ) / 2 ), ( ( y1 + y2 ) / 2 ) );
	}
	
	/**
	 * Calculates the Point of Intersection of two Lines.
	 * 
	 * @param a The First Line.
	 * @param b The Second Line.
	 * @return The Point at which the two Lines intersect, or null if the two Lines are Parallel.
	 */
	public static Point intersection ( Line a, Line b )
	{
		if ( a.isParallel ( b ) )
			return null;

		if ( a.isVertical() )
			return new Point ( a.xIntercept(), b.y ( a.xIntercept() ) );
		
		if ( b.isVertical() )
			return new Point ( b.xIntercept(), a.y ( b.xIntercept() ) );
		
		double x = ( a.yIntercept() - b.yIntercept() ) / ( b.slope() - a.slope() );
		double y = a.y ( x );

		return new Point ( x, y );
	}
	
	/**
	 * Returns a Point at a given Angle on the Circumference of a Circle.
	 * 
	 * @param circle The Circle.
	 * @param theta The Angle.
	 * @return The Point at the given Angle on the Circumference of the Circle.
	 */
	public static Point onCircle ( Circle circle, double theta )
	{
		double x = circle.center().x() + circle.radius() * Math.cos ( theta );
		double y = circle.center().y() + circle.radius() * Math.sin ( theta );
		
		return new Point ( x, y );
	}

	/**
	 * Returns a Point at a given Angle on the Circumference of an Ellipse.
	 * 
	 * @param ellipse The Ellipse.
	 * @param theta The Angle.
	 * @return The Point at the given Angle on the Circumference of the Ellipse.
	 */
	public static Point onEllipse ( Ellipse ellipse, double theta )
	{
		double x = ellipse.center().x() + ellipse.xRadius() * Math.cos ( theta );
		double y = ellipse.center().y() + ellipse.yRadius() * Math.sin ( theta );
		
		return new Point ( x, y );
	}
	
	/**
	 * Returns a Point at a given offset from a base {@link Point}
	 * @param point The base Point.
	 * @param x The X offset.
	 * @param y The Y offset.
	 * @return The new Point.
	 */
	public static Point offset ( Point point, double x, double y )
	{
		return new Point ( point.x () + x, point.y () + y );
	}
	
	/**
	 * Returns a {@link Point} at the given offset, specified in polar coordinates, from a base Point.
	 * @param point The base Point.
	 * @param theta The angle of offset.
	 * @param radius The distance of offset.
	 * @return The new Point.
	 */
	public static Point polarOffset ( Point point, double theta, double radius )
	{
		return new Point ( point.x () + Math.cos ( theta ) * radius, point.y () + Math.sin ( theta ) * radius );
	}
}

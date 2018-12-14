package utility.geometry;

/**
 * Non-Instantiatable Builder Class to Create <code>Circle</code> Objects.
 * 
 * @author Rob Duff
 */
public class CircleBuilder 
{
	/**
	 * Creates a Circle which is Outscribed on Two Points.
	 * @param a The First Point.
	 * @param b The Second Point.
	 * @return The New Circle.
	 */
	public static Circle outscribedCircle ( Point a, Point b )
	{
		if ( a == null || b == null )
			throw new NullPointerException ( "Neither point can be null" );
		
		Point center = PointBuilder.midpoint ( a, b );
		double radius = center.distanceTo ( a );
		
		return new Circle ( center, radius );
	}

	/**
	 * Creates a Circle which is Outscribed on Three Points.
	 * 
	 * @param a The First Point.
	 * @param b The Second Point.
	 * @param c The Third Point.
	 * @return The New Circle, or null if the Three Points are co-linear.
	 */
	public static Circle outscribedCircle ( Point a, Point b, Point c )
	{
		if ( a == null || b == null || c == null )
			throw new NullPointerException ( "Neither a, nor b, nor c may be null" );
		
		Line ab = LineBuilder.between ( a, b );
		Line bc = LineBuilder.between ( b, c );
		
		Point midAB = PointBuilder.midpoint ( a, b );
		Point midBC = PointBuilder.midpoint ( b, c );

		Line perpAB = LineBuilder.perpendicularThruPoint ( ab, midAB );
		Line perpBC = LineBuilder.perpendicularThruPoint ( bc, midBC );

		Point center = PointBuilder.intersection ( perpAB, perpBC );

		if ( center == null )
			return null;

		double radius = center.distanceTo ( a );
		
		return new Circle ( center, radius );
	}
	
	/**
	 * Creates a Circle which is Inscribed on Three Points.
	 * 
	 * @param a The First Point.
	 * @param b The Second Point.
	 * @param c The Third Point.
	 * @return The New Circle.
	 */
	public static Circle inscribedCircle ( Point a, Point b, Point c )
	{
		if ( a == null || b == null || c == null )
			throw new NullPointerException ( "Neither a, nor b, nor c may be null" );
		
		double lenA = b.distanceTo ( c );
		double lenB = a.distanceTo ( c );
		double lenC = a.distanceTo ( b );
		
		double x = ( ( lenA * a.x() + lenB * b.x() + lenC * c.x() ) / ( lenA + lenB + lenC ) );
		double y = ( ( lenA * a.y() + lenB * b.y() + lenC * c.y() ) / ( lenA + lenB + lenC ) );
		
		Point center = new Point ( x, y );
		
		Line ab = LineBuilder.between ( a, b );
		Line perp = LineBuilder.perpendicularThruPoint ( ab, center );
		Point intersection = PointBuilder.intersection ( ab, perp );
		
		double radius = center.distanceTo ( intersection );
				
		return new Circle ( center, radius );
	}
}

package utility.geometry;

/**
 * Non-Instantiatable Builder Class to Create <code>Line</code> Objects.
 * 
 * @author Rob Duff
 */
public class LineBuilder
{
	/**
	 * Creates a Line with the Given Slope and Y Axis Intercept
	 * @param slope The Slope of the Line.
	 * @param yIntercept The Y Axis Intercept
	 * @return The New Line.
	 * @throws IllegalArgumentException if the Slope is Vertical, since the line cannot have a Y Axis Intercept.
	 */
	public static Line slopeYIntercept ( double slope, double yIntercept )
	{
		if ( Line.isSlopeVertical ( slope ) )
			throw new IllegalArgumentException ( "Cannot create a Vertical Line with a Y-Intercept" );

		double theta = Math.atan ( slope );
		return new Line ( theta, yIntercept );
	}

	/**
	 * Creates a Vertical Line at the Given X Axis Coordinate.
	 * 
	 * @param xIntercept The X Axis Coordinate.
	 * @return The new Line.
	 */
	public static Line vertical ( double xIntercept )
	{
		return new Line ( Line.VERTICAL_THETA, xIntercept );
	}
	
	/**
	 * Creates a Horizontal Line at the Given Y Axis Coordinate.
	 * 
	 * @param yIntercept The Y Axis Coordinate.
	 * @return The new Line.
	 */
	public static Line horizontal ( double yIntercept )
	{
		return new Line ( 0, yIntercept );
	}
	
	/**
	 * Creates a Line passing through Two Points.
	 * 
	 * @param a The First Point.
	 * @param b The Second Point.
	 * @return The New Line.
	 * @throws IllegalArgumentException if the Two Points represent the Same Point.
	 */
	public static Line between ( Point a, Point b )
	{
		if ( a == null || b == null )
			throw new NullPointerException ( "Neither a nor b may be null" );
		
		return between ( a.x(), a.y(), b.x(), b.y() );
	}
	
	/**
	 * Creates a Line passing through Two Points.
	 * 
	 * @param x1 The X Axis Coordinate of the First Point.
	 * @param y1 The Y Axis Coordinate of the First Point.
	 * @param x2 The X Axis Coordinate of the Second Point.
	 * @param y2 The Y Axis Coordinate of the Second Point.
	 * @return The New Line.
	 * @throws IllegalArgumentException if the Two Points represent the Same Point.
	 */
	public static Line between ( double x1, double y1, double x2, double y2 )
	{
		if ( x1 == x2 && y1 == y2 )
			throw new IllegalArgumentException ( "The Two Points represent the Same Point." );
		
		double numerator   = y2 - y1;
		double denominator = x2 - x1;
		
//		if ( denominator == 0 )
//			return vertical ( x1 );

		double theta = Math.atan2 ( numerator, denominator );
		double slope = Line.slope ( theta );
		double yIntercept = y2 - slope * x2;
		
		// If line is vertical, the constructor uses the intercept as the x intercept.
		return new Line ( theta, denominator == 0? x1 : yIntercept );
	}
	
	/**
	 * Creates a Line at the Given Angle that Passes throught the Given Point.
	 * 
	 * @param theta The Angle.
	 * @param point The Point.
	 * @return The New Line.
	 */
	public static Line thetaThruPoint ( double theta, Point point )
	{
		if ( point == null )
			throw new NullPointerException ( "Point may not be null" );
		
		if ( Line.isThetaVertical ( theta ) )
			return vertical ( point.x() );
		
		double yIntercept  = point.y() - Line.slope ( theta ) * point.x();
		return new Line ( theta, yIntercept );
	}
	
	/**
	 * Creates a Line at the Given Slope that Passes throught the Given Point.
	 * 
	 * @param slope The Slope.
	 * @param point The Point.
	 * @return The New Line.
	 */
	public static Line slopeThruPoint ( double slope, Point point )
	{
		if ( point == null )
			throw new NullPointerException ( "Point may not be null" );
		
		return thetaThruPoint ( Line.theta ( slope ), point );
	}
	
	/**
	 * Creates a Line Parallel to a Given Line that Passes through a Given Point.
	 * 
	 * @param referenceLine The Referenced Line.
	 * @param point The Point.
	 * @return The New Line.
	 */
	public static Line parallelThruPoint ( Line referenceLine, Point point )
	{
		if ( point == null )
			throw new NullPointerException ( "Point may not be null" );
		
		if ( referenceLine == null )
			throw new NullPointerException ( "Line may not be null" );
		
		return thetaThruPoint ( referenceLine.theta(), point );
	}
	
	/**
	 * Creates a Line Perpendicular to a Given Line that Passes through a Given Point.
	 * 
	 * @param referenceLine The Referenced Line.
	 * @param point The Point.
	 * @return The New Line.
	 */
	public static Line perpendicularThruPoint ( Line referenceLine, Point point )
	{
		if ( point == null )
			throw new NullPointerException ( "Point may not be null" );
		
		if ( referenceLine == null )
			throw new NullPointerException ( "Line may not be null" );
		
		if ( referenceLine.isHorizontal() )
			return vertical ( point.x() );
		
		if ( referenceLine.isVertical() )
			return horizontal ( point.y() );
		
		return slopeThruPoint ( Line.perpendicularSlope ( referenceLine.slope() ), point );
	}
}

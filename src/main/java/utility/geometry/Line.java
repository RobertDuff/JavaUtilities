package utility.geometry;

/**
 * This class represents a 2-Dimensional Line.
 * 
 * @author Rob Duff
 */
public class Line
{
	/**
	 * This is the Theta value for a Vertical Line.
	 */
	public static final double VERTICAL_THETA = Math.PI / 2;

	/**
	 * This is the Theta value for a Horizontal Line.
	 */
	public static final double HORIZONTAL_THETA = 0;

	/**
	 * This is the Slope for a Vertical Line.<br>
	 * Since the Slope of a Vertical Line is not defined, it is equivalent to Double.NaN.
	 */
	public static final double VERTICAL_SLOPE = Double.NaN;

	/**
	 * This is the Slope for a Horizontal Line.<br>
	 */
	public static final double HORIZONTAL_SLOPE = 0;

	/**
	 * Returns the Slope of a Line with the given Angle.
	 * 
	 * @param theta An Angle in Radians.
	 * @return The calculated Slope.
	 */
	public static double slope ( double theta )
	{
		if ( compare ( theta, VERTICAL_THETA ) )
			return VERTICAL_SLOPE;
		
		return Math.tan ( theta );
	}
	
	/**
	 * Return the Angle of a Line with the given Slope.
	 * 
	 * @param slope The Slope of the Line.
	 * @return The Angle in Radians.
	 */
	public static double theta ( double slope )
	{
		if ( Double.isNaN ( slope ) )
			return VERTICAL_THETA;
		
		return Math.atan ( slope );
	}
	
	/**
	 * Calculates the Perpendicular to an Angle.
	 * 
	 * @param theta The Given Angle
	 * @return The Perpendicular Angle
	 */
	public static double perpendicularTheta ( double theta )
	{
		if ( theta <= 0 )
			return theta += Math.PI / 2;
		
		return theta -= Math.PI / 2;
	}
	
	/**
	 * Calculates the Perpendicular to an Slope.
	 * 
	 * @param slope The Given Slope
	 * @return The Perpendicular Slope
	 */
	public static double perpendicularSlope ( double slope )
	{
		if ( isSlopeVertical ( slope ) )
			return 0;
		
		if ( isSlopeHorizontal ( slope ) )
			return VERTICAL_SLOPE;
		
		return -1 / slope;
	}
	
	/**
	 * Determines if an Angle is Vertical.
	 * 
	 * @param theta An Angle
	 * @return <code>true</code> if the Angle is Vertical.
	 */
	public static boolean isThetaVertical ( double theta )
	{
		return compare ( Math.abs ( theta ), VERTICAL_THETA );
	}
	
	/**
	 * Determines if a Slope is Vertical.
	 * 
	 * @param slope A Slope
	 * @return <code>true</code> if the Slope is Vertical.
	 */
	public static boolean isSlopeVertical ( double slope )
	{
		return Double.isNaN ( slope );
	}
	
	/**
	 * Determines if an Angle is Horizontal.
	 * 
	 * @param theta An Angle
	 * @return <code>true</code> if the Angle is Horizontal.
	 */
	public static boolean isThetaHorizontal ( double theta )
	{
		if ( compare ( theta, HORIZONTAL_THETA ) )
			return true;
		
		return compare ( theta, Math.PI );
	}
	
	/**
	 * Determines if a Slope is Horizontal.
	 * 
	 * @param slope A Slope
	 * @return <code>true</code> if the Slope is Horizontal.
	 */
	public static boolean isSlopeHorizontal ( double slope )
	{
		return compare ( slope, HORIZONTAL_SLOPE );
	}
	
	private static final double TOLERANCE = 0.0000000000001;
	
	private static boolean compare ( double p, double q )
	{
		return Math.abs ( p - q ) < TOLERANCE;
	}
	
	/**
	 * The Angle of the Line, in Radians.  This value is between -PI/2 to PI/2.
	 */
	private double theta;

	/**
	 * This is the Slope of the Line.
	 */
	private  double  slope;

	/**
	 * This is the X Axis Intercept of the Line.
	 */
	private double xIntercept;

	/**
	 * This is the Y Axis Intercept of the Line.
	 */
	private  double  yIntercept;

	/**
	 * Construct a Line Object given an Angle and an Axis Intercept
	 * 
	 * @param theta The Angle of the Line.
	 * @param intercept If <code>theta</code> is Vertical, then intercept should be the X Axis Intercept. Otherwise, it should be the Y Axis Intercept.
	 */
	public Line ( double theta, double intercept )
	{
		this.theta = theta;
		slope = slope ( this.theta );
		
		if ( isThetaVertical ( this.theta ) )
		{
			xIntercept = intercept;
			yIntercept = Double.NaN;
		}
		else if ( isThetaHorizontal ( this.theta ) )
		{
			xIntercept = Double.NaN;
			yIntercept = intercept;
		}
		else
		{

			yIntercept = intercept;
			xIntercept = -yIntercept / slope;
		}
	}
	
	/**
	 * Copy Constructor.
	 * 
	 * @param line A Line to Copy.
	 */
	public Line ( Line line )
	{
		if ( line == null )
			throw new NullPointerException ( "Line cannot be null" );
		
		theta = line.theta;
		slope = line.slope;
		xIntercept = line.xIntercept;
		yIntercept = line.yIntercept;
	}
	
	/**
	 * Returns the Angle of the Line.
	 * 
	 * @return The Angle of the Line in Radians.
	 */
	public double theta()
	{
		return theta;
	}

	/**
	 * Returns the Slope of the Line.
	 * 
	 * @return The Slope of the Line.  If the Slope is Vertical, returns <code>Double.NaN</code>.
	 */
	public double slope()
	{
		return slope;
	}

	/**
	 * Returns the X Axis Intercept of the Line.
	 * 
	 * @return The X Axis Intercept Coordinate, or <code>Double.NaN</code> if the Line is Horizontal.
	 */
	public double xIntercept()
	{
		return xIntercept;
	}

	/**
	 * Returns the Y Axis Intercept of the Line.
	 * 
	 * @return The Y Axis Intercept Coordinate, or <code>Double.NaN</code> if the Line is Vertical.
	 */
	public double yIntercept()
	{
		return yIntercept;
	}

	/**
	 * Determines if the Line is Vertical.
	 * 
	 * @return <code>true</code> if the Line is Vertical.
	 */
	public boolean isVertical()
	{
		return isThetaVertical ( theta );
	}

	/**
	 * Determines if the Line is Horizontal.
	 * 
	 * @return <code>true</code> if the Line is Horizontal.
	 */
	public boolean isHorizontal()
	{
		return isThetaHorizontal ( theta );
	}

	/**
	 * Determines if this Line is Parallel to another Line.
	 * 
	 * @param a A Line.
	 * @return <code>true</code> if the two Lines are Parallel.
	 */
	public boolean isParallel ( Line a )
	{
		return compare ( theta, a.theta );
	}

	/**
	 * Determines if this Line is Perpendicular to another Line.
	 * 
	 * @param a A Line.
	 * @return <code>true</code> if the two Lines are Perpendicular.
	 */
	public boolean isPerpendicular ( Line a )
	{
		if ( isVertical() )
			return a.isHorizontal();

		if ( a.isVertical() )
			return isHorizontal();

		return compare ( slope, -1 / a.slope );
	}

	/**
	 * Returns the Y Coordinate for an X Coordinate on the Line.
	 * 
	 * @param x The X Axis Coordinate.
	 * @return The Y Axis Coordinate.
	 */
	public double y ( double x )
	{
		if ( isVertical() )
			return 0;

		return slope * x + yIntercept;
	}
}

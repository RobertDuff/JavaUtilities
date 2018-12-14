package utility.geometry;

/**
 * This class represents an Ellipse.
 * 
 * @author Robert Duff
 *
 */
public class Ellipse
{
	/**
	 * The Center of the Ellipse
	 */
	protected Point center;
	
	/**
	 * The X Radius of the Ellipse
	 */
	protected double xRadius;
	
	/**
	 * The Y Radius of the Ellipse
	 */
	protected double yRadius;
	
	/**
	 * Construct a new Ellipse
	 * 
	 * @param center The center Point of the Ellipse.
	 * @param xRadius The X radius of the Ellipse.
	 * @param yRadius The Y radius of the Ellipse.
	 */
	public Ellipse ( Point center, double xRadius, double yRadius )
	{
		this.center = center;
		this.xRadius = xRadius;
		this.yRadius = yRadius;
	}
	
	/**
	 * Construct a new Ellipse.
	 * 
	 * @param x The X Axis Coordinate of the Center of the Ellipse.
	 * @param y The Y Axis Coordinate of the Center of the Ellipse.
	 * @param xRadius The X radius of the Ellipse.
	 * @param yRadius The Y radius of the Ellipse.
	 */
	public Ellipse ( double x, double y, double xRadius, double yRadius )
	{
		this ( new Point ( x, y ), xRadius, yRadius );
	}
	
	/**
	 * Returns the Center Point of the Ellipse.
	 * @return Teh center Point.
	 */
	public Point center()
	{
		return center;
	}
	
	/**
	 * Returns the X Radius of the Ellipse.
	 * @return The X radius.
	 */
	public double xRadius()
	{
		return xRadius;
	}
	
	/**
	 * Returns the Y Radius of the Ellipse.
	 * @return The Y radius.
	 */
	public double yRadius()
	{
		return yRadius;
	}
	
	/**
	 * Returns the Area of the Ellipse.
	 * @return The area of the Ellipse.
	 */
	public double area()
	{
		return Math.PI * xRadius * yRadius;
	}
}

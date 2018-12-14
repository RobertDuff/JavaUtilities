package utility.geometry;

/**
 * This class represents a Circle.
 * 
 * @author Rob Duff
 */
public class Circle
{
	/**
	 * The Center of the Circle
	 */
	private Point  center;
	
	/**
	 * The Radius of the Circle.<p>Must be greater than 0.
	 */
	private double radius;

	/**
	 * Construct a new Circle.
	 * 
	 * @param x The X Axis Coordinate of the Circle's Center Point.
	 * @param y The Y Axis Coordinate of the Circle's Center Point.
	 * @param radius The Radius of the Circle.
	 */
	public Circle ( double x, double y, double radius )
	{
		this ( new Point ( x, y ), radius );
	}
	
	/**
	 * Construct a new Circle.
	 * 
	 * @param center The Circle's Center <code>Point</code>.
	 * @param radius The Radius of the Circle.
	 */
	public Circle ( Point center, double radius )
	{
		if ( center == null )
			throw new NullPointerException ( "Center point cannot be null" );
		
		this.center = center;
		this.radius = Math.abs ( radius );
	}
	
	/**
	 * Copy Constructor.
	 * 
	 * @param circle A Circle to Copy.
	 */
	public Circle ( Circle circle )
	{
		if ( circle == null )
			throw new NullPointerException ( "Circle cannot be null" );
		
		center = new Point ( circle.center );
		radius = circle.radius;
	}
	
	/**
	 * Returns the Center <code>Point</code> of the Circle.
	 * 
	 * @return The Circle's Center <code>Point</code>.
	 */
	public Point center()
	{
		return center;
	}
	
	/**
	 * Returns the Circle's Radius.
	 * @return The Circle's Radius.
	 */
	public double radius()
	{
		return radius;
	}
	
	/**
	 * @return The circumference of the Circle.
	 */
	public double circumference()
	{
		return 2 * Math.PI * radius;
	}
	
	/**
	 * @return The area of the Circle.
	 */
	public double area()
	{
		return Math.PI * radius * radius;
	}
}

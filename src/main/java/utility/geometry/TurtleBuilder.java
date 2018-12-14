package utility.geometry;

/**
 * Builds {@link Turtle}s quickly.
 * 
 * @author Robert Duff
 */
public class TurtleBuilder
{
	/**
	 * Build a turtle based on a given {@link LineSegment}.<br>
	 * <br>
	 * The turtle will be placed at the LineSegment's "A" point.<br>
	 * The turtle will be aimed at the LineSegment's "B" point.<br>
	 * The turtle's default radius of movement will be set to the length of the LineSegment.<br>
	 * @param l The LineSegement
	 * @return A new {@link Turtle}.
	 */
	public static Turtle build ( final LineSegment l )
	{
		if ( l == null ) throw new NullPointerException ( "Line segment cannot be null" );

		Turtle turtle = new Turtle ();

		turtle.moveTo ( l.a () );
		turtle.aim ( l.b () );
		turtle.setRadius ( l.length () );

		return turtle;
	}

	/**
	 * Build a turtle based on two {@link Point}s.<br>
	 * <br>
	 * The turtle will be placed at point "a".<br>
	 * The turtle will be aimed at point "b".<br>
	 * The turtle's default radius of movement will be set to the distance between the two points.<br>
	 * @param a The first point, used for the location of the turtle.
	 * @param b The second point, used to calculate the direction of the turtle, and the default radius of movement.
	 * @return A new {@link Turtle}.
	 */
	public static Turtle build ( final Point a, final Point b )
	{
		return build ( new LineSegment ( a, b ) );
	}

	/**
	 * Build a turtle based on two points.<br>
	 * <br>
	 * The turtle will be placed at the first point.<br>
	 * The turtle will be aimed at the second point.<br>
	 * The turtle's default radius of movement will be set to the distance between the two points.<br>
	 * @param x1 The X coordinate of the first point.
	 * @param y1 The Y coordinate of the first point.
	 * @param x2 The X coordinate of the second point.
	 * @param y2 The Y coordinate of the second point.
	 * @return A new {@link Turtle}.
	 */
	public static Turtle build ( double x1, double y1, double x2, double y2 )
	{
		return build ( new Point ( x1, y1 ), new Point ( x2, y2 ) );
	}
}

package utility.geometry;

import static org.junit.Assert.*;

import org.junit.Test;

public class TurtleBuilderTest
{
	private static final double TOLERANCE = 0.000000001;

	@Test
	public void testBuildLineSegment ()
	{
		LineSegment l = new LineSegment ( -3, -1, 2, 4 );
		Turtle t = TurtleBuilder.build ( l );

		assertEquals ( -3, t.point ().x (), TOLERANCE );
		assertEquals ( -1, t.point ().y (), TOLERANCE );
		assertEquals ( Math.PI / 4, t.theta (), TOLERANCE );
		assertEquals ( 5 * Math.sqrt ( 2 ), t.radius (), TOLERANCE );
	}

	@Test
	public void testBuildPointPoint ()
	{
		Turtle t = TurtleBuilder.build ( new Point ( -5, 2 ), new Point ( -1, -2 ) );

		assertEquals ( -5, t.point ().x (), TOLERANCE );
		assertEquals (  2, t.point ().y (), TOLERANCE );
		assertEquals ( -Math.PI / 4, t.theta (), TOLERANCE );
		assertEquals ( 4 * Math.sqrt ( 2 ), t.radius (), TOLERANCE );
	}

	@Test
	public void testBuildDoubleDoubleDoubleDouble ()
	{
		Turtle t = TurtleBuilder.build ( 4, -2, 4, 4 );

		assertEquals (  4, t.point ().x (), TOLERANCE );
		assertEquals ( -2, t.point ().y (), TOLERANCE );
		assertEquals ( Math.PI / 2, t.theta (), TOLERANCE );
		assertEquals ( 6, t.radius (), TOLERANCE );
	}
}

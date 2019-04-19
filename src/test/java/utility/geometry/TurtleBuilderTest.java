package utility.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TurtleBuilderTest
{
	private static final double TOLERANCE = 0.000000001;

	@Test
	public void testBuildLineSegment ()
	{
		LineSegment l = new LineSegment ( -3, -1, 2, 4 );
		Turtle t = TurtleBuilder.build ( l );

		Assertions.assertEquals ( -3, t.point ().x (), TOLERANCE );
		Assertions.assertEquals ( -1, t.point ().y (), TOLERANCE );
		Assertions.assertEquals ( Math.PI / 4, t.theta (), TOLERANCE );
		Assertions.assertEquals ( 5 * Math.sqrt ( 2 ), t.radius (), TOLERANCE );
	}

	@Test
	public void testBuildPointPoint ()
	{
		Turtle t = TurtleBuilder.build ( new Point ( -5, 2 ), new Point ( -1, -2 ) );

		Assertions.assertEquals ( -5, t.point ().x (), TOLERANCE );
		Assertions.assertEquals (  2, t.point ().y (), TOLERANCE );
		Assertions.assertEquals ( -Math.PI / 4, t.theta (), TOLERANCE );
		Assertions.assertEquals ( 4 * Math.sqrt ( 2 ), t.radius (), TOLERANCE );
	}

	@Test
	public void testBuildDoubleDoubleDoubleDouble ()
	{
		Turtle t = TurtleBuilder.build ( 4, -2, 4, 4 );

		Assertions.assertEquals (  4, t.point ().x (), TOLERANCE );
		Assertions.assertEquals ( -2, t.point ().y (), TOLERANCE );
		Assertions.assertEquals ( Math.PI / 2, t.theta (), TOLERANCE );
		Assertions.assertEquals ( 6, t.radius (), TOLERANCE );
	}
}

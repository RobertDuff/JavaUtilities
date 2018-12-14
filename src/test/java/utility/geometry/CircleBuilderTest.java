package utility.geometry;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CircleBuilderTest
{
	private static final double TOLERANCE = 0.000000001;

	@Test
	public void testOutscribedCirclePointPoint()
	{
		Point x;
		Point y;
		Circle c;
		
		x = new Point ( 4, 4 );
		y = new Point ( 4, 8 );
		c = CircleBuilder.outscribedCircle ( x, y );
		assertEquals ( 4, c.center().x(), TOLERANCE  );
		assertEquals ( 6, c.center().y(), TOLERANCE  );
		assertEquals ( 2, c.radius(), TOLERANCE  );
		
		x = new Point ( 4, 4 );
		y = new Point ( 10, 4 );
		c = CircleBuilder.outscribedCircle ( x, y );
		assertEquals ( 7, c.center().x(), TOLERANCE  );
		assertEquals ( 4, c.center().y(), TOLERANCE  );
		
		x = new Point ( 4, 4 );
		y = new Point ( 10, 10 );
		c = CircleBuilder.outscribedCircle ( x, y );
		assertEquals ( 7, c.center().x(), TOLERANCE  );
		assertEquals ( 7, c.center().y(), TOLERANCE  );
		assertEquals ( 3 * Math.sqrt ( 2 ), c.radius(), TOLERANCE  );
	}

	@Test
	public void testOutscribedCirclePointPointPoint()
	{
		Point x;
		Point y;
		Point z;
		Circle c;
		
		x = new Point ( 1, 6 );
		y = new Point ( 6, 1 );
		z = new Point ( 5, 5 );
		c = CircleBuilder.outscribedCircle ( x, y, z );
		assertEquals ( c.center().x(), c.center().y(), TOLERANCE );
		assertEquals ( 4.006938426723769, c.radius(), TOLERANCE );
	}

	@Test
	public void testInscribedCircle()
	{
		Point x;
		Point y;
		Point z;
		Circle c;
		
		x = new Point ( 1, 6 );
		y = new Point ( 6, 1 );
		z = new Point ( 5, 5 );
		c = CircleBuilder.inscribedCircle ( x, y, z );
		assertEquals ( c.center().x(), c.center().y(), TOLERANCE );
		assertEquals ( 0.9792861994748723, c.radius(), TOLERANCE );
	}
}

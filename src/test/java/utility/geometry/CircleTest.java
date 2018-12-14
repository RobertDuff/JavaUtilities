package utility.geometry;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CircleTest
{
	private static final double TOLERANCE = 0.000000001;

	@Test
	public void testCircleDoubleDoubleDouble()
	{
		Circle c = new Circle ( 4, 5, 8 );
		assertEquals ( 4, c.center().x(), TOLERANCE  );
		assertEquals ( 5, c.center().y(), TOLERANCE  );
		assertEquals ( 8, c.radius(), TOLERANCE  );
		
		c = new Circle ( 4, 5, -8 );
		assertEquals ( 4, c.center().x(), TOLERANCE  );
		assertEquals ( 5, c.center().y(), TOLERANCE  );
		assertEquals ( 8, c.radius(), TOLERANCE  );
	}

	@Test
	public void testCirclePointDouble()
	{
		Point p = new Point ( 4, 5 );
		Circle c = new Circle ( p, 8 );
		assertEquals ( 4, c.center().x(), TOLERANCE  );
		assertEquals ( 5, c.center().y(), TOLERANCE  );
		assertEquals ( 8, c.radius(), TOLERANCE  );
		
		c = new Circle ( p, 0 );
		assertEquals ( 4, c.center().x(), TOLERANCE  );
		assertEquals ( 5, c.center().y(), TOLERANCE  );
		assertEquals ( 0, c.radius(), TOLERANCE  );
	}

	@Test
	public void testCircleCircle() 
	{
		Circle a = new Circle ( 4, 5, 8 );
		Circle c = new Circle ( a );
		assertEquals ( 4, c.center().x(), TOLERANCE  );
		assertEquals ( 5, c.center().y(), TOLERANCE  );
		assertEquals ( 8, c.radius(), TOLERANCE  );
	}
}

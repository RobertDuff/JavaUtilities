package utility.geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class PointBuilderTest
{
	private static final double TOLERANCE = 0.0000001;
	@Test
	public void testMidpointPointPoint()
	{
		Point a;
		Point b;
		Point c;
		
		a = new Point ( 3, 4 );
		b = new Point ( 5, 4 );
		c = PointBuilder.midpoint ( a, b );
		assertEquals ( 4, c.x(), TOLERANCE );
		assertEquals ( 4, c.y(), TOLERANCE );
		
		a = new Point ( 4, 3 );
		b = new Point ( 4, 5 );
		c = PointBuilder.midpoint ( a, b );
		assertEquals ( 4, c.x(), TOLERANCE );
		assertEquals ( 4, c.y(), TOLERANCE );
		
		a = new Point ( 1, 1 );
		b = new Point ( 3, 3 );
		c = PointBuilder.midpoint ( a, b );
		assertEquals ( 2, c.x(), TOLERANCE );
		assertEquals ( 2, c.y(), TOLERANCE );
		
		c = PointBuilder.midpoint ( b, b );
		assertEquals ( 3, c.x(), TOLERANCE );
		assertEquals ( 3, c.y(), TOLERANCE );
	}

	@Test
	public void testMidpointDoubleDoubleDoubleDouble()
	{
		Point c;
		
		c = PointBuilder.midpoint ( 3, 4, 5, 4 );
		assertEquals ( 4, c.x(), TOLERANCE );
		assertEquals ( 4, c.y(), TOLERANCE );
		
		c = PointBuilder.midpoint ( 4, 3, 4, 5 );
		assertEquals ( 4, c.x(), TOLERANCE );
		assertEquals ( 4, c.y(), TOLERANCE );
		
		c = PointBuilder.midpoint ( 1, 1, 3, 3 );
		assertEquals ( 2, c.x(), TOLERANCE );
		assertEquals ( 2, c.y(), TOLERANCE );
		
		c = PointBuilder.midpoint ( 3, 3, 3, 3 );
		assertEquals ( 3, c.x(), TOLERANCE );
		assertEquals ( 3, c.y(), TOLERANCE );
	}

	@Test
	public void testIntersection()
	{
		Line a;
		Line b;
		Point c;
		
		a = new Line ( 0, 0 );
		b = new Line ( Math.PI / 2, 0 );
		c = PointBuilder.intersection ( a, b );
		assertEquals ( 0, c.x(), TOLERANCE );
		assertEquals ( 0, c.y(), TOLERANCE );
		
		a = new Line ( 0, 10 );
		b = new Line ( Math.PI / 2, 33 );
		c = PointBuilder.intersection ( a, b );
		assertEquals ( 33, c.x(), TOLERANCE );
		assertEquals ( 10, c.y(), TOLERANCE );
		
		a = new Line ( Math.PI / 4, 0 );
		b = new Line ( -Math.PI / 4, 4 );
		c = PointBuilder.intersection ( a, b );
		assertEquals ( 2, c.x(), TOLERANCE );
		assertEquals ( 2, c.y(), TOLERANCE );
		
		assertNull ( PointBuilder.intersection ( a, a ) );
	}

	@Test
	public void testOnCircle()
	{
		Circle a;
		Point c;
		
		a = new Circle ( 0, 0, 5 );
		
		c = PointBuilder.onCircle ( a, 0 );
		assertEquals ( 5, c.x(), TOLERANCE );
		assertEquals ( 0, c.y(), TOLERANCE );
		
		c = PointBuilder.onCircle ( a, Math.PI / 2 );
		assertEquals ( 0, c.x(), TOLERANCE );
		assertEquals ( 5, c.y(), TOLERANCE );
		
		c = PointBuilder.onCircle ( a, Math.PI );
		assertEquals ( -5, c.x(), TOLERANCE );
		assertEquals (  0, c.y(), TOLERANCE );
		
		c = PointBuilder.onCircle ( a, -Math.PI / 2 );
		assertEquals (  0, c.x(), TOLERANCE );
		assertEquals ( -5, c.y(), TOLERANCE );
		
		c = PointBuilder.onCircle ( a, Math.PI / 4 );
		assertEquals ( 5 / Math.sqrt ( 2 ), c.x(), TOLERANCE );
		assertEquals ( 5 / Math.sqrt ( 2 ), c.y(), TOLERANCE );
	}

	@Test
	public void testOffset()
	{
		Point a;
		Point b;
		
		a = new Point ( 0, 0 );
		b = PointBuilder.offset ( a, 3, -4 );
		assertEquals (  3, b.x (), TOLERANCE );
		assertEquals ( -4, b.y (), TOLERANCE );
		
		a = new Point( -8, 14 );
		b = PointBuilder.offset ( a, 8, -14 );
		assertEquals ( 0, b.x (), TOLERANCE );
		assertEquals ( 0, b.y (), TOLERANCE );
	}
	
	@Test
	public void testPolarOffset()
	{
		Point a;
		Point b;
		
		a = new Point ( 0, 0 );
		
		b = PointBuilder.polarOffset ( a, 0, 2 );
		assertEquals ( 2, b.x (), TOLERANCE );
		assertEquals ( 0, b.y (), TOLERANCE );
		
		b = PointBuilder.polarOffset ( a, Math.PI / 2, 2 );
		assertEquals ( 0, b.x (), TOLERANCE );
		assertEquals ( 2, b.y (), TOLERANCE );
		
		b = PointBuilder.polarOffset ( a, Math.PI, 2 );
		assertEquals ( -2, b.x (), TOLERANCE );
		assertEquals (  0, b.y (), TOLERANCE );
		
		b = PointBuilder.polarOffset ( a, Math.PI * 1.5, 2 );
		assertEquals (  0, b.x (), TOLERANCE );
		assertEquals ( -2, b.y (), TOLERANCE );
		
		b = PointBuilder.polarOffset ( a, Math.PI / 3, 5 );
		assertEquals ( 2.5,            b.x (), TOLERANCE );
		assertEquals ( 4.330127018922, b.y (), TOLERANCE );
	}
	
	@Test
	public void testOnEllipse()
	{
		Ellipse a;
		Point c;
		
		a = new Ellipse ( 0, 0, 5, 10 );
		
		c = PointBuilder.onEllipse ( a, 0 );
		assertEquals ( 5, c.x(), TOLERANCE );
		assertEquals ( 0, c.y(), TOLERANCE );
		
		c = PointBuilder.onEllipse ( a, Math.PI / 2 );
		assertEquals (  0, c.x(), TOLERANCE );
		assertEquals ( 10, c.y(), TOLERANCE );
		
		c = PointBuilder.onEllipse ( a, Math.PI );
		assertEquals ( -5, c.x(), TOLERANCE );
		assertEquals (  0, c.y(), TOLERANCE );
		
		c = PointBuilder.onEllipse ( a, -Math.PI / 2 );
		assertEquals (   0, c.x(), TOLERANCE );
		assertEquals ( -10, c.y(), TOLERANCE );
		
		c = PointBuilder.onEllipse ( a, Math.PI / 4 );
		assertEquals ( 5 / Math.sqrt ( 2 ), c.x(), TOLERANCE );
		assertEquals ( 10 / Math.sqrt ( 2 ), c.y(), TOLERANCE );
	}
}

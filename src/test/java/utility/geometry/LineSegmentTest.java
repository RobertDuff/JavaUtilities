package utility.geometry;

import static org.junit.Assert.*;

import org.junit.Test;


public class LineSegmentTest
{
	private static final double TOLERANCE = 0.000000001;

	@Test
	public void testLine ()
	{
		LineSegment l;
		
		l = new LineSegment ( 1, 1, 2, 2 );		
		assertEquals ( Math.PI / 4, l.line ().theta (), TOLERANCE );
	}

	@Test
	public void testLength ()
	{
		LineSegment l;
		
		l = new LineSegment ( 1, 1, 2, 2 );
		assertEquals ( Math.sqrt ( 2 ), l.length (), TOLERANCE );		
	}
	
	@Test
	public void testString()
	{
		LineSegment l = new LineSegment ( 1, 2, 3, 4 );
		
		assertEquals ( "LineSegment(Point(1.0,2.0),Point(3.0,4.0))", l.toString () );
	}

	@Test
	public void testTheta()
	{
		LineSegment l;
		
		l = new LineSegment ( 0, 0, 1, 0 );
		assertEquals ( 0, l.theta (), TOLERANCE );
		
		l = new LineSegment ( 0, 0, 1, 1 );
		assertEquals ( Math.PI / 4, l.theta (), TOLERANCE );
		
		l = new LineSegment ( 0, 0, 0, 1 );
		assertEquals ( Math.PI / 2, l.theta (), TOLERANCE );
		
		l = new LineSegment ( 0, 0, -1, 1 );
		assertEquals ( Math.PI * 3/4, l.theta (), TOLERANCE );
		
		l = new LineSegment ( 0, 0, -1, 0 );
		assertEquals ( Math.PI, l.theta (), TOLERANCE );
		
		l = new LineSegment ( 0, 0, -1, -1 );
		assertEquals ( -Math.PI * 3/4, l.theta (), TOLERANCE );
		
		l = new LineSegment ( 0, 0, 0, -1 );
		assertEquals ( -Math.PI / 2, l.theta (), TOLERANCE );
		
		l = new LineSegment ( 0, 0, 1, -1 );
		assertEquals ( -Math.PI / 4, l.theta (), TOLERANCE );
	}
	
	@Test
	public void testMidPoint()
	{
		LineSegment l;
		
		l = new LineSegment ( -1, -3, 1, 3 );
		Point m = l.midPoint ();
		assertEquals ( 0, m.x (), TOLERANCE );
		assertEquals ( 0, m.y (), TOLERANCE );
	}
	
	@Test
	public void testAlong()
	{
		LineSegment l = new LineSegment ( 3, 8, 11, 20 );
		
		Point a = l.along ( 0 );
		assertEquals ( l.a (), a );
		
		a = l.along ( 0.25 );
		assertEquals (  5, a.x (), TOLERANCE );
		assertEquals ( 11, a.y (), TOLERANCE );
		
		a = l.along ( 0.5 );
		assertEquals ( l.midPoint (), a );
		
		a = l.along ( 0.75 );
		assertEquals (  9, a.x (), TOLERANCE );
		assertEquals ( 17, a.y (), TOLERANCE );
		
		a = l.along ( 1 );
		assertEquals ( l.b (), a );
		
		a = l.along ( -0.25 );
		assertEquals ( 1, a.x (), TOLERANCE );
		assertEquals ( 5, a.y (), TOLERANCE );
		
		a = l.along ( 1.25 );
		assertEquals ( 13, a.x (), TOLERANCE );
		assertEquals ( 23, a.y (), TOLERANCE );
	}
}

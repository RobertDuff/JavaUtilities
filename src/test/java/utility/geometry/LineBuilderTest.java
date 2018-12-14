package utility.geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class LineBuilderTest
{
	private static final double TOLERANCE = 0.000001;

	@Test
	public void testSlopeYIntercept() 
	{
		Line l;
		
		l = LineBuilder.slopeYIntercept ( -1, 10 );
		assertEquals ( -Math.PI / 4, l.theta(),      TOLERANCE );
		assertEquals ( -1,           l.slope(),      TOLERANCE );
		assertEquals ( 10,           l.xIntercept(), TOLERANCE );
		assertEquals ( 10,           l.yIntercept(), TOLERANCE );
		
		l = LineBuilder.slopeYIntercept ( 0, 10 );
		assertEquals ( 0, l.theta(), TOLERANCE );
		assertEquals ( 0, l.slope(), TOLERANCE );
		assertTrue  ( Double.isNaN ( l.xIntercept() ) );
		assertEquals ( 10, l.yIntercept(), TOLERANCE );
		
		try 
		{
			l = LineBuilder.slopeYIntercept ( Line.VERTICAL_SLOPE, 10 );
		} 
		catch ( IllegalArgumentException e )
		{
			return;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			fail ( "Unexpected Exception" );
		}
		
		fail ( "Unexpected Success" );
	}

	@Test
	public void testVertical()
	{
		Line l = LineBuilder.vertical ( 7 );
		assertEquals ( Line.VERTICAL_THETA, l.theta(), TOLERANCE );
		assertTrue ( Double.isNaN ( l.slope() ) );
		assertEquals ( 7, l.xIntercept(), TOLERANCE );
		assertTrue ( Double.isNaN ( l.yIntercept() ) );
	}

	@Test
	public void testHorizontal()
	{
		Line l = LineBuilder.horizontal ( 7 );
		assertEquals ( Line.HORIZONTAL_THETA, l.theta(), TOLERANCE );
		assertEquals ( Line.HORIZONTAL_SLOPE, l.slope(), TOLERANCE );
		assertTrue ( Double.isNaN ( l.xIntercept() ) );
		assertEquals ( 7, l.yIntercept(), TOLERANCE );
	}

	@Test
	public void testBetweenPointPoint()
	{
		Point a;
		Point b;
		Line l;
		
		a = new Point ( 3, 3 );
		b = new Point ( 6, 9 );
		l = LineBuilder.between ( a, b );
		assertEquals ( Math.atan ( 2 ), l.theta(), TOLERANCE );
		assertEquals ( 2, l.slope(), TOLERANCE );
		assertEquals ( 1.5, l.xIntercept(), TOLERANCE );
		assertEquals ( -3, l.yIntercept(), TOLERANCE );
		
		a = new Point ( 3, 3 );
		b = new Point ( 3, 9 );
		l = LineBuilder.between ( a, b );
		assertEquals ( Line.VERTICAL_THETA, l.theta(), TOLERANCE );
		assertTrue   ( Double.isNaN ( l.slope() ) );
		assertEquals ( 3, l.xIntercept(), TOLERANCE );
		assertTrue ( Double.isNaN ( l.yIntercept() ) );
		
		a = new Point ( 3, 3 );
		b = new Point ( 9, 3 );
		l = LineBuilder.between ( a, b );
		assertEquals ( Line.HORIZONTAL_THETA, l.theta(), TOLERANCE );
		assertEquals ( Line.HORIZONTAL_SLOPE, l.slope(), TOLERANCE );
		assertTrue ( Double.isNaN ( l.xIntercept() ) );
		assertEquals ( 3, l.yIntercept(), TOLERANCE );
		
		try
		{
			l = LineBuilder.between ( a, a );
		}
		catch ( IllegalArgumentException e )
		{
			return;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			fail ( "Unexpected Exception" );
		}
		
		fail ( "Unexpected Success" );
	}

	@Test
	public void testBetweenDoubleDoubleDoubleDouble()
	{
		Line l;
		
		l = LineBuilder.between ( 3, 3, 6, 9 );
		assertEquals ( Math.atan ( 2 ), l.theta(), TOLERANCE );
		assertEquals ( 2, l.slope(), TOLERANCE );
		assertEquals ( 1.5, l.xIntercept(), TOLERANCE );
		assertEquals ( -3, l.yIntercept(), TOLERANCE );
		
		l = LineBuilder.between ( 3, 3, 3, 9 );
		assertEquals ( Line.VERTICAL_THETA, l.theta(), TOLERANCE );
		assertTrue   ( Double.isNaN ( l.slope() ) );
		assertEquals ( 3, l.xIntercept(), TOLERANCE );
		assertTrue ( Double.isNaN ( l.yIntercept() ) );
		
		l = LineBuilder.between ( 3, 3, 9, 3 );
		assertEquals ( Line.HORIZONTAL_THETA, l.theta(), TOLERANCE );
		assertEquals ( Line.HORIZONTAL_SLOPE, l.slope(), TOLERANCE );
		assertTrue ( Double.isNaN ( l.xIntercept() ) );
		assertEquals ( 3, l.yIntercept(), TOLERANCE );
		
		try
		{
			l = LineBuilder.between ( 1, 2, 1, 2 );
		}
		catch ( IllegalArgumentException e )
		{
			return;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			fail ( "Unexpected Exception" );
		}
		
		fail ( "Unexpected Success" );
	}

	@Test
	public void testThetaThruPoint()
	{
		Point p;
		Line l;
		
		p = new Point ( 0, 0 );
		l = LineBuilder.thetaThruPoint ( Line.HORIZONTAL_THETA, p );
		assertEquals ( Line.HORIZONTAL_THETA, l.theta(), TOLERANCE );
		assertEquals ( Line.HORIZONTAL_SLOPE, l.slope(), TOLERANCE );
		assertTrue ( Double.isNaN ( l.xIntercept() ) );
		assertEquals ( 0, l.yIntercept(), TOLERANCE );
		
		l = LineBuilder.thetaThruPoint ( Line.VERTICAL_THETA, p );
		assertEquals ( Line.VERTICAL_THETA, l.theta(), TOLERANCE );
		assertTrue   ( Double.isNaN ( l.slope() ) );
		assertEquals ( 0, l.xIntercept(), TOLERANCE );
		assertTrue ( Double.isNaN ( l.yIntercept() ) );
		
		l = LineBuilder.thetaThruPoint ( Math.PI / 4, p );
		assertEquals ( Math.PI / 4, l.theta(), TOLERANCE );
		assertEquals ( 1, l.slope(), TOLERANCE );
		assertEquals ( 0, l.xIntercept(), TOLERANCE );
		assertEquals ( 0, l.yIntercept(), TOLERANCE );
		
		p = new Point ( 7, 12 );
		
		l = LineBuilder.thetaThruPoint ( Line.HORIZONTAL_THETA, p );
		assertEquals ( Line.HORIZONTAL_THETA, l.theta(), TOLERANCE );
		assertEquals ( Line.HORIZONTAL_SLOPE, l.slope(), TOLERANCE );
		assertTrue ( Double.isNaN ( l.xIntercept() ) );
		assertEquals ( 12, l.yIntercept(), TOLERANCE );
		
		l = LineBuilder.thetaThruPoint ( Line.VERTICAL_THETA, p );
		assertEquals ( Line.VERTICAL_THETA, l.theta(), TOLERANCE );
		assertTrue   ( Double.isNaN ( l.slope() ) );
		assertEquals ( 7, l.xIntercept(), TOLERANCE );
		assertTrue ( Double.isNaN ( l.yIntercept() ) );
		
		l = LineBuilder.thetaThruPoint ( Math.PI / 4, p );
		assertEquals ( Math.PI / 4, l.theta(), TOLERANCE );
		assertEquals ( 1, l.slope(), TOLERANCE );
		assertEquals ( -5, l.xIntercept(), TOLERANCE );
		assertEquals ( 5, l.yIntercept(), TOLERANCE );
	}

	@Test
	public void testSlopeThruPoint()
	{
		Point p;
		Line l;
		
		p = new Point ( 0, 0 );
		l = LineBuilder.slopeThruPoint ( Line.HORIZONTAL_SLOPE, p );
		assertEquals ( Line.HORIZONTAL_THETA, l.theta(), TOLERANCE );
		assertEquals ( Line.HORIZONTAL_SLOPE, l.slope(), TOLERANCE );
		assertTrue ( Double.isNaN ( l.xIntercept() ) );
		assertEquals ( 0, l.yIntercept(), TOLERANCE );
		
		l = LineBuilder.slopeThruPoint ( Line.VERTICAL_SLOPE, p );
		assertEquals ( Line.VERTICAL_THETA, l.theta(), TOLERANCE );
		assertTrue   ( Double.isNaN ( l.slope() ) );
		assertEquals ( 0, l.xIntercept(), TOLERANCE );
		assertTrue ( Double.isNaN ( l.yIntercept() ) );
		
		l = LineBuilder.slopeThruPoint ( 1, p );
		assertEquals ( Math.PI / 4, l.theta(), TOLERANCE );
		assertEquals ( 1, l.slope(), TOLERANCE );
		assertEquals ( 0, l.xIntercept(), TOLERANCE );
		assertEquals ( 0, l.yIntercept(), TOLERANCE );
		
		p = new Point ( 7, 12 );
		
		l = LineBuilder.slopeThruPoint ( Line.HORIZONTAL_SLOPE, p );
		assertEquals ( Line.HORIZONTAL_THETA, l.theta(), TOLERANCE );
		assertEquals ( Line.HORIZONTAL_SLOPE, l.slope(), TOLERANCE );
		assertTrue ( Double.isNaN ( l.xIntercept() ) );
		assertEquals ( 12, l.yIntercept(), TOLERANCE );
		
		l = LineBuilder.slopeThruPoint ( Line.VERTICAL_SLOPE, p );
		assertEquals ( Line.VERTICAL_THETA, l.theta(), TOLERANCE );
		assertTrue   ( Double.isNaN ( l.slope() ) );
		assertEquals ( 7, l.xIntercept(), TOLERANCE );
		assertTrue ( Double.isNaN ( l.yIntercept() ) );
		
		l = LineBuilder.slopeThruPoint ( 1, p );
		assertEquals ( Math.PI / 4, l.theta(), TOLERANCE );
		assertEquals ( 1, l.slope(), TOLERANCE );
		assertEquals ( -5, l.xIntercept(), TOLERANCE );
		assertEquals ( 5, l.yIntercept(), TOLERANCE );
	}

	@Test
	public void testParallelThruPoint()
	{
		Point p;
		Line a;
		Line b;
		
		p = new Point ( 0, 0 );
		a = new Line ( Line.HORIZONTAL_THETA, 13 );
		b = LineBuilder.parallelThruPoint ( a, p );
		assertEquals ( Line.HORIZONTAL_THETA, b.theta(), TOLERANCE );
		assertEquals ( Line.HORIZONTAL_SLOPE, b.slope(), TOLERANCE );
		assertTrue ( Double.isNaN ( b.xIntercept() ) );
		assertEquals ( 0, b.yIntercept(), TOLERANCE );
		
		a = new Line ( Line.VERTICAL_THETA, 13 );
		b = LineBuilder.parallelThruPoint ( a, p );
		assertEquals ( Line.VERTICAL_THETA, b.theta(), TOLERANCE );
		assertTrue   ( Double.isNaN ( b.slope() ) );
		assertEquals ( 0, b.xIntercept(), TOLERANCE );
		assertTrue ( Double.isNaN ( b.yIntercept() ) );
		
		a = new Line ( Line.theta ( 1 ), 13 );
		b = LineBuilder.parallelThruPoint ( a, p );
		assertEquals ( Math.PI / 4, b.theta(), TOLERANCE );
		assertEquals ( 1, b.slope(), TOLERANCE );
		assertEquals ( 0, b.xIntercept(), TOLERANCE );
		assertEquals ( 0, b.yIntercept(), TOLERANCE );
		
		p = new Point ( 7, 12 );
		
		a = new Line ( Line.HORIZONTAL_THETA, 13 );
		b = LineBuilder.parallelThruPoint ( a, p );
		assertEquals ( Line.HORIZONTAL_THETA, b.theta(), TOLERANCE );
		assertEquals ( Line.HORIZONTAL_SLOPE, b.slope(), TOLERANCE );
		assertTrue ( Double.isNaN ( b.xIntercept() ) );
		assertEquals ( 12, b.yIntercept(), TOLERANCE );
		
		a = new Line ( Line.VERTICAL_THETA, 13 );
		b = LineBuilder.parallelThruPoint ( a, p );
		assertEquals ( Line.VERTICAL_THETA, b.theta(), TOLERANCE );
		assertTrue   ( Double.isNaN ( b.slope() ) );
		assertEquals ( 7, b.xIntercept(), TOLERANCE );
		assertTrue ( Double.isNaN ( b.yIntercept() ) );
		
		a = new Line ( Line.theta ( 1 ), 13 );
		b = LineBuilder.parallelThruPoint ( a, p );
		assertEquals ( Math.PI / 4, b.theta(), TOLERANCE );
		assertEquals ( 1, b.slope(), TOLERANCE );
		assertEquals ( -5, b.xIntercept(), TOLERANCE );
		assertEquals ( 5, b.yIntercept(), TOLERANCE );
	}

	@Test
	public void testPerpendicularThruPoint()
	{
		Point p;
		Line a;
		Line b;
		
		p = new Point ( 0, 0 );
		a = new Line ( Line.VERTICAL_THETA, 13 );
		b = LineBuilder.perpendicularThruPoint ( a, p );
		assertEquals ( Line.HORIZONTAL_THETA, b.theta(), TOLERANCE );
		assertEquals ( Line.HORIZONTAL_SLOPE, b.slope(), TOLERANCE );
		assertTrue ( Double.isNaN ( b.xIntercept() ) );
		assertEquals ( 0, b.yIntercept(), TOLERANCE );
		
		a = new Line ( Line.HORIZONTAL_THETA, 13 );
		b = LineBuilder.perpendicularThruPoint ( a, p );
		assertEquals ( Line.VERTICAL_THETA, b.theta(), TOLERANCE );
		assertTrue   ( Double.isNaN ( b.slope() ) );
		assertEquals ( 0, b.xIntercept(), TOLERANCE );
		assertTrue ( Double.isNaN ( b.yIntercept() ) );
		
		a = new Line ( Line.theta ( -1 ), 13 );
		b = LineBuilder.perpendicularThruPoint ( a, p );
		assertEquals ( Math.PI / 4, b.theta(), TOLERANCE );
		assertEquals ( 1, b.slope(), TOLERANCE );
		assertEquals ( 0, b.xIntercept(), TOLERANCE );
		assertEquals ( 0, b.yIntercept(), TOLERANCE );
		
		p = new Point ( 7, 12 );
		
		a = new Line ( Line.VERTICAL_THETA, 13 );
		b = LineBuilder.perpendicularThruPoint ( a, p );
		assertEquals ( Line.HORIZONTAL_THETA, b.theta(), TOLERANCE );
		assertEquals ( Line.HORIZONTAL_SLOPE, b.slope(), TOLERANCE );
		assertTrue ( Double.isNaN ( b.xIntercept() ) );
		assertEquals ( 12, b.yIntercept(), TOLERANCE );
		
		a = new Line ( Line.HORIZONTAL_THETA, 13 );
		b = LineBuilder.perpendicularThruPoint ( a, p );
		assertEquals ( Line.VERTICAL_THETA, b.theta(), TOLERANCE );
		assertTrue   ( Double.isNaN ( b.slope() ) );
		assertEquals ( 7, b.xIntercept(), TOLERANCE );
		assertTrue ( Double.isNaN ( b.yIntercept() ) );
		
		a = new Line ( Line.theta ( -1 ), 13 );
		b = LineBuilder.perpendicularThruPoint ( a, p );
		assertEquals ( Math.PI / 4, b.theta(), TOLERANCE );
		assertEquals ( 1, b.slope(), TOLERANCE );
		assertEquals ( -5, b.xIntercept(), TOLERANCE );
		assertEquals ( 5, b.yIntercept(), TOLERANCE );
	}
}

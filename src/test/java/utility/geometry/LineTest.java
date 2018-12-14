package utility.geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LineTest
{
	private static final double TOLERANCE = 0.000001;
	
	@Test
	public void testSlopeDouble()
	{
		double s;
		
		s = Line.slope ( 0 );
		assertEquals ( 0, s, TOLERANCE );
		
		s = Line.slope ( Line.VERTICAL_THETA );
		assertTrue ( Double.isNaN ( s ) );
		
		s = Line.slope ( Math.PI / 4 );
		assertEquals ( 1, s, TOLERANCE );
	}

	@Test
	public void testThetaDouble() 
	{
		double t;
		
		t = Line.theta ( 0 );
		assertEquals ( 0, t, TOLERANCE );
		
		t = Line.theta ( Line.VERTICAL_SLOPE );
		assertEquals ( Line.VERTICAL_THETA, t, TOLERANCE );
		
		t = Line.theta ( 1 );
		assertEquals ( Math.PI / 4, t, TOLERANCE );
	}

	@Test
	public void testPerpendicularTheta()
	{
		double t;
		
		t = Line.perpendicularTheta ( Line.HORIZONTAL_THETA );
		assertEquals ( Line.VERTICAL_THETA, t, TOLERANCE );
		
		t = Line.perpendicularTheta ( Line.VERTICAL_THETA );
		assertEquals ( Line.HORIZONTAL_THETA, t, TOLERANCE );
		
		t = Line.perpendicularTheta ( Math.PI / 4 );
		assertEquals ( -Math.PI / 4, t, TOLERANCE );
		
		t = Line.perpendicularTheta ( -Math.PI / 4 );
		assertEquals ( Math.PI / 4, t, TOLERANCE );
	}

	@Test
	public void testPerpendicularSlope()
	{
		double s;
		
		s = Line.perpendicularSlope ( Line.HORIZONTAL_SLOPE );
		assertTrue ( Double.isNaN ( s ) );
		
		s = Line.perpendicularSlope ( Line.VERTICAL_SLOPE );
		assertEquals ( Line.HORIZONTAL_SLOPE, s, TOLERANCE );
		
		s = Line.perpendicularSlope ( 5 );
		assertEquals ( -0.2, s, TOLERANCE );
		
		s = Line.perpendicularSlope ( -0.1 );
		assertEquals ( 10, s, TOLERANCE );
	}

	@Test
	public void testIsThetaVertical()
	{
		assertFalse ( Line.isThetaVertical ( 0 ) );
		assertFalse ( Line.isThetaVertical ( Math.PI / 4 ) );
		assertFalse ( Line.isThetaVertical ( Line.HORIZONTAL_SLOPE ) );
		assertTrue  ( Line.isThetaVertical ( Line.VERTICAL_THETA ) );
	}

	@Test
	public void testIsSlopeVertical()
	{
		assertFalse ( Line.isSlopeVertical ( 0 ) );
		assertFalse ( Line.isSlopeVertical ( 10 ) );
		assertFalse ( Line.isSlopeVertical ( 100 ) );
		assertFalse ( Line.isSlopeVertical ( Line.HORIZONTAL_SLOPE ) );
		assertTrue  ( Line.isSlopeVertical ( Line.VERTICAL_SLOPE ) );
	}

	@Test
	public void testIsThetaHorizontal()
	{
		assertTrue  ( Line.isThetaHorizontal ( 0 ) );
		assertFalse ( Line.isThetaHorizontal ( Math.PI / 4 ) );
		assertTrue  ( Line.isThetaHorizontal ( Line.HORIZONTAL_SLOPE ) );
		assertFalse ( Line.isThetaHorizontal ( Line.VERTICAL_THETA ) );
	}

	@Test
	public void testIsSlopeHorizontal()
	{
		assertTrue  ( Line.isSlopeHorizontal ( 0 ) );
		assertFalse ( Line.isSlopeHorizontal ( 10 ) );
		assertFalse ( Line.isSlopeHorizontal ( 100 ) );
		assertTrue  ( Line.isSlopeHorizontal ( Line.HORIZONTAL_SLOPE ) );
		assertFalse ( Line.isSlopeHorizontal ( Line.VERTICAL_SLOPE ) );
	}

	@Test
	public void testLineDoubleDouble()
	{
		Line l;
		
		l = new Line ( 0, 22 );
		assertEquals ( 0, l.theta(), TOLERANCE );
		assertEquals ( 0, l.slope(), TOLERANCE );
		assertTrue ( Double.isNaN ( l.xIntercept() ) );
		assertEquals ( 22, l.yIntercept(), TOLERANCE );
		assertTrue ( l.isHorizontal() );
		assertFalse ( l.isVertical() );
		
		l = new Line ( Line.VERTICAL_THETA, 16 );
		assertEquals ( Line.VERTICAL_THETA, l.theta(), TOLERANCE );
		assertTrue   ( Double.isNaN ( l.slope() ) );
		assertEquals ( 16, l.xIntercept(), TOLERANCE );
		assertTrue   ( Double.isNaN ( l.yIntercept() ) );
		assertFalse  ( l.isHorizontal() );
		assertTrue   ( l.isVertical() );
		
		l = new Line ( -Math.PI / 4, 77 );
		assertEquals ( -Math.PI / 4, l.theta(), TOLERANCE );
		assertEquals ( -1, l.slope(), TOLERANCE );
		assertEquals ( 77, l.xIntercept(), TOLERANCE );
		assertEquals ( 77, l.yIntercept(), TOLERANCE );
		assertFalse  ( l.isHorizontal() );
		assertFalse  ( l.isVertical() );
	}

	@Test
	public void testLineLine()
	{
		Line a = new Line ( Line.theta ( 6 ), 10 );
		Line b = new Line ( a );
		
		assertEquals ( a.theta(), b.theta(), TOLERANCE );
		assertEquals ( a.slope(), b.slope(), TOLERANCE );
		assertEquals ( a.xIntercept(), b.xIntercept(), TOLERANCE );
		assertEquals ( a.yIntercept(), b.yIntercept(), TOLERANCE );
	}
	
	@Test
	public void testIsParallel()
	{
		Line a;
		Line b;
		
		a = new Line ( Line.HORIZONTAL_THETA, 0 );
		b = new Line ( Line.HORIZONTAL_THETA, 4 );
		assertTrue ( a.isParallel ( a ) );
		assertTrue ( a.isParallel ( b ) );
		
		a = new Line ( Line.VERTICAL_THETA, 0 );
		b = new Line ( Line.VERTICAL_THETA, 4 );
		assertTrue ( a.isParallel ( a ) );
		assertTrue ( a.isParallel ( b ) );
		
		a = new Line ( 0.3456545, 45.33544 );
		b = new Line ( 0.3456545, 10 );
		assertTrue ( a.isParallel ( a ) );
		assertTrue ( a.isParallel ( b ) );
		
		b = new Line ( 0.3457, 10 );
		assertFalse ( a.isParallel ( b ) );
	}

	@Test
	public void testIsPerpendicular() 
	{
		Line a;
		Line b;
		
		a = new Line ( Line.HORIZONTAL_THETA, 0 );
		b = new Line ( Line.VERTICAL_THETA, 4 );
		assertTrue ( a.isPerpendicular ( b ) );
		
		a = new Line ( Line.HORIZONTAL_THETA, 0 );
		b = new Line ( Line.VERTICAL_THETA, 4 );
		assertTrue ( a.isPerpendicular ( b ) );
		
		a = new Line ( Line.theta ( 4 ), 45.33544 );
		b = new Line ( Line.theta ( -0.25 ), 10 );
		assertTrue ( a.isPerpendicular ( b ) );
		
		b = new Line ( 0.3457, 10 );
		assertFalse ( a.isPerpendicular ( b ) );
	}

	@Test
	public void testY()
	{
		Line l = new Line ( Line.theta ( 3 ), 5 );
		
		assertEquals ( 5, l.y ( 0 ), TOLERANCE );
		assertEquals ( 8, l.y ( 1 ), TOLERANCE );
		assertEquals ( 2, l.y ( -1 ), TOLERANCE );
	}
}

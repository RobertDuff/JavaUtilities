package utility.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LineTest
{
	private static final double TOLERANCE = 0.000001;
	
	@Test
	public void testSlopeDouble()
	{
		double s;
		
		s = Line.slope ( 0 );
		Assertions.assertEquals ( 0, s, TOLERANCE );
		
		s = Line.slope ( Line.VERTICAL_THETA );
		Assertions.assertTrue ( Double.isNaN ( s ) );
		
		s = Line.slope ( Math.PI / 4 );
		Assertions.assertEquals ( 1, s, TOLERANCE );
	}

	@Test
	public void testThetaDouble() 
	{
		double t;
		
		t = Line.theta ( 0 );
		Assertions.assertEquals ( 0, t, TOLERANCE );
		
		t = Line.theta ( Line.VERTICAL_SLOPE );
		Assertions.assertEquals ( Line.VERTICAL_THETA, t, TOLERANCE );
		
		t = Line.theta ( 1 );
		Assertions.assertEquals ( Math.PI / 4, t, TOLERANCE );
	}

	@Test
	public void testPerpendicularTheta()
	{
		double t;
		
		t = Line.perpendicularTheta ( Line.HORIZONTAL_THETA );
		Assertions.assertEquals ( Line.VERTICAL_THETA, t, TOLERANCE );
		
		t = Line.perpendicularTheta ( Line.VERTICAL_THETA );
		Assertions.assertEquals ( Line.HORIZONTAL_THETA, t, TOLERANCE );
		
		t = Line.perpendicularTheta ( Math.PI / 4 );
		Assertions.assertEquals ( -Math.PI / 4, t, TOLERANCE );
		
		t = Line.perpendicularTheta ( -Math.PI / 4 );
		Assertions.assertEquals ( Math.PI / 4, t, TOLERANCE );
	}

	@Test
	public void testPerpendicularSlope()
	{
		double s;
		
		s = Line.perpendicularSlope ( Line.HORIZONTAL_SLOPE );
		Assertions.assertTrue ( Double.isNaN ( s ) );
		
		s = Line.perpendicularSlope ( Line.VERTICAL_SLOPE );
		Assertions.assertEquals ( Line.HORIZONTAL_SLOPE, s, TOLERANCE );
		
		s = Line.perpendicularSlope ( 5 );
		Assertions.assertEquals ( -0.2, s, TOLERANCE );
		
		s = Line.perpendicularSlope ( -0.1 );
		Assertions.assertEquals ( 10, s, TOLERANCE );
	}

	@Test
	public void testIsThetaVertical()
	{
		Assertions.assertFalse ( Line.isThetaVertical ( 0 ) );
		Assertions.assertFalse ( Line.isThetaVertical ( Math.PI / 4 ) );
		Assertions.assertFalse ( Line.isThetaVertical ( Line.HORIZONTAL_SLOPE ) );
		Assertions.assertTrue  ( Line.isThetaVertical ( Line.VERTICAL_THETA ) );
	}

	@Test
	public void testIsSlopeVertical()
	{
		Assertions.assertFalse ( Line.isSlopeVertical ( 0 ) );
		Assertions.assertFalse ( Line.isSlopeVertical ( 10 ) );
		Assertions.assertFalse ( Line.isSlopeVertical ( 100 ) );
		Assertions.assertFalse ( Line.isSlopeVertical ( Line.HORIZONTAL_SLOPE ) );
		Assertions.assertTrue  ( Line.isSlopeVertical ( Line.VERTICAL_SLOPE ) );
	}

	@Test
	public void testIsThetaHorizontal()
	{
		Assertions.assertTrue  ( Line.isThetaHorizontal ( 0 ) );
		Assertions.assertFalse ( Line.isThetaHorizontal ( Math.PI / 4 ) );
		Assertions.assertTrue  ( Line.isThetaHorizontal ( Line.HORIZONTAL_SLOPE ) );
		Assertions.assertFalse ( Line.isThetaHorizontal ( Line.VERTICAL_THETA ) );
	}

	@Test
	public void testIsSlopeHorizontal()
	{
		Assertions.assertTrue  ( Line.isSlopeHorizontal ( 0 ) );
		Assertions.assertFalse ( Line.isSlopeHorizontal ( 10 ) );
		Assertions.assertFalse ( Line.isSlopeHorizontal ( 100 ) );
		Assertions.assertTrue  ( Line.isSlopeHorizontal ( Line.HORIZONTAL_SLOPE ) );
		Assertions.assertFalse ( Line.isSlopeHorizontal ( Line.VERTICAL_SLOPE ) );
	}

	@Test
	public void testLineDoubleDouble()
	{
		Line l;
		
		l = new Line ( 0, 22 );
		Assertions.assertEquals ( 0, l.theta(), TOLERANCE );
		Assertions.assertEquals ( 0, l.slope(), TOLERANCE );
		Assertions.assertTrue ( Double.isNaN ( l.xIntercept() ) );
		Assertions.assertEquals ( 22, l.yIntercept(), TOLERANCE );
		Assertions.assertTrue ( l.isHorizontal() );
		Assertions.assertFalse ( l.isVertical() );
		
		l = new Line ( Line.VERTICAL_THETA, 16 );
		Assertions.assertEquals ( Line.VERTICAL_THETA, l.theta(), TOLERANCE );
		Assertions.assertTrue   ( Double.isNaN ( l.slope() ) );
		Assertions.assertEquals ( 16, l.xIntercept(), TOLERANCE );
		Assertions.assertTrue   ( Double.isNaN ( l.yIntercept() ) );
		Assertions.assertFalse  ( l.isHorizontal() );
		Assertions.assertTrue   ( l.isVertical() );
		
		l = new Line ( -Math.PI / 4, 77 );
		Assertions.assertEquals ( -Math.PI / 4, l.theta(), TOLERANCE );
		Assertions.assertEquals ( -1, l.slope(), TOLERANCE );
		Assertions.assertEquals ( 77, l.xIntercept(), TOLERANCE );
		Assertions.assertEquals ( 77, l.yIntercept(), TOLERANCE );
		Assertions.assertFalse  ( l.isHorizontal() );
		Assertions.assertFalse  ( l.isVertical() );
	}

	@Test
	public void testLineLine()
	{
		Line a = new Line ( Line.theta ( 6 ), 10 );
		Line b = new Line ( a );
		
		Assertions.assertEquals ( a.theta(), b.theta(), TOLERANCE );
		Assertions.assertEquals ( a.slope(), b.slope(), TOLERANCE );
		Assertions.assertEquals ( a.xIntercept(), b.xIntercept(), TOLERANCE );
		Assertions.assertEquals ( a.yIntercept(), b.yIntercept(), TOLERANCE );
	}
	
	@Test
	public void testIsParallel()
	{
		Line a;
		Line b;
		
		a = new Line ( Line.HORIZONTAL_THETA, 0 );
		b = new Line ( Line.HORIZONTAL_THETA, 4 );
		Assertions.assertTrue ( a.isParallel ( a ) );
		Assertions.assertTrue ( a.isParallel ( b ) );
		
		a = new Line ( Line.VERTICAL_THETA, 0 );
		b = new Line ( Line.VERTICAL_THETA, 4 );
		Assertions.assertTrue ( a.isParallel ( a ) );
		Assertions.assertTrue ( a.isParallel ( b ) );
		
		a = new Line ( 0.3456545, 45.33544 );
		b = new Line ( 0.3456545, 10 );
		Assertions.assertTrue ( a.isParallel ( a ) );
		Assertions.assertTrue ( a.isParallel ( b ) );
		
		b = new Line ( 0.3457, 10 );
		Assertions.assertFalse ( a.isParallel ( b ) );
	}

	@Test
	public void testIsPerpendicular() 
	{
		Line a;
		Line b;
		
		a = new Line ( Line.HORIZONTAL_THETA, 0 );
		b = new Line ( Line.VERTICAL_THETA, 4 );
		Assertions.assertTrue ( a.isPerpendicular ( b ) );
		
		a = new Line ( Line.HORIZONTAL_THETA, 0 );
		b = new Line ( Line.VERTICAL_THETA, 4 );
		Assertions.assertTrue ( a.isPerpendicular ( b ) );
		
		a = new Line ( Line.theta ( 4 ), 45.33544 );
		b = new Line ( Line.theta ( -0.25 ), 10 );
		Assertions.assertTrue ( a.isPerpendicular ( b ) );
		
		b = new Line ( 0.3457, 10 );
		Assertions.assertFalse ( a.isPerpendicular ( b ) );
	}

	@Test
	public void testY()
	{
		Line l = new Line ( Line.theta ( 3 ), 5 );
		
		Assertions.assertEquals ( 5, l.y ( 0 ), TOLERANCE );
		Assertions.assertEquals ( 8, l.y ( 1 ), TOLERANCE );
		Assertions.assertEquals ( 2, l.y ( -1 ), TOLERANCE );
	}
}

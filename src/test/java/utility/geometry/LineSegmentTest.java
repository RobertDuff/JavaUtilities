package utility.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LineSegmentTest
{
	private static final double TOLERANCE = 0.000000001;

	@Test
	public void testLine ()
	{
		LineSegment l;
		
		l = new LineSegment ( 1, 1, 2, 2 );		
		Assertions.assertEquals ( Math.PI / 4, l.line ().theta (), TOLERANCE );
	}

	@Test
	public void testLength ()
	{
		LineSegment l;
		
		l = new LineSegment ( 1, 1, 2, 2 );
		Assertions.assertEquals ( Math.sqrt ( 2 ), l.length (), TOLERANCE );		
	}
	
	@Test
	public void testString()
	{
		LineSegment l = new LineSegment ( 1, 2, 3, 4 );
		
		Assertions.assertEquals ( "LineSegment(Point(1.0,2.0),Point(3.0,4.0))", l.toString () );
	}

	@ParameterizedTest
	@CsvSource ( {
	    "0,0,1,0,0",
        "0,0,1,1,0.78539816339744830961566084581988",
        "0,0,0,1,1.5707963267948966192313216916398",
        "0,0,-1,1,2.3561944901923449288469825374596",
        "0,0,-1,0,3.1415926535897932384626433832795",
        "0,0,-1,-1,-2.3561944901923449288469825374596",
        "0,0,0,-1,-1.5707963267948966192313216916398",
        "0,0,1,-1,-0.78539816339744830961566084581988",
	} )
	public void testTheta ( double x1, double y1, double x2, double y2, double theta )
	{
		LineSegment l;
		
		l = new LineSegment ( x1, y1, x2, y2 );
		Assertions.assertEquals ( theta, l.theta (), TOLERANCE );
	}
	
	@Test
	public void testMidPoint()
	{
		LineSegment l;
		
		l = new LineSegment ( -1, -3, 1, 3 );
		Point m = l.midPoint ();
		
		Assertions.assertAll ( 
		        () -> Assertions.assertEquals ( 0, m.x (), TOLERANCE ),
		        () -> Assertions.assertEquals ( 0, m.y (), TOLERANCE )
		        );
	}
	
	@ParameterizedTest
	@CsvSource ( {
	    "-0.25,1,5",
	    "0,3,8",
	    "0.25,5,11",
	    "0.5,7,14",
	    "0.75,9,17",
	    "1,11,20",
	    "1.25,13,23",
	} )
	public void testAlong ( double ratio, double x, double y )
	{
		LineSegment l = new LineSegment ( 3, 8, 11, 20 );
		
		Point a = l.along ( ratio );
		Assertions.assertEquals ( x, a.x (), TOLERANCE );
		Assertions.assertEquals ( y, a.y (), TOLERANCE );
	}
}

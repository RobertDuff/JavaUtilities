package utility.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LineBuilderTest
{
	private static final double TOLERANCE = 0.000001;

	@ParameterizedTest
	@CsvSource ( {
	    "-1,10,-0.78539816339744830961566084581988,10,false",
	    "0,10,0,,false",
	    ",10,0,0,true",
	} )
	public void testSlopeYIntercept ( Double slope, Double yInt, Double theta, Double xInt, boolean shouldThrow ) 
	{
	    final double s = slope == null ? Double.NaN : slope;
        final double y = yInt  == null ? Double.NaN : yInt;
        final double t = theta == null ? Double.NaN : theta;
        final double x = xInt  == null ? Double.NaN : xInt;
	    
        if ( shouldThrow )
        {
            Assertions.assertThrows ( IllegalArgumentException.class, () -> LineBuilder.slopeYIntercept ( s, y ) );
            return;
        }
        
		final Line l = LineBuilder.slopeYIntercept ( s, y );
		
		Assertions.assertAll ( 
		() -> Assertions.assertEquals ( t, l.theta(),      TOLERANCE ),
		() -> Assertions.assertEquals ( s, l.slope(),      TOLERANCE ),
		() -> Assertions.assertEquals ( x, l.xIntercept(), TOLERANCE ),
		() -> Assertions.assertEquals ( y, l.yIntercept(), TOLERANCE )
		);
	}

	@Test
	public void testVertical()
	{
		Line l = LineBuilder.vertical ( 7 );
		Assertions.assertAll ( 
		        () -> Assertions.assertEquals ( Line.VERTICAL_THETA, l.theta(), TOLERANCE ),
		        () -> Assertions.assertTrue ( Double.isNaN ( l.slope() ) ),
		        () -> Assertions.assertEquals ( 7, l.xIntercept(), TOLERANCE ),
		        () -> Assertions.assertTrue ( Double.isNaN ( l.yIntercept() ) )
		        );
	}

	@Test
	public void testHorizontal()
	{
		Line l = LineBuilder.horizontal ( 7 );
		Assertions.assertAll ( 
		        () -> Assertions.assertEquals ( Line.HORIZONTAL_THETA, l.theta(), TOLERANCE ),
		        () -> Assertions.assertEquals ( Line.HORIZONTAL_SLOPE, l.slope(), TOLERANCE ),
		        () -> Assertions.assertTrue ( Double.isNaN ( l.xIntercept() ) ),
		        () -> Assertions.assertEquals ( 7, l.yIntercept(), TOLERANCE )
		        );
	}

    @ParameterizedTest
    @CsvSource ( {
        "3,3,6,9, 1.1071487177940905030170654601785,2,1.5,-3,false",
        "3,3,3,9, 1.5707963267948966192313216916398,,3,,false",
        "3,3,9,3, 0,0,,3,false",
        "3,3,3,3,0,0,0,0,true",
    } )
    public void testBetweenPointPoint ( double x1, double y1, double x2, double y2, Double theta, Double slope, Double xInt, Double yInt, boolean shouldThrow )
    {
        final double s = slope == null ? Double.NaN : slope;
        final double y = yInt  == null ? Double.NaN : yInt;
        final double t = theta == null ? Double.NaN : theta;
        final double x = xInt  == null ? Double.NaN : xInt;
        
        if ( shouldThrow )
        {
            Assertions.assertThrows ( IllegalArgumentException.class, () -> LineBuilder.between ( new Point ( x1, y1 ), new Point ( x2, y2 ) ) );
            return;
        }
        
        Line l = LineBuilder.between ( new Point ( x1, y1 ), new Point ( x2, y2 ) );
        
        Assertions.assertAll ( 
                () -> Assertions.assertEquals ( t, l.theta(),      TOLERANCE ),
                () -> Assertions.assertEquals ( s, l.slope(),      TOLERANCE ),
                () -> Assertions.assertEquals ( x, l.xIntercept(), TOLERANCE ),
                () -> Assertions.assertEquals ( y, l.yIntercept(), TOLERANCE )
                );
    }
    
    @ParameterizedTest
    @CsvSource ( {
        "3,3,6,9, 1.1071487177940905030170654601785,2,1.5,-3,false",
        "3,3,3,9, 1.5707963267948966192313216916398,,3,,false",
        "3,3,9,3, 0,0,,3,false",
        "3,3,3,3,0,0,0,0,true",
    } )
    public void testBetweenDDDD ( double x1, double y1, double x2, double y2, Double theta, Double slope, Double xInt, Double yInt, boolean shouldThrow )
    {
        final double s = slope == null ? Double.NaN : slope;
        final double y = yInt  == null ? Double.NaN : yInt;
        final double t = theta == null ? Double.NaN : theta;
        final double x = xInt  == null ? Double.NaN : xInt;
        
        if ( shouldThrow )
        {
            Assertions.assertThrows ( IllegalArgumentException.class, () -> LineBuilder.between ( x1, y1, x2, y2 ) );
            return;
        }
        
        Line l = LineBuilder.between ( x1, y1, x2, y2 );
        
        Assertions.assertAll ( 
                () -> Assertions.assertEquals ( t, l.theta(),      TOLERANCE ),
                () -> Assertions.assertEquals ( s, l.slope(),      TOLERANCE ),
                () -> Assertions.assertEquals ( x, l.xIntercept(), TOLERANCE ),
                () -> Assertions.assertEquals ( y, l.yIntercept(), TOLERANCE )
                );
    }

    @ParameterizedTest
    @CsvSource ( {
        "0,0,0,0,,0",
        "0,0,1.5707963267948966192313216916398,,0,",
        "0,0,0.78539816339744830961566084581988,1,0,0",
        "7,12,0,0,,12",
        "7,12,1.5707963267948966192313216916398,,7,",
        "7,12,0.78539816339744830961566084581988,1,-5,5",
    } )
    public void testThetaThruPoint ( double x1, double y1, Double theta, Double slope, Double xInt, Double yInt )
    {
        final double s = slope == null ? Double.NaN : slope;
        final double y = yInt  == null ? Double.NaN : yInt;
        final double t = theta == null ? Double.NaN : theta;
        final double x = xInt  == null ? Double.NaN : xInt;
        
        Line l = LineBuilder.thetaThruPoint ( t, new Point ( x1, y1 ) );
        
        Assertions.assertAll ( 
                () -> Assertions.assertEquals ( t, l.theta(),      TOLERANCE ),
                () -> Assertions.assertEquals ( s, l.slope(),      TOLERANCE ),
                () -> Assertions.assertEquals ( x, l.xIntercept(), TOLERANCE ),
                () -> Assertions.assertEquals ( y, l.yIntercept(), TOLERANCE )
                );
    }

    @ParameterizedTest
    @CsvSource ( {
        "0,0,0,0,,0",
        "0,0,,1.5707963267948966192313216916398,0,",
        "0,0,1,0.78539816339744830961566084581988,0,0",
        "7,12,0,0,,12",
        "7,12,,1.5707963267948966192313216916398,7,",
        "7,12,1,0.78539816339744830961566084581988,-5,5",
    } )
    public void testSlopeThruPoint ( double x1, double y1, Double slope, Double theta, Double xInt, Double yInt )
    {
        final double s = slope == null ? Double.NaN : slope;
        final double y = yInt  == null ? Double.NaN : yInt;
        final double t = theta == null ? Double.NaN : theta;
        final double x = xInt  == null ? Double.NaN : xInt;
        
        Line l = LineBuilder.slopeThruPoint ( s, new Point ( x1, y1 ) );
        
        Assertions.assertAll ( 
                () -> Assertions.assertEquals ( t, l.theta(),      TOLERANCE ),
                () -> Assertions.assertEquals ( s, l.slope(),      TOLERANCE ),
                () -> Assertions.assertEquals ( x, l.xIntercept(), TOLERANCE ),
                () -> Assertions.assertEquals ( y, l.yIntercept(), TOLERANCE )
                );
    }

    @ParameterizedTest
    @CsvSource ( {
        "0,0,0,13,0,,0",
        "0,0,1.5707963267948966192313216916398,13,,0,",
        "0,0,0.78539816339744830961566084581988,13,1,0,0",
        "7,12,0,13,0,,12",
        "7,12,1.5707963267948966192313216916398,13,,7,",
        "7,12,0.78539816339744830961566084581988,13,1,-5,5"
    } )
    public void testParallelThruPoint ( double x1, double y1, Double theta, Double intr, Double slope, Double xInt, Double yInt )
    {
        // intr is the intercept, either X or Y depending on if the line is vertical or not.
        final double i = intr  == null ? Double.NaN : intr;
        final double s = slope == null ? Double.NaN : slope;
        final double y = yInt  == null ? Double.NaN : yInt;
        final double t = theta == null ? Double.NaN : theta;
        final double x = xInt  == null ? Double.NaN : xInt;
        
        Line base = new Line ( t, i );
        Line l = LineBuilder.parallelThruPoint ( base, new Point ( x1, y1 ) );
        
        Assertions.assertAll ( 
                () -> Assertions.assertEquals ( t, l.theta(),      TOLERANCE ),
                () -> Assertions.assertEquals ( s, l.slope(),      TOLERANCE ),
                () -> Assertions.assertEquals ( x, l.xIntercept(), TOLERANCE ),
                () -> Assertions.assertEquals ( y, l.yIntercept(), TOLERANCE )
                );
    }

    @ParameterizedTest
    @CsvSource ( {
        "0,0,1.5707963267948966192313216916398,13,0,0,,0",
        "0,0,0,13,1.5707963267948966192313216916398,,0,",
        "0,0,-0.78539816339744830961566084581988,13,0.78539816339744830961566084581988,1,0,0",
        "7,12,1.5707963267948966192313216916398,13,0,0,,12",
        "7,12,0,13,1.5707963267948966192313216916398,,7,",
        "7,12,-0.78539816339744830961566084581988,13,0.78539816339744830961566084581988,1,-5,5",
    } )
    public void testPerpendicularThruPoint ( double x1, double y1, Double theta, Double intr, Double pTheta, Double slope, Double xInt, Double yInt )
    {
        // intr is the intercept, either X or Y depending on if the line is vertical or not.
        // ptheta is the perpendicular theta
        
        final double i = intr   == null ? Double.NaN : intr;
        final double s = slope  == null ? Double.NaN : slope;
        final double y = yInt   == null ? Double.NaN : yInt;
        final double t = theta  == null ? Double.NaN : theta;
        final double x = xInt   == null ? Double.NaN : xInt;
        final double p = pTheta == null ? Double.NaN : pTheta;
        
        Line base = new Line ( t, i );
        Line l = LineBuilder.perpendicularThruPoint ( base, new Point ( x1, y1 ) );
        
        Assertions.assertAll ( 
                () -> Assertions.assertEquals ( p, l.theta(),      TOLERANCE ),
                () -> Assertions.assertEquals ( s, l.slope(),      TOLERANCE ),
                () -> Assertions.assertEquals ( x, l.xIntercept(), TOLERANCE ),
                () -> Assertions.assertEquals ( y, l.yIntercept(), TOLERANCE )
                );
    }
}

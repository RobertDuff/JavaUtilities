package utility.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CircleTest
{
    private static final double TOLERANCE = 0.000000001;

    @ParameterizedTest
    @CsvSource ( {
        "4,5,8",
        "4,5,-8",
    } )
    public void testCircleDoubleDoubleDouble ( double cx, double cy, double r )
    {
        Circle c = new Circle ( cx, cy, r );

        Assertions.assertAll ( 
                () -> Assertions.assertEquals ( cx,              c.center().x(), TOLERANCE ),
                () -> Assertions.assertEquals ( cy,              c.center().y(), TOLERANCE ),
                () -> Assertions.assertEquals ( Math.abs ( r ),  c.radius(),     TOLERANCE )
                );
    }

    @ParameterizedTest
    @CsvSource ( {
        "4,5,8",
        "8,3,0",
    } )
    public void testCirclePointDouble( double cx, double cy, double r )
    {
        Circle c = new Circle ( new Point ( cx, cy ), r );

        Assertions.assertAll (
                () -> Assertions.assertEquals ( cx, c.center().x(), TOLERANCE  ),
                () -> Assertions.assertEquals ( cy, c.center().y(), TOLERANCE  ),
                () -> Assertions.assertEquals ( r, c.radius(), TOLERANCE  )
                );
    }

    @Test
    public void testCircleCircle() 
    {
        Circle a = new Circle ( 4, 5, 8 );
        Circle c = new Circle ( a );

        Assertions.assertAll (
                () -> Assertions.assertEquals ( 4, c.center().x(), TOLERANCE  ),
                () -> Assertions.assertEquals ( 5, c.center().y(), TOLERANCE  ),
                () -> Assertions.assertEquals ( 8, c.radius(), TOLERANCE  )
                );
    }
}

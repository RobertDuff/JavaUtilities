package utility.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CircleBuilderTest
{
    private static final double TOLERANCE = 0.000000001;

    @ParameterizedTest
    @CsvSource ( {
        "4,4,4,8,4,6,2",
        "4,4,10,4,7,4,3",
        "4,4,10,10,7,7,4.2426406871192851464050661726291",
    } )
    public void testOutscribedCirclePointPoint ( double x1, double y1, double x2, double y2, double cx, double cy, double radius )
    {
        Circle c = CircleBuilder.outscribedCircle ( new Point ( x1, y1 ), new Point ( x2, y2 ) );
        
        Assertions.assertAll ( 
                () -> Assertions.assertEquals ( cx,     c.center().x(), TOLERANCE  ),
                () -> Assertions.assertEquals ( cy,     c.center().y(), TOLERANCE  ),
                () -> Assertions.assertEquals ( radius, c.radius(),     TOLERANCE  )
                );
    }

    @Test
    public void testOutscribedCirclePointPointPoint()
    {
        Circle c = CircleBuilder.outscribedCircle ( new Point ( 1, 6 ), new Point ( 6, 1 ), new Point ( 5, 5 ) );

        Assertions.assertAll ( 
                () -> Assertions.assertEquals ( c.center().x(), c.center().y(), TOLERANCE ),
                () -> Assertions.assertEquals ( 4.006938426723769, c.radius(), TOLERANCE )
                );
    }

    @Test
    public void testInscribedCircle()
    {
        Circle c = CircleBuilder.inscribedCircle ( new Point ( 1, 6 ), new Point ( 6, 1 ), new Point ( 5, 5 ) );

        Assertions.assertAll (
                () -> Assertions.assertEquals ( c.center().x(), c.center().y(), TOLERANCE ),
                () -> Assertions.assertEquals ( 0.9792861994748723, c.radius(), TOLERANCE )
                );
    }
}

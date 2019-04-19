package utility.geometry;


import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import utility.geometry.Turtle.AngleUnit;


public class TurtleTest
{
    private static final double TOLERANCE = 0.000000001;

    private static final double R15  = Math.PI / 12;
    private static final double R30  = Math.PI /  6;
    private static final double R45  = Math.PI /  4;
    private static final double R60  = Math.PI /  3;
    private static final double R75  = R15 * 5;
    private static final double R90  = Math.PI /  2;
    private static final double R105 = R15 * 7;
    private static final double R120 = R60 * 2;
    private static final double R135 = R45 * 3;
    private static final double R150 = R30 * 5;
    //private static final double R165 = R15 * 11;
    private static final double R180 = Math.PI;

    @Test
    public void testTurtle ()
    {
        Turtle t = new Turtle();

        Assertions.assertEquals ( 0, t.point().x(), TOLERANCE );
        Assertions.assertEquals ( 0, t.point().y(), TOLERANCE );

        Assertions.assertEquals ( 0, t.theta(), TOLERANCE );

        Assertions.assertEquals ( Math.PI /2, t.phi(), TOLERANCE );

        Assertions.assertFalse ( t.isMirrored () );
        Assertions.assertEquals ( Turtle.AngleUnit.RADIANS, t.angleUnits() );
    }

    @Test
    public void testSetPhi ()
    {
        Turtle t = new Turtle();		
        Assertions.assertEquals ( R90, t.phi(), TOLERANCE );

        t.setAngleUnits ( AngleUnit.DEGREES );
        Assertions.assertEquals ( 90, t.phi(), TOLERANCE );

        t.setAngleUnits ( AngleUnit.RADIANS );
        t.setPhi ( R60 );
        Assertions.assertEquals ( R60, t.phi(), TOLERANCE );

        t.setAngleUnits ( AngleUnit.DEGREES );
        Assertions.assertEquals ( 60, t.phi(), TOLERANCE );

        t.setPhi ( -45 );
        Assertions.assertEquals ( -45, t.phi(), TOLERANCE );

        t.setAngleUnits ( AngleUnit.RADIANS );
        Assertions.assertEquals ( -R45, t.phi(), TOLERANCE );		
    }

    @Test
    public void testInvert ()
    {
        Turtle t = new Turtle();

        Assertions.assertFalse ( t.isMirrored () );

        t.invert ();

        Assertions.assertTrue ( t.isMirrored () );
    }

    @Test
    public void testTurnTo ()
    {
        Turtle t = new Turtle();
        Assertions.assertEquals ( 0, t.theta(), TOLERANCE );

        t.turnTo ( R60 );
        Assertions.assertEquals ( R60, t.theta(), TOLERANCE );

        t.setAngleUnits ( AngleUnit.DEGREES );
        Assertions.assertEquals ( 60, t.theta(), TOLERANCE );

        t.turnTo ( 150 );
        Assertions.assertEquals ( 150, t.theta(), TOLERANCE );

        t.setAngleUnits ( AngleUnit.RADIANS );
        Assertions.assertEquals ( R150, t.theta(), TOLERANCE );
    }

    @Test
    public void testTurnBy ()
    {
        Turtle t = new Turtle();
        t.turnTo ( R45 );
        Assertions.assertEquals ( R45, t.theta(), TOLERANCE );

        t.turnBy ( R90 );
        Assertions.assertEquals ( 3 * R45, t.theta(), TOLERANCE );

        t.setAngleUnits ( AngleUnit.DEGREES );
        Assertions.assertEquals ( 135, t.theta(), TOLERANCE );

        t.turnBy ( -15 );
        Assertions.assertEquals ( 120, t.theta(), TOLERANCE );

        t.setAngleUnits ( AngleUnit.RADIANS );
        Assertions.assertEquals ( R120, t.theta(), TOLERANCE );
    }

    @Test
    public void testTurnDouble ()
    {
        Turtle t = new Turtle();

        t.setPhi ( R30 );
        t.turn ( 4 );
        Assertions.assertEquals ( R120, t.theta(), TOLERANCE );

        t.turn ( -1.5 );
        Assertions.assertEquals ( R75, t.theta(), TOLERANCE );

        t.invert ();
        t.turn ( 0.5 );
        Assertions.assertEquals ( R60, t.theta(), TOLERANCE );		

        t.turn ( -3 );
        Assertions.assertEquals ( R150, t.theta(), TOLERANCE );		
    }

    @Test
    public void testTurn ()
    {
        Turtle t = new Turtle();

        t.turn ();
        Assertions.assertEquals ( R90, t.theta(), TOLERANCE );

        t.turn ();
        Assertions.assertEquals ( R180, t.theta(), TOLERANCE );

        t.setPhi ( -R60 );

        t.turn();
        Assertions.assertEquals ( R120, t.theta(), TOLERANCE );

        t.setAngleUnits ( AngleUnit.DEGREES );
        Assertions.assertEquals ( 120, t.theta(), TOLERANCE );

        t.turn ();
        Assertions.assertEquals ( 60, t.theta(), TOLERANCE );

        t.setPhi ( 45 );
        t.turn ();
        Assertions.assertEquals ( 105, t.theta(), TOLERANCE );

        t.setAngleUnits ( AngleUnit.RADIANS );
        Assertions.assertEquals ( R105, t.theta(), TOLERANCE );

        t.invert ();
        t.turn ();
        Assertions.assertEquals ( R60, t.theta(), TOLERANCE );
    }

    @Test
    public void testAimPoint ()
    {
        Turtle t = new Turtle();

        t.aim ( new Point ( -4, 4 ) );
        Assertions.assertEquals ( R135, t.theta(), TOLERANCE );

        t.aim ( new Point ( -6, -3 ) );
        Assertions.assertEquals ( -2.677945044588987, t.theta(), TOLERANCE );
    }

    @Test
    public void testAimDoubleDouble ()
    {
        Turtle t = new Turtle();

        t.aim ( 4, 4 );
        Assertions.assertEquals ( R45, t.theta(), TOLERANCE );

        t.aim ( 6, -3 );
        Assertions.assertEquals ( -0.4636476090008061, t.theta(), TOLERANCE );
    }

    @Test
    public void testSetRadius ()
    {
        Turtle t = new Turtle();

        t.setRadius ( 4.6 );
        Assertions.assertEquals ( 4.6, t.radius (), TOLERANCE );
    }

    @Test
    public void testSetRadiusToPoint ()
    {
        Turtle t = new Turtle();

        Point p = new Point ( 10, 10 );
        t.setRadiusTo ( p );
        Assertions.assertEquals ( 10 * Math.sqrt ( 2 ), t.radius (), TOLERANCE );	

        p = new Point ( -4, -3 );
        t.setRadiusTo ( p );
        Assertions.assertEquals ( 5, t.radius (), TOLERANCE );			
    }

    @Test
    public void testSetRadiusToDoubleDouble ()
    {
        Turtle t = new Turtle();

        t.setRadiusTo ( -6, 6 );
        Assertions.assertEquals ( 6 * Math.sqrt ( 2 ), t.radius (), TOLERANCE );	

        t.setRadiusTo ( 5, -12 );
        Assertions.assertEquals ( 13, t.radius (), TOLERANCE );			
    }

    @Test
    public void testSetRadiusFromToLineSegment ()
    {
        Turtle t = new Turtle();
        Point a = new Point ( -17, 134 );
        Point b = PointBuilder.offset ( a, 40, 9 );

        t.setRadiusFromTo ( a, b );
        Assertions.assertEquals ( 41, t.radius (), TOLERANCE );			
    }

    @Test
    public void testSetRadiusFromToPointPoint ()
    {
        Turtle t = new Turtle();
        Point a = new Point ( 3, 3 );
        Point b = PointBuilder.offset ( a, -7, -24 );

        t.setRadiusFromTo ( a, b );
        Assertions.assertEquals ( 25, t.radius (), TOLERANCE );			
    }

    @Test
    public void testSetRadiusFromToDoubleDoubleDoubleDouble ()
    {
        Turtle t = new Turtle();

        t.setRadiusFromTo ( 1, -1, -7, 14 );
        Assertions.assertEquals ( 17, t.radius (), TOLERANCE );			
    }

    @Test
    public void testScaleRadius ()
    {
        Turtle t = new Turtle();

        t.setRadius ( 2 );
        t.scaleRadius ( 1.5 );
        Assertions.assertEquals ( 3, t.radius (), TOLERANCE );					
    }

    @Test
    public void testMoveToDoubleDouble ()
    {
        Turtle t = new Turtle();

        t.moveTo (  18, 45 );
        LineSegment l = t.moveTo ( -34, 77 );

        Assertions.assertEquals ( -34, t.point().x(), TOLERANCE );
        Assertions.assertEquals (  77, t.point().y(), TOLERANCE );

        Assertions.assertEquals ( 18, l.a ().x (), TOLERANCE );
        Assertions.assertEquals ( 45, l.a ().y (), TOLERANCE );

        Assertions.assertEquals ( -34, l.b ().x (), TOLERANCE );
        Assertions.assertEquals (  77, l.b ().y (), TOLERANCE );
    }

    @Test
    public void testMoveToPoint ()
    {
        Turtle t = new Turtle();
        Point a = new Point ( 234, 473 );
        Point b = new Point ( 100, 150 );

        t.moveTo ( a );
        LineSegment l = t.moveTo ( b );

        Assertions.assertEquals ( b.x (), t.point().x(), TOLERANCE );
        Assertions.assertEquals ( b.y (), t.point().y(), TOLERANCE );

        Assertions.assertEquals ( a, l.a () );
        Assertions.assertEquals ( b, l.b () );
    }

    @Test
    public void testMoveDoubleDouble ()
    {
        Turtle t = new Turtle();

        t.move ( R45, 4 );
        Assertions.assertEquals ( Math.sqrt ( 8 ), t.point().x(), TOLERANCE );
        Assertions.assertEquals ( Math.sqrt ( 8 ), t.point().y(), TOLERANCE );
        Assertions.assertEquals ( 0, t.theta(), TOLERANCE );

        LineSegment l = t.move ( R180, 5 );
        Assertions.assertEquals ( Math.sqrt ( 8 ) - 5, t.point().x(), TOLERANCE );
        Assertions.assertEquals ( Math.sqrt ( 8 ), t.point().y(), TOLERANCE );
        Assertions.assertEquals ( 0, t.theta(), TOLERANCE );

        Assertions.assertEquals ( Math.sqrt ( 8 ), l.a().x (), TOLERANCE );
        Assertions.assertEquals ( Math.sqrt ( 8 ), l.b ().y(), TOLERANCE );

        Assertions.assertEquals ( Math.sqrt ( 8 ) - 5, l.b ().x(), TOLERANCE );
        Assertions.assertEquals ( Math.sqrt ( 8 ), l.b ().y(), TOLERANCE );
    }

    @Test
    public void testMoveDir ()
    {
        Turtle t = new Turtle();

        t.moveDir ( R180 );
        LineSegment l = t.moveDir ( -R90 );

        Assertions.assertEquals ( -1, t.point().x(), TOLERANCE );
        Assertions.assertEquals ( -1, t.point().y(), TOLERANCE );

        Assertions.assertEquals ( -1, l.a ().x(), TOLERANCE );
        Assertions.assertEquals (  0, l.a ().y(), TOLERANCE );

        Assertions.assertEquals ( -1, l.b ().x(), TOLERANCE );
        Assertions.assertEquals ( -1, l.b ().y(), TOLERANCE );
    }

    @Test
    public void testMoveTowardRadius()
    {
        Turtle t = new Turtle();

        t.setRadius ( 10 );
        t.moveToward ( new Point ( 4, 3 ) );
        LineSegment l = t.moveToward ( 11, 2 );

        Assertions.assertEquals ( 14, t.point().x(), TOLERANCE );
        Assertions.assertEquals ( -2, t.point().y(), TOLERANCE );

        Assertions.assertEquals (  8, l.a ().x(), TOLERANCE );
        Assertions.assertEquals (  6, l.a ().y(), TOLERANCE );

        Assertions.assertEquals ( 14, l.b ().x(), TOLERANCE );
        Assertions.assertEquals ( -2, l.b ().y(), TOLERANCE );
    }

    @Test
    public void testMoveTowardDistance()
    {
        Turtle t = new Turtle();

        t.moveToward ( new Point ( -8, 15 ), 34 );
        LineSegment l = t.moveToward ( 8, 23, 50 );

        Assertions.assertEquals ( 32, t.point().x(), TOLERANCE );
        Assertions.assertEquals ( 16, t.point().y(), TOLERANCE );

        Assertions.assertEquals ( -16, l.a ().x(), TOLERANCE );
        Assertions.assertEquals (  30, l.a ().y(), TOLERANCE );

        Assertions.assertEquals ( 32, l.b ().x(), TOLERANCE );
        Assertions.assertEquals ( 16, l.b ().y(), TOLERANCE );
    }

    @Test
    public void testMoveDouble ()
    {
        Turtle t = new Turtle();

        t.aim ( 3, 4 );
        t.move ( 5 );
        LineSegment l = t.move ( 10 );

        Assertions.assertEquals (  9, t.point().x(), TOLERANCE );
        Assertions.assertEquals ( 12, t.point().y(), TOLERANCE );

        Assertions.assertEquals ( 3, l.a ().x(), TOLERANCE );
        Assertions.assertEquals ( 4, l.a ().y(), TOLERANCE );

        Assertions.assertEquals (  9, l.b ().x(), TOLERANCE );
        Assertions.assertEquals ( 12, l.b ().y(), TOLERANCE );
    }

    @Test
    public void testMove ()
    {
        Turtle t = new Turtle();

        t.turnTo ( R135 );
        t.setRadius ( 16 * Math.sqrt ( 2 ) );
        t.move ();

        t.setRadius ( 6 * Math.sqrt ( 2 ) );
        LineSegment l = t.move ();

        Assertions.assertEquals ( -22, t.point().x(), TOLERANCE );
        Assertions.assertEquals (  22, t.point().y(), TOLERANCE );

        Assertions.assertEquals ( -16, l.a ().x(), TOLERANCE );
        Assertions.assertEquals (  16, l.a ().y(), TOLERANCE );

        Assertions.assertEquals ( -22, l.b ().x(), TOLERANCE );
        Assertions.assertEquals (  22, l.b ().y(), TOLERANCE );
    }

    @Test
    public void testStack ()
    {
        Turtle t = new Turtle();

        Assertions.assertThrows ( NoSuchElementException.class, () -> t.pop () );

        t.moveTo ( 10,  -32 );
        t.turnTo ( R75 );
        t.setRadius ( 3 );
        t.setPhi ( R45 );

        t.push();

        t.moveTo ( 77, 66 );
        t.turnTo ( R180 );
        t.setRadius ( 8 );
        t.setPhi ( R105 );

        LineSegment l = t.pop ();

        Assertions.assertEquals (  10, t.point().x(), TOLERANCE );
        Assertions.assertEquals ( -32, t.point().y(), TOLERANCE );

        Assertions.assertEquals ( R75, t.theta(), TOLERANCE );

        Assertions.assertEquals ( 3, t.radius (), TOLERANCE );

        Assertions.assertEquals ( R45,  t.phi (), TOLERANCE );

        Assertions.assertEquals ( 77, l.a ().x (), TOLERANCE );
        Assertions.assertEquals ( 66, l.a ().y (), TOLERANCE );

        Assertions.assertEquals (  10, l.b ().x (), TOLERANCE );
        Assertions.assertEquals ( -32, l.b ().y (), TOLERANCE );
    }

    @Test
    public void testMark ()
    {
        Turtle t = new Turtle();

        Assertions.assertThrows ( NoSuchElementException.class, () ->  t.jump ( "Fred" ) );

        t.moveTo ( 10,  -32 );
        t.turnTo ( R75 );
        t.setRadius ( 3 );
        t.setPhi ( R45 );

        t.mark ( "A" );

        t.moveTo ( 77, 66 );
        t.turnTo ( R180 );
        t.setRadius ( 8 );
        t.setPhi ( R105 );

        t.mark ( "B" );

        LineSegment l = t.jump ( "A" );

        Assertions.assertEquals (  10, t.point().x(), TOLERANCE );
        Assertions.assertEquals ( -32, t.point().y(), TOLERANCE );

        Assertions.assertEquals ( R75, t.theta(), TOLERANCE );

        Assertions.assertEquals ( 3, t.radius (), TOLERANCE );

        Assertions.assertEquals ( R45,  t.phi (), TOLERANCE );

        Assertions.assertEquals ( 77, l.a ().x (), TOLERANCE );
        Assertions.assertEquals ( 66, l.a ().y (), TOLERANCE );

        Assertions.assertEquals (  10, l.b ().x (), TOLERANCE );
        Assertions.assertEquals ( -32, l.b ().y (), TOLERANCE );

        l = t.jump ( "B" );

        Assertions.assertEquals ( 77, t.point().x(), TOLERANCE );
        Assertions.assertEquals ( 66, t.point().y(), TOLERANCE );

        Assertions.assertEquals ( R180, t.theta(), TOLERANCE );

        Assertions.assertEquals ( 8, t.radius (), TOLERANCE );

        Assertions.assertEquals ( R105,  t.phi (), TOLERANCE );

        Assertions.assertEquals ( 10, l.a ().x (), TOLERANCE );
        Assertions.assertEquals ( -32, l.a ().y (), TOLERANCE );

        Assertions.assertEquals ( 77, l.b ().x (), TOLERANCE );
        Assertions.assertEquals ( 66, l.b ().y (), TOLERANCE );
    }
}

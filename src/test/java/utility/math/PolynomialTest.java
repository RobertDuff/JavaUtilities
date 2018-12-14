package utility.math;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class PolynomialTest
{
	@Test
	public void testConstants()
	{
		assertArrayEquals ( new int[] {   }, Polynomial.ZERO.coefficients () );
		assertArrayEquals ( new int[] { 1 }, Polynomial.ONE.coefficients () );
	}
	
	@Test
	public void testConstructor()
	{
		assertArrayEquals ( new int[] {  }, new Polynomial().coefficients () );
		assertArrayEquals ( new int[] {  }, new Polynomial( 0 ).coefficients () );
		assertArrayEquals ( new int[] {  }, new Polynomial( 0, 0, 0 ).coefficients () );
		
		assertArrayEquals ( new int[] { 1 }, new Polynomial( 1 ).coefficients () );
		assertArrayEquals ( new int[] { 1 }, new Polynomial( 1, 0 ).coefficients () );
		assertArrayEquals ( new int[] { 1 }, new Polynomial( 1, 0, 0, 0 ).coefficients () );
		
		assertArrayEquals ( new int[] { 2, 3, 4 }, new Polynomial( 2, 3, 4 ).coefficients () );
		assertArrayEquals ( new int[] { 2, 3, 4 }, new Polynomial( 2, 3, 4, 0 ).coefficients () );
		assertArrayEquals ( new int[] { 2, 3, 4 }, new Polynomial( 2, 3, 4, 0, 0 ).coefficients () );
		
		assertArrayEquals ( new int[] { 0, 0, 1 }, new Polynomial( 0, 0, 1 ).coefficients () );
		assertArrayEquals ( new int[] { 0, 0, 1 }, new Polynomial( 0, 0, 1, 0 ).coefficients () );
		assertArrayEquals ( new int[] { 0, 0, 1 }, new Polynomial( 0, 0, 1, 0, 0, 0 ).coefficients () );
	}
	
	@Test
	public void testAddition ()
	{
		assertArrayEquals ( new int[] { 5, 6, 2 }, new Polynomial (   5, 6,  2 ).add (     Polynomial.ZERO            ).coefficients () );
		assertArrayEquals ( new int[] { 4, 4, 5 }, new Polynomial (   5, 6,  2 ).add ( new Polynomial ( -1, -2,   3 ) ).coefficients () );
		assertArrayEquals ( new int[] { 1, 1    }, new Polynomial (   0, 1     ).add ( new Polynomial (  1,  0      ) ).coefficients () );
		assertArrayEquals ( new int[] { 7       }, new Polynomial (   3, 8, -6 ).add ( new Polynomial (  4, -8,   6 ) ).coefficients () );
		assertArrayEquals ( new int[] { 0, 5    }, new Polynomial ( -99, 0, 45 ).add ( new Polynomial ( 99,  5, -45 ) ).coefficients () );
	}
	
	@Test
	public void testSubtract ()
	{
		assertArrayEquals ( new int[] {  5, 6,  2 }, new Polynomial ( 5, 6, 2 ).subtract (     Polynomial.ZERO          ).coefficients () );
		assertArrayEquals ( new int[] {  6, 8, -1 }, new Polynomial ( 5, 6, 2 ).subtract ( new Polynomial ( -1, -2, 3 ) ).coefficients () );
		assertArrayEquals ( new int[] { -1, 1     }, new Polynomial ( 0, 1    ).subtract ( new Polynomial (  1,  0    ) ).coefficients () );
		assertArrayEquals ( new int[] {           }, new Polynomial ( 1, 2, 3 ).subtract ( new Polynomial (  1,  2, 3 ) ).coefficients () );
	}
	
	@Test
	public void testMultiply ()
	{
		assertArrayEquals ( new int[] {  5, 6,  2         }, new Polynomial ( 5, 6, 2 ).multiply (     Polynomial.ONE           ).coefficients () );
		assertArrayEquals ( new int[] {                   }, new Polynomial ( 5, 6, 2 ).multiply (     Polynomial.ZERO          ).coefficients () );
		assertArrayEquals ( new int[] { -5, -16, 1, 14, 6 }, new Polynomial ( 5, 6, 2 ).multiply ( new Polynomial ( -1, -2, 3 ) ).coefficients () );
	}
}

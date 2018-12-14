package utility.math;

import java.util.Arrays;

/**
 * Represents a Polynomial (e.g. 3x<sup>2</sup> + 13x + 45), when the index of the
 * coefficient array is equivalent to the exponent of the x term, and the value
 * of that index is the coefficient.
 * 
 * Example: 5x<sup>3</sup> + 7x + 8 is represented as {@code [8, 7, 0, 5]}
 * 
 * @author Rob Duff
 *
 */
public class Polynomial
{
	public static final Polynomial ZERO = new Polynomial();
	public static final Polynomial ONE  = new Polynomial ( 1 );
	
	private int[] coefficients;
	
	/**
	 * Construct a new Polynomial.
	 * @param coefficients The array of coefficients for each x term (Example: 5x<sup>3</sup> + 7x + 8 is represented as {@code [8, 7, 0, 5]})
	 */
	public Polynomial ( int... coefficients )
	{
		int i = coefficients.length-1;
		
		while ( i >= 0 )
		{
			if ( coefficients[ i ] != 0 )
				break;
			
			i--;
		}
		
		this.coefficients = Arrays.copyOf ( coefficients, i+1 );
	}
	
	/**
	 * @return The {@code Polynomial}'s coefficient array.
	 */
	public int[] coefficients()
	{
		return coefficients;
	}
	
	/**
	 * Calculates the Sum of this {@code Polynomial} and the argument {@code Polynomial}.
	 * @param poly The {@code Polynomial} addend.
	 * @return The sum of the two {@code Polynomial}'s.
	 */
	public Polynomial add ( Polynomial poly )
	{
		int[] sum = Arrays.copyOf ( coefficients, Math.max ( coefficients.length, poly.coefficients ().length ) );
		
		for ( int i=0; i<poly.coefficients ().length; i++ )
			sum[ i ] += poly.coefficients ()[ i ];
		
		return new Polynomial ( sum );
	}
	
	/**
	 * Calculates the Difference between this {@code Polynomial} (the minuend) and the argument {@code Polynomial} (the subtrahend). 
	 * @param poly The {@code Polynomial} subtrahend.
	 * @return The difference between the two {@code Polynomial}'s.
	 */
	public Polynomial subtract ( Polynomial poly )
	{
		int[] difference = Arrays.copyOf ( coefficients, Math.max ( coefficients.length, poly.coefficients ().length ) );
		
		
		for ( int i=0; i<poly.coefficients ().length; i++ )
			difference[ i ] -= poly.coefficients ()[ i ];
		
		return new Polynomial ( difference );
	}
	
	/**
	 * Calculates the product of this {@code Polynomial} and the argument {@code Polynomial}.
	 * @param poly  The {@code Polynomial} multiplier.
	 * @return The product of the two {@code Polynomial}'s.
	 */
	public Polynomial multiply ( Polynomial poly )
	{
		int[] product = new int[ coefficients.length + poly.coefficients ().length + 1 ];
		Arrays.fill ( product, 0 );
		
		for ( int a=0; a<coefficients.length; a++ )
			for ( int b=0; b<poly.coefficients ().length; b++ )
				product [ a+b ] += coefficients[ a ] * poly.coefficients ()[ b ];
		
		return new Polynomial ( product );
	}
}

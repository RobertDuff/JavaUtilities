package utility.math;


public class Combinatorial 
{
	private static long[] factorials = { 
		      1L, 
		      1L, 
		      2L, 
		      6L, 
		     24L, 
		    120L, 
		    720L, 
		   5040L, 
		  40320L, 
		 362880L, 
		3628800L
	};
	
	public static long factorial ( long n )
	{
		if ( n < factorials.length )
			return factorials[ (int) n ];
		
		return n * factorial ( n-1 );
	}

	public static long combinations ( long n, long m )
	{
		return permutations ( n, m ) / factorial ( m );
	}

	public static long permutations ( long n, long m )
	{
		return factorial ( n ) / factorial ( n - m );
	}

	public static long arithmeticSum ( long n )
	{
		return arithmeticSum ( 1L, n );
	}
	
	public static long arithmeticSum ( long a, long b )
	{
		return ( ( b - a + 1 ) * ( b + a ) ) / 2L;
	}
}

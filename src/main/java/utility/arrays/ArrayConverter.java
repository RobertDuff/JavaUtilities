package utility.arrays;

import java.text.Format;
import java.text.NumberFormat;

/**
 * Convert Arrays of one type to Arrays of various other types.
 * 
 * @author Rob Duff
 */

public class ArrayConverter
{
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>Boolean[]</code> Array.
	 * @return A <code>boolean[]</code> Array.
	 */
	public static boolean[] booleanArray ( Boolean[] source )
	{
		if ( source == null ) return null;
		
		boolean[] dest = new boolean[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>boolean[]</code> Array.
	 * @return A <code>Boolean[]</code> Array.
	 */
	public static Boolean[] booleanArray ( boolean[] source )
	{
		if ( source == null ) return null;

		Boolean[] dest = new Boolean[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ];
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * A <code>byte</code> value of Zero is considered <code>false</code>.  Any other value is considered <code>true</code>.
	 * 
	 * @param source A <code>byte[]</code> Array.
	 * @return A <code>boolean[]</code> Array.
	 */
	public static boolean[] booleanArray ( byte[] source )
	{
		if ( source == null ) return null;

		boolean[] dest = new boolean[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ] != 0;
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * A <code>char</code> value of Zero is considered <code>false</code>.  Any other value is considered <code>true</code>.
	 * 
	 * @param source A <code>char[]</code> Array.
	 * @return A <code>boolean[]</code> Array.
	 */
	public static boolean[] booleanArray ( char[] source )
	{
		if ( source == null ) return null;

		boolean[] dest = new boolean[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ] != 0;
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * A <code>short</code> value of Zero is considered <code>false</code>.  Any other value is considered <code>true</code>.
	 * 
	 * @param source A <code>short[]</code> Array.
	 * @return A <code>boolean[]</code> Array.
	 */
	public static boolean[] booleanArray ( short[] source )
	{
		if ( source == null ) return null;

		boolean[] dest = new boolean[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ] != 0;
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * A <code>int</code> value of Zero is considered <code>false</code>.  Any other value is considered <code>true</code>.
	 * 
	 * @param source A <code>int[]</code> Array.
	 * @return A <code>boolean[]</code> Array.
	 */
	public static boolean[] booleanArray ( int[] source )
	{
		if ( source == null ) return null;

		boolean[] dest = new boolean[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ] != 0;
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * A <code>long</code> value of Zero is considered <code>false</code>.  Any other value is considered <code>true</code>.
	 * 
	 * @param source A <code>long[]</code> Array.
	 * @return A <code>boolean[]</code> Array.
	 */
	public static boolean[] booleanArray ( long[] source )
	{
		if ( source == null ) return null;

		boolean[] dest = new boolean[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ] != 0;
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * A <code>float</code> value of Zero is considered <code>false</code>.  Any other value is considered <code>true</code>.
	 * 
	 * @param source A <code>float[]</code> Array.
	 * @return A <code>boolean[]</code> Array.
	 */
	public static boolean[] booleanArray ( float[] source )
	{
		if ( source == null ) return null;

		boolean[] dest = new boolean[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ] != 0;
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * A <code>double</code> value of Zero is considered <code>false</code>.  Any other value is considered <code>true</code>.
	 * 
	 * @param source A <code>double[]</code> Array.
	 * @return A <code>boolean[]</code> Array.
	 */
	public static boolean[] booleanArray ( double[] source )
	{
		if ( source == null ) return null;

		boolean[] dest = new boolean[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ] != 0;
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * A <code>String</code> value of "true" (case insensitive) is considered <code>true</code>.  Any other value, including null, is considered <code>false</code>.
	 * 
	 * @param source A <code>String[]</code> Array.
	 * @return A <code>boolean[]</code> Array.
	 */
	public static boolean[] booleanArray ( String[] source )
	{
		if ( source == null ) return null;

		boolean[] dest = new boolean[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = Boolean.valueOf ( source[ i ] );
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * A <code>boolean</code> value of false is converted to 0, and a value of true is converted to 1.
	 * 
	 * @param source A <code>boolean[]</code> Array.
	 * @return A <code>byte[]</code> Array.
	 */
	public static byte[] byteArray ( boolean[] source )
	{
		if ( source == null ) return null;

		byte[] dest = new byte[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( byte ) ( source[ i ]? 1 : 0 );
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>Byte[]</code> Array.
	 * @return A <code>byte[]</code> Array.
	 */
	public static byte[] byteArray ( Byte[] source )
	{
		if ( source == null ) return null;
	
		byte[] dest = new byte[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>byte[]</code> Array.
	 * @return A <code>Byte[]</code> Array.
	 */
	public static Byte[] byteArray ( byte[] source )
	{
		if ( source == null ) return null;
	
		Byte[] dest = new Byte[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>char[]</code> Array.
	 * @return A <code>byte[]</code> Array.
	 */
	public static byte[] byteArray ( char[] source )
	{
		if ( source == null ) return null;

		byte[] dest = new byte[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( byte ) source[ i ];
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>short[]</code> Array.
	 * @return A <code>byte[]</code> Array.
	 */
	public static byte[] byteArray ( short[] source )
	{
		if ( source == null ) return null;

		byte[] dest = new byte[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( byte ) source[ i ];
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>int[]</code> Array.
	 * @return A <code>byte[]</code> Array.
	 */
	public static byte[] byteArray ( int[] source )
	{
		if ( source == null ) return null;

		byte[] dest = new byte[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( byte ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>long[]</code> Array.
	 * @return A <code>byte[]</code> Array.
	 */
	public static byte[] byteArray ( long[] source )
	{
		if ( source == null ) return null;

		byte[] dest = new byte[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( byte ) source[ i ];
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>float[]</code> Array.
	 * @return A <code>byte[]</code> Array.
	 */
	public static byte[] byteArray ( float[] source )
	{
		if ( source == null ) return null;

		byte[] dest = new byte[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( byte ) source[ i ];
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>double[]</code> Array.
	 * @return A <code>byte[]</code> Array.
	 */
	public static byte[] byteArray ( double[] source )
	{
		if ( source == null ) return null;

		byte[] dest = new byte[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( byte ) source[ i ];
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>String[]</code> Array.
	 * @return A <code>byte[]</code> Array.
	 */
	public static byte[] byteArray ( String[] source )
	{
		if ( source == null ) return null;

		byte[] dest = new byte[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = Byte.valueOf ( source[ i ] );
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>Character[]</code> Array.
	 * @return A <code>char[]</code> Array.
	 */
	public static char[] charArray ( Character[] source )
	{
		if ( source == null ) return null;
	
		char[] dest = new char[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>char[]</code> Array.
	 * @return A <code>Character[]</code> Array.
	 */
	public static Character[] charArray ( char[] source )
	{
		if ( source == null ) return null;
	
		Character[] dest = new Character[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * A <code>boolean</code> value of false is converted to 0, and a value of true is converted to 1.
	 * 
	 * @param source A <code>boolean[]</code> Array.
	 * @return A <code>char[]</code> Array.
	 */
	public static char[] charArray ( boolean[] source )
	{
		if ( source == null ) return null;

		char[] dest = new char[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( char ) ( source[ i ]? 1 : 0 );
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>byte[]</code> Array.
	 * @return A <code>char[]</code> Array.
	 */
	public static char[] charArray ( byte[] source )
	{
		if ( source == null ) return null;

		char[] dest = new char[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( char ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>short[]</code> Array.
	 * @return A <code>char[]</code> Array.
	 */
	public static char[] charArray ( short[] source )
	{
		if ( source == null ) return null;

		char[] dest = new char[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( char ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>int[]</code> Array.
	 * @return A <code>char[]</code> Array.
	 */
	public static char[] charArray ( int[] source )
	{
		if ( source == null ) return null;

		char[] dest = new char[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( char ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>long[]</code> Array.
	 * @return A <code>char[]</code> Array.
	 */
	public static char[] charArray ( long[] source )
	{
		if ( source == null ) return null;

		char[] dest = new char[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( char ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>float[]</code> Array.
	 * @return A <code>char[]</code> Array.
	 */
	public static char[] charArray ( float[] source )
	{
		if ( source == null ) return null;

		char[] dest = new char[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( char ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>double[]</code> Array.
	 * @return A <code>char[]</code> Array.
	 */
	public static char[] charArray ( double[] source )
	{
		if ( source == null ) return null;

		char[] dest = new char[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( char ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>Short[]</code> Array.
	 * @return A <code>short[]</code> Array.
	 */
	public static short[] shortArray ( Short[] source )
	{
		if ( source == null ) return null;
	
		short[] dest = new short[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>short[]</code> Array.
	 * @return A <code>Short[]</code> Array.
	 */
	public static Short[] shortArray ( short[] source )
	{
		if ( source == null ) return null;
	
		Short[] dest = new Short[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * A <code>boolean</code> value of false is converted to 0, and a value of true is converted to 1.
	 * 
	 * @param source A <code>boolean[]</code> Array.
	 * @return A <code>short[]</code> Array.
	 */
	public static short[] shortArray ( boolean[] source )
	{
		if ( source == null ) return null;

		short[] dest = new short[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( short ) ( source[ i ]? 1 : 0 );
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>byte[]</code> Array.
	 * @return A <code>short[]</code> Array.
	 */
	public static short[] shortArray ( byte[] source )
	{
		if ( source == null ) return null;

		short[] dest = new short[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( short ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>char[]</code> Array.
	 * @return A <code>short[]</code> Array.
	 */
	public static short[] shortArray ( char[] source )
	{
		if ( source == null ) return null;

		short[] dest = new short[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( short ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>int[]</code> Array.
	 * @return A <code>short[]</code> Array.
	 */
	public static short[] shortArray ( int[] source )
	{
		if ( source == null ) return null;

		short[] dest = new short[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( short ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>long[]</code> Array.
	 * @return A <code>short[]</code> Array.
	 */
	public static short[] shortArray ( long[] source )
	{
		if ( source == null ) return null;

		short[] dest = new short[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( short ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>float[]</code> Array.
	 * @return A <code>short[]</code> Array.
	 */
	public static short[] shortArray ( float[] source )
	{
		if ( source == null ) return null;

		short[] dest = new short[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( short ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>double[]</code> Array.
	 * @return A <code>short[]</code> Array.
	 */
	public static short[] shortArray ( double[] source )
	{
		if ( source == null ) return null;

		short[] dest = new short[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( short ) source[ i ];
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>String[]</code> Array.
	 * @return A <code>short[]</code> Array.
	 */
	public static short[] shortArray ( String[] source )
	{
		if ( source == null ) return null;

		short[] dest = new short[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = Short.valueOf ( source[ i ] );
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>Integer[]</code> Array.
	 * @return A <code>int[]</code> Array.
	 */
	public static int[] intArray ( Integer[] source )
	{
		if ( source == null ) return null;
	
		int[] dest = new int[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>int[]</code> Array.
	 * @return A <code>Integer[]</code> Array.
	 */
	public static Integer[] intArray ( int[] source )
	{
		if ( source == null ) return null;
	
		Integer[] dest = new Integer[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * A <code>boolean</code> value of false is converted to 0, and a value of true is converted to 1.
	 * 
	 * @param source A <code>boolean[]</code> Array.
	 * @return A <code>int[]</code> Array.
	 */
	public static int[] intArray ( boolean[] source )
	{
		if ( source == null ) return null;

		int[] dest = new int[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ]? 1 : 0;
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>byte[]</code> Array.
	 * @return A <code>int[]</code> Array.
	 */
	public static int[] intArray ( byte[] source )
	{
		if ( source == null ) return null;

		int[] dest = new int[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( int ) source[ i ];
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>char[]</code> Array.
	 * @return A <code>int[]</code> Array.
	 */
	public static int[] intArray ( char[] source )
	{
		if ( source == null ) return null;

		int[] dest = new int[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( int ) source[ i ];
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>short[]</code> Array.
	 * @return A <code>int[]</code> Array.
	 */
	public static int[] intArray ( short[] source )
	{
		if ( source == null ) return null;

		int[] dest = new int[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( int ) source[ i ];
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>long[]</code> Array.
	 * @return A <code>int[]</code> Array.
	 */
	public static int[] intArray ( long[] source )
	{
		if ( source == null ) return null;

		int[] dest = new int[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( int ) source[ i ];
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>float[]</code> Array.
	 * @return A <code>int[]</code> Array.
	 */
	public static int[] intArray ( float[] source )
	{
		if ( source == null ) return null;

		int[] dest = new int[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( int ) source[ i ];
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>double[]</code> Array.
	 * @return A <code>int[]</code> Array.
	 */
	public static int[] intArray ( double[] source )
	{
		if ( source == null ) return null;

		int[] dest = new int[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( int ) source[ i ];
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>String[]</code> Array.
	 * @return A <code>int[]</code> Array.
	 */
	public static int[] intArray ( String[] source )
	{
		if ( source == null ) return null;

		int[] dest = new int[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = Integer.valueOf ( source[ i ] );
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>Long[]</code> Array.
	 * @return A <code>long[]</code> Array.
	 */
	public static long[] longArray ( Long[] source )
	{
		if ( source == null ) return null;
	
		long[] dest = new long[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>long[]</code> Array.
	 * @return A <code>Long[]</code> Array.
	 */
	public static Long[] longArray ( long[] source )
	{
		if ( source == null ) return null;
	
		Long[] dest = new Long[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * A <code>boolean</code> value of false is converted to 0, and a value of true is converted to 1.
	 * 
	 * @param source A <code>boolean[]</code> Array.
	 * @return A <code>long[]</code> Array.
	 */
	public static long[] longArray ( boolean[] source )
	{
		if ( source == null ) return null;

		long[] dest = new long[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( long ) ( source[ i ]? 1 : 0 );
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>byte[]</code> Array.
	 * @return A <code>long[]</code> Array.
	 */
	public static long[] longArray ( byte[] source )
	{
		if ( source == null ) return null;

		long[] dest = new long[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( long ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>char[]</code> Array.
	 * @return A <code>long[]</code> Array.
	 */
	public static long[] longArray ( char[] source )
	{
		if ( source == null ) return null;

		long[] dest = new long[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( long ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>short[]</code> Array.
	 * @return A <code>long[]</code> Array.
	 */
	public static long[] longArray ( short[] source )
	{
		if ( source == null ) return null;

		long[] dest = new long[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( long ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>int[]</code> Array.
	 * @return A <code>long[]</code> Array.
	 */
	public static long[] longArray ( int[] source )
	{
		if ( source == null ) return null;

		long[] dest = new long[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( long ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>float[]</code> Array.
	 * @return A <code>long[]</code> Array.
	 */
	public static long[] longArray ( float[] source )
	{
		if ( source == null ) return null;

		long[] dest = new long[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( long ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>double[]</code> Array.
	 * @return A <code>long[]</code> Array.
	 */
	public static long[] longArray ( double[] source )
	{
		if ( source == null ) return null;

		long[] dest = new long[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( long ) source[ i ];
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>String[]</code> Array.
	 * @return A <code>long[]</code> Array.
	 */
	public static long[] longArray ( String[] source )
	{
		if ( source == null ) return null;

		long[] dest = new long[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = Long.valueOf ( source[ i ] );
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>Float[]</code> Array.
	 * @return A <code>float[]</code> Array.
	 */
	public static float[] floatArray ( Float[] source )
	{
		if ( source == null ) return null;
	
		float[] dest = new float[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>float[]</code> Array.
	 * @return A <code>Float[]</code> Array.
	 */
	public static Float[] floatArray ( float[] source )
	{
		if ( source == null ) return null;
	
		Float[] dest = new Float[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * A <code>boolean</code> value of false is converted to 0.0, and a value of true is converted to 1.0.
	 * 
	 * @param source A <code>boolean[]</code> Array.
	 * @return A <code>float[]</code> Array.
	 */
	public static float[] floatArray ( boolean[] source )
	{
		if ( source == null ) return null;

		float[] dest = new float[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( float ) ( source[ i ]? 1 : 0 );
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>byte[]</code> Array.
	 * @return A <code>float[]</code> Array.
	 */
	public static float[] floatArray ( byte[] source )
	{
		if ( source == null ) return null;

		float[] dest = new float[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( float ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>char[]</code> Array.
	 * @return A <code>float[]</code> Array.
	 */
	public static float[] floatArray ( char[] source )
	{
		if ( source == null ) return null;

		float[] dest = new float[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( float ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>short[]</code> Array.
	 * @return A <code>float[]</code> Array.
	 */
	public static float[] floatArray ( short[] source )
	{
		if ( source == null ) return null;

		float[] dest = new float[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( float ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>int[]</code> Array.
	 * @return A <code>float[]</code> Array.
	 */
	public static float[] floatArray ( int[] source )
	{
		if ( source == null ) return null;

		float[] dest = new float[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( float ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>long[]</code> Array.
	 * @return A <code>float[]</code> Array.
	 */
	public static float[] floatArray ( long[] source )
	{
		if ( source == null ) return null;

		float[] dest = new float[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( float ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>double[]</code> Array.
	 * @return A <code>float[]</code> Array.
	 */
	public static float[] floatArray ( double[] source )
	{
		if ( source == null ) return null;

		float[] dest = new float[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( float ) source[ i ];
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>String[]</code> Array.
	 * @return A <code>float[]</code> Array.
	 */
	public static float[] floatArray ( String[] source )
	{
		if ( source == null ) return null;

		float[] dest = new float[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = Float.valueOf ( source[ i ] );
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>Double[]</code> Array.
	 * @return A <code>double[]</code> Array.
	 */
	public static double[] doubleArray ( Double[] source )
	{
		if ( source == null ) return null;

		double[] dest = new double[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ];
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>double[]</code> Array.
	 * @return A <code>Double[]</code> Array.
	 */
	public static Double[] doubleArray ( double[] source )
	{
		if ( source == null ) return null;

		Double[] dest = new Double[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ];
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * A <code>boolean</code> value of false is converted to 0.0, and a value of true is converted to 1.0.
	 * 
	 * @param source A <code>boolean[]</code> Array.
	 * @return A <code>double[]</code> Array.
	 */
	public static double[] doubleArray ( boolean[] source )
	{
		if ( source == null ) return null;

		double[] dest = new double[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( double ) ( source[ i ]? 1 : 0 );
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>byte[]</code> Array.
	 * @return A <code>double[]</code> Array.
	 */
	public static double[] doubleArray ( byte[] source )
	{
		if ( source == null ) return null;

		double[] dest = new double[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( double ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>char[]</code> Array.
	 * @return A <code>double[]</code> Array.
	 */
	public static double[] doubleArray ( char[] source )
	{
		if ( source == null ) return null;

		double[] dest = new double[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( double ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>short[]</code> Array.
	 * @return A <code>double[]</code> Array.
	 */
	public static double[] doubleArray ( short[] source )
	{
		if ( source == null ) return null;

		double[] dest = new double[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( double ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>int[]</code> Array.
	 * @return A <code>double[]</code> Array.
	 */
	public static double[] doubleArray ( int[] source )
	{
		if ( source == null ) return null;

		double[] dest = new double[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( double ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>long[]</code> Array.
	 * @return A <code>double[]</code> Array.
	 */
	public static double[] doubleArray ( long[] source )
	{
		if ( source == null ) return null;

		double[] dest = new double[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( double ) source[ i ];
		
		return dest;
	}

	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>float[]</code> Array.
	 * @return A <code>double[]</code> Array.
	 */
	public static double[] doubleArray ( float[] source )
	{
		if ( source == null ) return null;

		double[] dest = new double[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = ( double ) source[ i ];
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>String[]</code> Array.
	 * @return A <code>double[]</code> Array.
	 */
	public static double[] doubleArray ( String[] source )
	{
		if ( source == null ) return null;

		double[] dest = new double[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = Double.valueOf ( source[ i ] );
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>boolean[]</code> Array.
	 * @return A <code>String[]</code> Array.
	 */
	public static String[] stringArray ( boolean[] source )
	{
		if ( source == null ) return null;

		String[] dest = new String[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = String.valueOf ( source[ i ] );
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param format A <code>Format</code> used to generate the String value.
	 * @param source A <code>boolean[]</code> Array.
	 * @return A <code>[]</code> Array.
	 */
	public static String[] stringArray ( Format format, boolean[] source )
	{
		if ( source == null ) return null;

		String[] dest = new String[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = format.format ( source[ i ] );
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>byte[]</code> Array.
	 * @return A <code>String[]</code> Array.
	 */
	public static String[] stringArray ( byte[] source )
	{
		return stringArray ( longArray ( source ) );
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param format A <code>NumberFormat</code> used to generate the String value.
	 * @param source A <code>byte[]</code> Array.
	 * @return A <code>String[]</code> Array.
	 */
	public static String[] stringArray ( NumberFormat format, byte[] source )
	{
		return stringArray ( format, longArray ( source ) );
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>short[]</code> Array.
	 * @return A <code>String[]</code> Array.
	 */
	public static String[] stringArray ( short[] source )
	{
		return stringArray ( longArray ( source ) );
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param format A <code>NumberFormat</code> used to generate the String value.
	 * @param source A <code>short[]</code> Array.
	 * @return A <code>String[]</code> Array.
	 */
	public static String[] stringArray ( NumberFormat format, short[] source )
	{
		return stringArray ( format, longArray ( source ) );
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>int[]</code> Array.
	 * @return A <code>String[]</code> Array.
	 */
	public static String[] stringArray ( int[] source )
	{
		return stringArray ( longArray ( source ) );
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param format A <code>NumberFormat</code> used to generate the String value.
	 * @param source A <code>int[]</code> Array.
	 * @return A <code>String[]</code> Array.
	 */
	public static String[] stringArray ( NumberFormat format, int[] source )
	{
		return stringArray ( format, longArray ( source ) );
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>long[]</code> Array.
	 * @return A <code>String[]</code> Array.
	 */
	public static String[] stringArray ( long[] source )
	{
		if ( source == null ) return null;

		String[] dest = new String[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = String.valueOf ( source[ i ] );
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param format A <code>NumberFormat</code> used to generate the String value.
	 * @param source A <code>long[]</code> Array.
	 * @return A <code>String[]</code> Array.
	 */
	public static String[] stringArray ( NumberFormat format, long[] source )
	{
		if ( source == null ) return null;

		String[] dest = new String[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = format.format ( source[ i ] );
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>float[]</code> Array.
	 * @return A <code>String[]</code> Array.
	 */
	public static String[] stringArray ( float[] source )
	{
		return stringArray ( doubleArray ( source ) );
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param format A <code>NumberFormat</code> used to generate the String value.
	 * @param source A <code>float[]</code> Array.
	 * @return A <code>String[]</code> Array.
	 */
	public static String[] stringArray ( NumberFormat format, float[] source )
	{
		return stringArray ( format, doubleArray ( source ) );
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param source A <code>double[]</code> Array.
	 * @return A <code>String[]</code> Array.
	 */
	public static String[] stringArray ( double[] source )
	{
		if ( source == null ) return null;

		String[] dest = new String[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = String.valueOf ( source[ i ] );
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param format A <code>NumberFormat</code> used to generate the String value.
	 * @param source A <code>double[]</code> Array.
	 * @return A <code>String[]</code> Array.
	 */
	public static String[] stringArray ( NumberFormat format, double[] source )
	{
		if ( source == null ) return null;

		String[] dest = new String[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = format.format ( source[ i ] );
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * Uses <code>Object.toString()</code> to generate the String values.
	 * 
	 * @param source A <code>Object[]</code> Array.
	 * @return A <code>String[]</code> Array.
	 */
	public static String[] stringArray ( Object[] source )
	{
		if ( source == null ) return null;

		String[] dest = new String[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = source[ i ].toString();
		
		return dest;
	}
	
	/**
	 * Performs an Array type conversion.
	 * 
	 * @param format A <code>Format</code> used to generate the String values.
	 * @param source A <code>Object[]</code> Array.
	 * @return A <code>String[]</code> Array.
	 */
	public static String[] stringArray ( Format format, Object[] source )
	{
		if ( source == null ) return null;

		String[] dest = new String[ source.length ];
		
		for ( int i=0; i<source.length; i++ ) dest[ i ] = format.format ( source[ i ] );
		
		return dest;
	}
}

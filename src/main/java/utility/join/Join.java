package utility.join;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Arrays;

import static utility.arrays.ArrayConverter.*;

import java.util.Iterator;

/**
 * Join is non-instantiatable class that provides static methods to join arrays or collections into Strings.
 * <p>
 * For each variation of the <code>join</code> method, the following apply:
 * <ul>
 * <li>Each value in the array or collection will be separated by a delimiter string.</li>
 * <li>If the array or collection contains only one element, then the generated String will not contain the delimiter String.</li>
 * <li>If the array or collection is null or empty, an empty String is returned.</li>
 * </ul>
 * 
 * @author Rob Duff
 *
 */
public class Join 
{
	private Join()
	{}
	
	/**
	 * Creates a string from a <code>boolean</code> array.<p>
	 * 
	 * @param delimiter A <code>String</code> to separate the individual values.
	 * @param collection An array of <code>boolean</code> values.
	 * @return A <code>String</code> constructed of the values in <code>collection</code>, separated by <code>delimiter</code>.
	 */
	public static String join ( String delimiter, boolean[] collection )
	{
		if ( collection == null )
			return "";
		
		StringBuilder builder = new StringBuilder();

		for ( int i=0; i<collection.length; i++ )
		{
			if ( i > 0 ) builder.append ( delimiter );
			builder.append ( collection[i] );
		}

		return builder.toString();
	}
	
	/**
	 * Creates a string from a <code>boolean</code> array.<p>
	 * 
	 * @param delimiter A <code>String</code> to separate the individual values.
	 * @param format A <code>Format</code> used to format the <code>boolean</code> values.
	 * @param collection An array of <code>boolean</code> values.
	 * @return A <code>String</code> constructed of the formatted values in <code>collection</code>, separated by <code>delimiter</code>.
	 */
	public static String join ( String delimiter, Format format, boolean[] collection )
	{
		if ( collection == null )
			return "";
		
		StringBuilder builder = new StringBuilder();

		for ( int i=0; i<collection.length; i++ )
		{
			if ( i > 0 ) builder.append ( delimiter );
			builder.append ( format.format ( collection[i] ) );
		}

		return builder.toString();
	}

	/**
	 * Creates a string from a <code>byte</code> array.<p>
	 * 
	 * @param delimiter A <code>String</code> to separate the individual values.
	 * @param collection An array of <code>byte</code> values.
	 * @return A <code>String</code> constructed of the values in <code>collection</code>, separated by <code>delimiter</code>.
	 */
	public static String join ( String delimiter, byte[] collection )
	{
		return join ( delimiter, longArray ( collection ) );
	}

	/**
	 * Creates a string from a <code>byte</code> array.<p>
	 * 
	 * @param delimiter A <code>String</code> to separate the individual values.
	 * @param format A <code>NumberFormat</code> used to format the <code>byte</code> values.
	 * @param collection An array of <code>byte</code> values.
	 * @return A <code>String</code> constructed of the formatted values in <code>collection</code>, separated by <code>delimiter</code>.
	 */
	public static String join ( String delimiter, NumberFormat format, byte[] collection )
	{
		return join ( delimiter, format, longArray ( collection ) );
	}

	/**
	 * Creates a string from a <code>char</code> array.<p>
	 * 
	 * @param delimiter A <code>String</code> to separate the individual values.
	 * @param collection An array of <code>char</code> values.
	 * @return A <code>String</code> constructed of the values in <code>collection</code>, separated by <code>delimiter</code>.
	 */
	public static String join ( String delimiter, char[] collection )
	{
		return join ( delimiter, longArray ( collection ) );
	}

	/**
	 * Creates a string from a <code>char</code> array.<p>
	 * 
	 * @param delimiter A <code>String</code> to separate the individual values.
	 * @param format A <code>NumberFormat</code> used to format the <code>char</code> values.
	 * @param collection An array of <code>char</code> values.
	 * @return A <code>String</code> constructed of the formatted values in <code>collection</code>, separated by <code>delimiter</code>.
	 */
	public static String join ( String delimiter, NumberFormat format, char[] collection )
	{
		return join ( delimiter, format, longArray ( collection ) );
	}

	/**
	 * Creates a string from a <code>short</code> array.<p>
	 * 
	 * @param delimiter A <code>String</code> to separate the individual values.
	 * @param collection An array of <code>short</code> values.
	 * @return A <code>String</code> constructed of the values in <code>collection</code>, separated by <code>delimiter</code>.
	 */
	public static String join ( String delimiter, short[] collection )
	{
		return join ( delimiter, longArray ( collection ) );
	}

	/**
	 * Creates a string from a <code>short</code> array.<p>
	 * 
	 * @param delimiter A <code>String</code> to separate the individual values.
	 * @param format A <code>NumberFormat</code> used to format the <code>short</code> values.
	 * @param collection An array of <code>short</code> values.
	 * @return A <code>String</code> constructed of the formatted values in <code>collection</code>, separated by <code>delimiter</code>.
	 */
	public static String join ( String delimiter, NumberFormat format, short[] collection )
	{
		return join ( delimiter, format, longArray ( collection ) );
	}

	/**
	 * Creates a string from a <code>int</code> array.<p>
	 * 
	 * @param delimiter A <code>String</code> to separate the individual values.
	 * @param collection An array of <code>int</code> values.
	 * @return A <code>String</code> constructed of the values in <code>collection</code>, separated by <code>delimiter</code>.
	 */
	public static String join ( String delimiter, int[] collection )
	{
		return join ( delimiter, longArray ( collection ) );
	}

	/**
	 * Creates a string from a <code>int</code> array.<p>
	 * 
	 * @param delimiter A <code>String</code> to separate the individual values.
	 * @param format A <code>NumberFormat</code> used to format the <code>int</code> values.
	 * @param collection An array of <code>int</code> values.
	 * @return A <code>String</code> constructed of the formatted values in <code>collection</code>, separated by <code>delimiter</code>.
	 */
	public static String join ( String delimiter, NumberFormat format, int[] collection )
	{
		return join ( delimiter, format, longArray ( collection ) );
	}

	/**
	 * Creates a string from a <code>long</code> array.<p>
	 * 
	 * @param delimiter A <code>String</code> to separate the individual values.
	 * @param collection An array of <code>long</code> values.
	 * @return A <code>String</code> constructed of the values in <code>collection</code>, separated by <code>delimiter</code>.
	 */
	public static String join ( String delimiter, long[] collection )
	{
		return join ( delimiter, NumberFormat.getIntegerInstance(), collection );
	}

	/**
	 * Creates a string from a <code>long</code> array.<p>
	 * 
	 * @param delimiter A <code>String</code> to separate the individual values.
	 * @param format A <code>NumberFormat</code> used to format the <code>long</code> values.
	 * @param collection An array of <code>long</code> values.
	 * @return A <code>String</code> constructed of the formatted values in <code>collection</code>, separated by <code>delimiter</code>.
	 */
	public static String join ( String delimiter, NumberFormat format, long[] collection )
	{
		if ( collection == null )
			return "";

		StringBuilder builder = new StringBuilder();

		for ( int i=0; i<collection.length; i++ )
		{
			if ( i > 0 ) builder.append ( delimiter );
			builder.append ( format.format ( collection[i] ) );
		}

		return builder.toString();
	}

	/**
	 * Creates a string from a <code>float</code> array.<p>
	 * 
	 * @param delimiter A <code>String</code> to separate the individual values.
	 * @param collection An array of <code>float</code> values.
	 * @return A <code>String</code> constructed of the values in <code>collection</code>, separated by <code>delimiter</code>.
	 */
	public static String join ( String delimiter, float[] collection )
	{
		return join ( delimiter, doubleArray ( collection ) );
	}

	/**
	 * Creates a string from a <code>float</code> array.<p>
	 * 
	 * @param delimiter A <code>String</code> to separate the individual values.
	 * @param format A <code>NumberFormat</code> used to format the <code>float</code> values.
	 * @param collection An array of <code>float</code> values.
	 * @return A <code>String</code> constructed of the formatted values in <code>collection</code>, separated by <code>delimiter</code>.
	 */
	public static String join ( String delimiter, NumberFormat format, float[] collection )
	{
		return join ( delimiter, format, doubleArray ( collection ) );
	}

	/**
	 * Creates a string from a <code>double</code> array.<p>
	 * 
	 * @param delimiter A <code>String</code> to separate the individual values.
	 * @param collection An array of <code>double</code> values.
	 * @return A <code>String</code> constructed of the values in <code>collection</code>, separated by <code>delimiter</code>.
	 */
	public static String join ( String delimiter, double[] collection )
	{
		return join ( delimiter, DecimalFormat.getNumberInstance(), collection );
	}

	/**
	 * Creates a string from a <code>double</code> array.<p>
	 * 
	 * @param delimiter A <code>String</code> to separate the individual values.
	 * @param format A <code>NumberFormat</code> used to format the <code>double</code> values.
	 * @param collection An array of <code>double</code> values.
	 * @return A <code>String</code> constructed of the formatted values in <code>collection</code>, separated by <code>delimiter</code>.
	 */
	public static String join ( String delimiter, NumberFormat format, double[] collection )
	{
		if ( collection == null )
			return "";

		StringBuilder builder = new StringBuilder();

		for ( int i=0; i<collection.length; i++ )
		{
			if ( i > 0 ) builder.append ( delimiter );
			builder.append ( format.format ( collection[i] ) );
		}

		return builder.toString();
	}

	/**
	 * Creates a string from an <code>Object</code> array.
	 * <p>
	 * This method uses <code>Object.toString()</code> to format each value.
	 * </p>
	 * @param delimiter A <code>String</code> to separate the individual values.
	 * @param collection An <code>Object</code> array.
	 * @return A <code>String</code> constructed of the values in <code>collection</code>, separated by <code>delimiter</code>.
	 */
	public static String join ( String delimiter, Object[] collection )
	{
		if ( collection == null )
			return "";
		return join ( delimiter, Arrays.asList ( collection ) );
	}

	/**
	 * Creates a string from an <code>Object</code> array.
	 * <p>
	 * This method uses a <code>Format</code> to format each value.
	 * </p>
	 * @param delimiter A <code>String</code> to separate the individual values.
	 * @param format A <code>Format</code> used to format the values in the array.
	 * @param collection An <code>Object</code> array.
	 * @return A <code>String</code> constructed of the values in <code>collection</code>, separated by <code>delimiter</code>.
	 */
	public static String join ( String delimiter, Format format, Object[] collection )
	{
		if ( collection == null )
			return "";
		return join ( delimiter, format, Arrays.asList ( collection ) );
	}

	/**
	 * Creates a string from an <code>Iterable</code> collection of values of type <code>Object</code>.
	 * <p>
	 * This method uses <code>Object.toString()</code> to format each value.
	 * </p>
	 * @param delimiter A <code>String</code> to separate the individual values.
	 * @param iterable A collection implementing <code>Iterable</code>.
	 * @return A <code>String</code> constructed of the values in <code>iterable</code>, separated by <code>delimiter</code>.
	 */
	public static String join ( String delimiter, Iterable< ? extends Object > iterable )
	{
		if ( iterable == null )
			return "";

		StringBuilder builder = new StringBuilder();

		Iterator< ? extends Object > i = iterable.iterator();

		if ( i.hasNext() )
			builder.append ( i.next().toString() );

		while ( i.hasNext() )
			builder.append( delimiter ).append ( i.next().toString() );

		return builder.toString();
	}

	/**
	 * Creates a string from an <code>Iterable</code> collection of values of type <code>Object</code>.
	 * <p>
	 * This method uses a <code>Format</code> to format each value.
	 * 
	 * @param delimiter A <code>String</code> to separate the individual values.
	 * @param format A <code>Format</code> used to format the values in the collection.
	 * @param iterable A collection implementing <code>Iterable</code>.
	 * @return A <code>String</code> constructed of the values in <code>iterable</code>, separated by <code>delimiter</code>.
	 */
	public static String join ( String delimiter, Format format, Iterable< ? extends Object > iterable )
	{
		if ( iterable == null )
			return "";
	
		StringBuilder builder = new StringBuilder();
	
		Iterator< ? extends Object > i = iterable.iterator();
	
		if ( i.hasNext() )
			builder.append ( format.format ( i.next() ) );
	
		while ( i.hasNext() )
			builder.append( delimiter ).append ( format.format ( i.next() ) );
	
		return builder.toString();
	}
}

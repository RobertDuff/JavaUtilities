package utility.arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static utility.arrays.ArrayConverter.booleanArray;
import static utility.arrays.ArrayConverter.byteArray;
import static utility.arrays.ArrayConverter.charArray;
import static utility.arrays.ArrayConverter.doubleArray;
import static utility.arrays.ArrayConverter.floatArray;
import static utility.arrays.ArrayConverter.intArray;
import static utility.arrays.ArrayConverter.longArray;
import static utility.arrays.ArrayConverter.shortArray;
import static utility.arrays.ArrayConverter.stringArray;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.junit.Test;

public class ArrayConverterTest
{
	private static final boolean[]   ta  = new boolean[]   { false, true, true };
	private static final Boolean[]   TA  = new Boolean[]   { false, true, true };
	
	private static final byte[]      ba  = new byte[]      {     0,    1,    1 };
	private static final Byte[]      BA  = new Byte[]      {     0,    1,    1 };
	
	private static final char[]      ca  = new char[]      {     0,    1,    1 };
	private static final Character[] CA  = new Character[] {     0,    1,    1 };
	
	private static final short[]     sa  = new short[]     {     0,    1,    1 };
	private static final Short[]     SA  = new Short[]     {     0,    1,    1 };
	
	private static final int[]       ia  = new int[]       {     0,    1,    1 };
	private static final Integer[]   IA  = new Integer[]   {     0,    1,    1 };
	
	private static final long[]      la  = new long[]      {     0,    1,    1 };
	private static final Long[]      LA  = new Long[]      {    0l,   1l,   1l };
	
	private static final float[]     fa  = new float[]     {     0,    1,    1 };
	private static final Float[]     FA  = new Float[]     {    0f,   1f,   1f };
	
	private static final double[]    da  = new double[]    {     0,    1,    1 };
	private static final Double[]    DA  = new Double[]    {    0d,   1d,   1d };
	
	private static final String[]    tsa = new String[]    { "false",  "true",  "true" };
	private static final String[]    nsa = new String[]    {   "0",  "1",  "1" };
	private static final String[]    fsa = new String[]    {   "0.0",  "1.0",  "1.0" };
	private static final String[]    xsa = new String[]    {   "00",  "01",  "01" };
	
	public void assertBooleanArrayEquals ( boolean[] a, boolean[] b )
	{
		if ( a == null && b == null ) return;
		
		assertEquals ( a.length, b.length );
		
		for ( int i=0; i<a.length; i++ )
			assertTrue ( a[ i ] == b[ i ] );
	}
	@Test
	public final void testBooleanArray()
	{		
		assertNull ( booleanArray ( ( boolean[] ) null ) );
		assertNull ( booleanArray ( ( Boolean[] ) null ) );
		assertNull ( booleanArray ( ( byte[]    ) null ) );
		assertNull ( booleanArray ( ( char[]    ) null ) );
		assertNull ( booleanArray ( ( short[]   ) null ) );
		assertNull ( booleanArray ( ( int[]     ) null ) );
		assertNull ( booleanArray ( ( long[]    ) null ) );
		assertNull ( booleanArray ( ( float[]   ) null ) );
		assertNull ( booleanArray ( ( double[]  ) null ) );
		assertNull ( booleanArray ( ( String[]  ) null ) );
		
		assertBooleanArrayEquals ( ta, booleanArray ( TA ) );
		assertArrayEquals        ( TA, booleanArray ( ta ) );
		assertBooleanArrayEquals ( ta, booleanArray ( ba ) );
		assertBooleanArrayEquals ( ta, booleanArray ( ca ) );
		assertBooleanArrayEquals ( ta, booleanArray ( sa ) );
		assertBooleanArrayEquals ( ta, booleanArray ( ia ) );
		assertBooleanArrayEquals ( ta, booleanArray ( la ) );
		assertBooleanArrayEquals ( ta, booleanArray ( fa ) );
		assertBooleanArrayEquals ( ta, booleanArray ( da ) );
		assertBooleanArrayEquals ( ta, booleanArray ( tsa ) );
	}

	@Test
	public final void testByteArray ()
	{
		assertNull ( byteArray ( ( boolean[] ) null ) );
		assertNull ( byteArray ( ( byte[]    ) null ) );
		assertNull ( byteArray ( ( Byte[]    ) null ) );
		assertNull ( byteArray ( ( char[]    ) null ) );
		assertNull ( byteArray ( ( short[]   ) null ) );
		assertNull ( byteArray ( ( int[]     ) null ) );
		assertNull ( byteArray ( ( long[]    ) null ) );
		assertNull ( byteArray ( ( float[]   ) null ) );
		assertNull ( byteArray ( ( double[]  ) null ) );
		assertNull ( byteArray ( ( String[]  ) null ) );
		
		assertArrayEquals ( ba, byteArray ( ta ) );
		assertArrayEquals ( ba, byteArray ( BA ) );
		assertArrayEquals ( BA, byteArray ( ba ) );
		assertArrayEquals ( ba, byteArray ( ca ) );
		assertArrayEquals ( ba, byteArray ( sa ) );
		assertArrayEquals ( ba, byteArray ( ia ) );
		assertArrayEquals ( ba, byteArray ( la ) );
		assertArrayEquals ( ba, byteArray ( fa ) );
		assertArrayEquals ( ba, byteArray ( da ) );
		assertArrayEquals ( ba, byteArray ( nsa ) );
	}

	@Test
	public final void testCharArray ()
	{
		assertNull ( charArray ( ( boolean[]   ) null ) );
		assertNull ( charArray ( ( byte[]      ) null ) );
		assertNull ( charArray ( ( char[]      ) null ) );
		assertNull ( charArray ( ( Character[] ) null ) );
		assertNull ( charArray ( ( short[]     ) null ) );
		assertNull ( charArray ( ( int[]       ) null ) );
		assertNull ( charArray ( ( long[]      ) null ) );
		assertNull ( charArray ( ( float[]     ) null ) );
		assertNull ( charArray ( ( double[]    ) null ) );
		
		assertArrayEquals ( ca, charArray ( ta ) );
		assertArrayEquals ( ca, charArray ( ba ) );
		assertArrayEquals ( ca, charArray ( CA ) );
		assertArrayEquals ( CA, charArray ( ca ) );
		assertArrayEquals ( ca, charArray ( sa ) );
		assertArrayEquals ( ca, charArray ( ia ) );
		assertArrayEquals ( ca, charArray ( la ) );
		assertArrayEquals ( ca, charArray ( fa ) );
		assertArrayEquals ( ca, charArray ( da ) );
	}

	@Test
	public final void testShortArray ()
	{		
		assertNull ( shortArray ( ( boolean[] ) null ) );
		assertNull ( shortArray ( ( byte[]    ) null ) );
		assertNull ( shortArray ( ( char[]    ) null ) );
		assertNull ( shortArray ( ( short[]   ) null ) );
		assertNull ( shortArray ( ( Short[]   ) null ) );
		assertNull ( shortArray ( ( int[]     ) null ) );
		assertNull ( shortArray ( ( long[]    ) null ) );
		assertNull ( shortArray ( ( float[]   ) null ) );
		assertNull ( shortArray ( ( double[]  ) null ) );
		assertNull ( shortArray ( ( String[]  ) null ) );
		
		assertArrayEquals ( sa, shortArray ( ta ) );
		assertArrayEquals ( sa, shortArray ( ba ) );
		assertArrayEquals ( sa, shortArray ( ca ) );
		assertArrayEquals ( sa, shortArray ( SA ) );
		assertArrayEquals ( SA, shortArray ( sa ) );
		assertArrayEquals ( sa, shortArray ( ia ) );
		assertArrayEquals ( sa, shortArray ( la ) );
		assertArrayEquals ( sa, shortArray ( fa ) );
		assertArrayEquals ( sa, shortArray ( da ) );
		assertArrayEquals ( sa, shortArray ( nsa ) );
	}

	@Test
	public final void testIntArray ()
	{
		assertNull ( intArray ( ( boolean[] ) null ) );
		assertNull ( intArray ( ( byte[]    ) null ) );
		assertNull ( intArray ( ( char[]    ) null ) );
		assertNull ( intArray ( ( short[]   ) null ) );
		assertNull ( intArray ( ( int[]     ) null ) );
		assertNull ( intArray ( ( Integer[] ) null ) );
		assertNull ( intArray ( ( long[]    ) null ) );
		assertNull ( intArray ( ( float[]   ) null ) );
		assertNull ( intArray ( ( double[]  ) null ) );
		assertNull ( intArray ( ( String[]  ) null ) );
		
		assertArrayEquals ( ia, intArray ( ta ) );
		assertArrayEquals ( ia, intArray ( ba ) );
		assertArrayEquals ( ia, intArray ( ca ) );
		assertArrayEquals ( ia, intArray ( sa ) );
		assertArrayEquals ( ia, intArray ( IA ) );
		assertArrayEquals ( IA, intArray ( ia ) );
		assertArrayEquals ( ia, intArray ( la ) );
		assertArrayEquals ( ia, intArray ( fa ) );
		assertArrayEquals ( ia, intArray ( da ) );
		assertArrayEquals ( ia, intArray ( nsa ) );
	}

	@Test
	public final void testLongArray ()
	{
		assertNull ( longArray ( ( boolean[] ) null ) );
		assertNull ( longArray ( ( byte[]    ) null ) );
		assertNull ( longArray ( ( char[]    ) null ) );
		assertNull ( longArray ( ( short[]   ) null ) );
		assertNull ( longArray ( ( int[]     ) null ) );
		assertNull ( longArray ( ( long[]    ) null ) );
		assertNull ( longArray ( ( Long[]    ) null ) );
		assertNull ( longArray ( ( float[]   ) null ) );
		assertNull ( longArray ( ( double[]  ) null ) );
		assertNull ( longArray ( ( String[]  ) null ) );
		
		assertArrayEquals ( la, longArray ( ta ) );
		assertArrayEquals ( la, longArray ( ba ) );
		assertArrayEquals ( la, longArray ( ca ) );
		assertArrayEquals ( la, longArray ( sa ) );
		assertArrayEquals ( la, longArray ( ia ) );
		assertArrayEquals ( la, longArray ( LA ) );
		assertArrayEquals ( LA, longArray ( la ) );
		assertArrayEquals ( la, longArray ( fa ) );
		assertArrayEquals ( la, longArray ( da ) );
		assertArrayEquals ( la, longArray ( nsa ) );
	}

	@Test
	public final void testFloatArray ()
	{		
		assertNull ( floatArray ( ( boolean[] ) null ) );
		assertNull ( floatArray ( ( byte[]    ) null ) );
		assertNull ( floatArray ( ( char[]    ) null ) );
		assertNull ( floatArray ( ( short[]   ) null ) );
		assertNull ( floatArray ( ( int[]     ) null ) );
		assertNull ( floatArray ( ( long[]    ) null ) );
		assertNull ( floatArray ( ( float[]   ) null ) );
		assertNull ( floatArray ( ( Float[]   ) null ) );
		assertNull ( floatArray ( ( double[]  ) null ) );
		assertNull ( floatArray ( ( String[]  ) null ) );
		
		assertArrayEquals ( fa, floatArray ( ta ), 0.1f );
		assertArrayEquals ( fa, floatArray ( ba ), 0.1f );
		assertArrayEquals ( fa, floatArray ( ca ), 0.1f );
		assertArrayEquals ( fa, floatArray ( sa ), 0.1f );
		assertArrayEquals ( fa, floatArray ( ia ), 0.1f );
		assertArrayEquals ( fa, floatArray ( la ), 0.1f );
		assertArrayEquals ( fa, floatArray ( FA ), 0.1f );
		assertArrayEquals ( FA, floatArray ( fa )       );
		assertArrayEquals ( fa, floatArray ( da ), 0.1f );
		assertArrayEquals ( fa, floatArray ( nsa ), 0.1f );
	}

	@Test
	public final void testDoubleArray ()
	{
		assertNull ( doubleArray ( ( boolean[] ) null ) );
		assertNull ( doubleArray ( ( byte[]    ) null ) );
		assertNull ( doubleArray ( ( char[]    ) null ) );
		assertNull ( doubleArray ( ( short[]   ) null ) );
		assertNull ( doubleArray ( ( int[]     ) null ) );
		assertNull ( doubleArray ( ( long[]    ) null ) );
		assertNull ( doubleArray ( ( float[]   ) null ) );
		assertNull ( doubleArray ( ( double[]  ) null ) );
		assertNull ( doubleArray ( ( Double[]  ) null ) );
		assertNull ( doubleArray ( ( String[]  ) null ) );
		
		assertArrayEquals ( da, doubleArray ( ta ), 0.1d );
		assertArrayEquals ( da, doubleArray ( ba ), 0.1d );
		assertArrayEquals ( da, doubleArray ( ca ), 0.1d );
		assertArrayEquals ( da, doubleArray ( sa ), 0.1d );
		assertArrayEquals ( da, doubleArray ( ia ), 0.1d );
		assertArrayEquals ( da, doubleArray ( la ), 0.1d );
		assertArrayEquals ( da, doubleArray ( fa ), 0.1d );
		assertArrayEquals ( da, doubleArray ( DA ), 0.1d );
		assertArrayEquals ( DA, doubleArray ( da )       );
		assertArrayEquals ( da, doubleArray ( nsa ), 0.1d );
	}

	@Test
	public final void testStringArray()
	{
		assertNull ( stringArray ( ( boolean[] ) null ) );
		assertNull ( stringArray ( ( byte[]    ) null ) );
		assertNull ( stringArray ( ( short[]   ) null ) );
		assertNull ( stringArray ( ( int[]     ) null ) );
		assertNull ( stringArray ( ( long[]    ) null ) );
		assertNull ( stringArray ( ( float[]   ) null ) );
		assertNull ( stringArray ( ( double[]  ) null ) );
		
		assertArrayEquals ( tsa, stringArray ( ta ) );
		assertArrayEquals ( nsa, stringArray ( ba ) );
		assertArrayEquals ( nsa, stringArray ( sa ) );
		assertArrayEquals ( nsa, stringArray ( ia ) );
		assertArrayEquals ( nsa, stringArray ( la ) );
		assertArrayEquals ( fsa, stringArray ( fa ) );
		assertArrayEquals ( fsa, stringArray ( da ) );
		
		NumberFormat format = new DecimalFormat ( "00" );
		
		assertArrayEquals ( xsa, stringArray ( format, ba ) );
		assertArrayEquals ( xsa, stringArray ( format, sa ) );
		assertArrayEquals ( xsa, stringArray ( format, ia ) );
		assertArrayEquals ( xsa, stringArray ( format, la ) );
		assertArrayEquals ( xsa, stringArray ( format, fa ) );
		assertArrayEquals ( xsa, stringArray ( format, da ) );
	}
}

package utility.join;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static utility.join.Join.join;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class JoinTest
{	
	private static class Fred
	{
		private int x;
		
		public Fred ( int i )
		{
			x = i;
		}
		
		public String toString()
		{
			return "Fred-" + x;
		}
	}
	
	@Test
	public void testJoinStringBooleanArray()
	{
		boolean[] a;
		String e;
		String r;
		
		a = null;
		e = "";
		r = join ( ", ", a );
		assertEquals ( e, r );

		a = new boolean[ 0 ];
		e = "";
		r = join ( ", ", a );
		assertEquals ( e, r );

		a = new boolean[] { true };
		e = "true";
		r = join ( ", ", a );
		assertEquals ( e, r );

		a = new boolean[] { true, true, false, true };
		e = "true, true, false, true";
		r = join ( ", ", a );
		assertEquals ( e, r );
		
		Format format = new Format()
		{
			private static final long serialVersionUID = -3271932577242036718L;

			@Override
			public StringBuffer format ( Object obj, StringBuffer toAppendTo, FieldPosition pos )
			{
				Boolean bool = ( Boolean ) obj;
				
				toAppendTo.append ( bool? "YES" : "NO" );
				return toAppendTo;
			}

			@Override
			public Object parseObject ( String source, ParsePosition pos )
			{
				fail ( "Not Implemented!" );
				return null;
			}
		};
		
		a = new boolean[] { true, true, false, true };
		e = "YES, YES, NO, YES";
		r = join ( ", ", format, a );
		assertEquals ( e, r );
	}

	@Test
	public void testJoinStringByteArray() 
	{
		byte[] a;
		String e;
		String r;
		
		a = null;
		e = "";
		r = join ( ", ", a );
		assertEquals ( e, r );
	
		a = new byte[ 0 ];
		e = "";
		r = join ( ", ", a );
		assertEquals ( e, r );
	
		a = new byte[] { 1 };
		e = "1";
		r = join ( ", ", a );
		assertEquals ( e, r );
	
		a = new byte[] { 1, 2, 3, 4 };
		e = "1, 2, 3, 4";
		r = join ( ", ", a );
		assertEquals ( e, r );
		
		a = new byte[] { 23, -23 };
		e = "023 -023";
		NumberFormat format = new DecimalFormat ( "000" );
		format.setGroupingUsed ( true );
		r = join ( " ", format, a );
		assertEquals ( e, r );
	}

	@Test
	public void testJoinStringCharArray() 
	{
		char[] a;
		String e;
		String r;
		
		a = null;
		e = "";
		r = join ( ", ", a );
		assertEquals ( e, r );
	
		a = new char[ 0 ];
		e = "";
		r = join ( ", ", a );
		assertEquals ( e, r );
	
		a = new char[] { 1 };
		e = "1";
		r = join ( ", ", a );
		assertEquals ( e, r );
	
		a = new char[] { 1, 2, 3, 4 };
		e = "1, 2, 3, 4";
		r = join ( ", ", a );
		assertEquals ( e, r );
		
		a = new char[] { 23, 555 };
		e = "0023 0555";
		NumberFormat format = new DecimalFormat ( "0000" );
		format.setGroupingUsed ( true );
		r = join ( " ", format, a );
		assertEquals ( e, r );
	}

	@Test
	public void testJoinStringShortArray() 
	{
		short[] a;
		String e;
		String r;
		
		a = null;
		e = "";
		r = join ( ", ", a );
		assertEquals ( e, r );
	
		a = new short[ 0 ];
		e = "";
		r = join ( ", ", a );
		assertEquals ( e, r );
	
		a = new short[] { 1 };
		e = "1";
		r = join ( ", ", a );
		assertEquals ( e, r );
		
		a = new short[] { 1, 2, 3, 4 };
		e = "1, 2, 3, 4";
		r = join ( ", ", a );
		assertEquals ( e, r );
		
		a = new short[] { 12345, -12345 };
		e = "12,345 -12,345";
		NumberFormat format = new DecimalFormat();
		format.setGroupingUsed ( true );
		r = join ( " ", format, a );
		assertEquals ( e, r );
	}

	@Test
	public void testJoinStringIntArray() 
	{
		int[] a;
		String e;
		String r;
		
		a = null;
		e = "";
		r = join ( ", ", a );
		assertEquals ( e, r );

		a = new int[ 0 ];
		e = "";
		r = join ( ", ", a );
		assertEquals ( e, r );

		a = new int[] { 1 };
		e = "1";
		r = join ( ", ", a );
		assertEquals ( e, r );

		a = new int[] { 1, 2, 3, 4 };
		e = "1, 2, 3, 4";
		r = join ( ", ", a );
		assertEquals ( e, r );
		
		a = new int[] { 12345678, -12345678 };
		e = "12,345,678 -12,345,678";
		NumberFormat format = new DecimalFormat();
		format.setGroupingUsed ( true );
		r = join ( " ", format, a );
		assertEquals ( e, r );
	}

	@Test
	public void testJoinStringLongArray() 
	{
		long[] a;
		String e;
		String r;
		
		a = null;
		e = "";
		r = join ( ", ", a );
		assertEquals ( e, r );

		a = new long[ 0 ];
		e = "";
		r = join ( ", ", a );
		assertEquals ( e, r );

		a = new long[] { 1 };
		e = "1";
		r = join ( ", ", a );
		assertEquals ( e, r );

		a = new long[] { 1, 2, 3, 4 };
		e = "1, 2, 3, 4";
		r = join ( ", ", a );
		assertEquals ( e, r );
		
		a = new long[] { 12345678901L, -12345678901L };
		e = "12,345,678,901 -12,345,678,901";
		NumberFormat format = new DecimalFormat();
		format.setGroupingUsed ( true );
		r = join ( " ", format, a );
		assertEquals ( e, r );
	}

	@Test
	public void testJoinStringFloatArray()
	{
		float[] a;
		String e;
		String r;
		
		a = null;
		e = "";
		r = join ( ", ", a );
		assertEquals ( e, r );

		a = new float[ 0 ];
		e = "";
		r = join ( ", ", a );
		assertEquals ( e, r );

		a = new float[] { 1 };
		e = "1";
		r = join ( ", ", a );
		assertEquals ( e, r );

		a = new float[] { 1f, 2.2f, 3.33f, 4.444f };
		e = "1, 2.2, 3.33, 4.444";
		r = join ( ", ", a );
		assertEquals ( e, r );
		
		a = new float[] { 1.23F, -456.3456F };
		e = "1.230 -456.346";
		NumberFormat format = new DecimalFormat ( "#,###.000" );
		r = join ( " ", format, a );
		assertEquals ( e, r );
	}

	@Test
	public void testJoinStringDoubleArray() 
	{
		double[] a;
		String e;
		String r;
		
		a = null;
		e = "";
		r = join ( ", ", a );
		assertEquals ( e, r );

		a = new double[ 0 ];
		e = "";
		r = join ( ", ", a );
		assertEquals ( e, r );

		a = new double[] { 1 };
		e = "1";
		r = join ( ", ", a );
		assertEquals ( e, r );

		a = new double[] { 1, 2.2, 3.33, 4.444 };
		e = "1, 2.2, 3.33, 4.444";
		r = join ( ", ", a );
		assertEquals ( e, r );
		
		a = new double[] { 1.23, -123456.3456 };
		e = "1.230 -123,456.346";
		NumberFormat format = new DecimalFormat ( "#,###.000" );
		r = join ( " ", format, a );
		assertEquals ( e, r );
	}

	@Test
	public void testJoinStringObjectArray()
	{
		Object[] a;
		String e;
		String r;
		
		a = null;
		e = "";
		r = join ( ", ", a );
		assertEquals ( e, r );

		a = new Fred[ 0 ];
		e = "";
		r = join ( ", ", a );
		assertEquals ( e, r );

		a = new Fred[] { new Fred ( 7 ) };
		e = "Fred-7";
		r = join ( ", ", a );
		assertEquals ( e, r );

		a = new Fred[] { new Fred ( 3 ), new Fred ( 8 ) };
		e = "Fred-3, Fred-8";
		r = join ( ", ", a );
		assertEquals ( e, r );
		
		Format format = new Format()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public StringBuffer format ( Object obj, StringBuffer toAppendTo, FieldPosition pos )
			{
				toAppendTo.append ( "{" ).append( obj.toString() ).append ( "}" );
				return toAppendTo;
			}

			@Override
			public Object parseObject ( String source, ParsePosition pos )
			{
				fail ( "Not Implemented!" );
				return null;
			}
		};
				
		a = new Fred[] { new Fred ( 3 ), new Fred ( 8 ) };
		e = "{Fred-3}, {Fred-8}";
		r = join ( ", ", format, a );
		assertEquals ( e, r );
	}

	@Test
	public void testJoinStringIterableOfQextendsObject() 
	{
		List<Object> a;
		String e;
		String r;
		
		a = null;
		e = "";
		r = join ( ", ", a );
		assertEquals ( e, r );

		a = new ArrayList<Object>();
		e = "";
		r = join ( ", ", a );
		assertEquals ( e, r );

		a = Arrays.asList( new Object[] { new Fred ( 7 ) } );
		e = "Fred-7";
		r = join ( ", ", a );
		assertEquals ( e, r );

		a = Arrays.asList( new Object[] { new Fred ( 3 ), new Fred ( 8 ) } );
		e = "Fred-3, Fred-8";
		r = join ( ", ", a );
		assertEquals ( e, r );
		
		Format format = new Format()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public StringBuffer format ( Object obj, StringBuffer toAppendTo, FieldPosition pos )
			{
				toAppendTo.append ( "{" ).append( obj.toString() ).append ( "}" );
				return toAppendTo;
			}

			@Override
			public Object parseObject ( String source, ParsePosition pos )
			{
				fail ( "Not Implemented!" );
				return null;
			}
		};
				
		a = Arrays.asList ( new Object[] { new Fred ( 3 ), new Fred ( 8 ) } );
		e = "{Fred-3}, {Fred-8}";
		r = join ( ", ", format, a );
		assertEquals ( e, r );
	}
}

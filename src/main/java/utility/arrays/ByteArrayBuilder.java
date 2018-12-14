package utility.arrays;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ByteArrayBuilder
{
	private List<Byte> buffer = new ArrayList<>();

	public ByteArrayBuilder()
	{}

	public ByteArrayBuilder append ( boolean b )
	{
		buffer.add ( ( byte ) ( b? 1 : 0 ) );
		return this;
	}

	public ByteArrayBuilder append ( Byte b )
	{
		buffer.add ( b );
		return this;
	}

	public ByteArrayBuilder append ( Byte[] a )
	{
		for ( Byte b : a )
			buffer.add ( b );

		return this;
	}

	public ByteArrayBuilder append ( Byte[] a, int offset, int length )
	{
		for ( Byte b : a )
			buffer.add ( b );

		return this;
	}

	public ByteArrayBuilder append ( byte[] a )
	{
		for ( byte b : a )
			buffer.add ( b );

		return this;
	}

	public ByteArrayBuilder append ( byte[] a, int offset, int length )
	{
		for ( byte b : a )
			buffer.add ( b );

		return this;
	}

	public ByteArrayBuilder append ( Character c )
	{
		return append ( c.toString() );
	}

	public ByteArrayBuilder append ( char[] a )
	{
		for ( char c : a )
			append ( c );

		return this;
	}

	public ByteArrayBuilder append ( char[] a, int offset, int length )
	{
		for ( int i = offset; i < offset+length; i++ )
			append ( a[ i ] );

		return this;
	}

	public ByteArrayBuilder append ( Character[] a )
	{
		for ( Character c : a )
			append ( c );

		return this;
	}

	public ByteArrayBuilder append ( Character[] a, int offset, int length )
	{
		for ( int i = offset; i < offset+length; i++ )
			append ( a[ i ] );

		return this;
	}
	
	public ByteArrayBuilder append ( CharSequence s )
	{
		return append ( s.toString() );
	}
	
	public ByteArrayBuilder append ( Double d )
	{
		return append ( ByteBuffer.allocate ( Double.BYTES ).putDouble ( d ).array() );
	}
	
	public ByteArrayBuilder append ( Float f )
	{
		return append ( ByteBuffer.allocate ( Float.BYTES ).putFloat ( f ).array() );
	}
	
	public ByteArrayBuilder append ( Short s )
	{
		return append ( ByteBuffer.allocate ( Short.BYTES ).putShort ( s ).array() );
	}
	
	public ByteArrayBuilder append ( Integer i )
	{
		return append ( ByteBuffer.allocate ( Integer.BYTES ).putInt ( i ).array() );
	}
	
	public ByteArrayBuilder append ( Long l )
	{
		return append ( ByteBuffer.allocate ( Long.BYTES ).putLong ( l ).array() );
	}

	public ByteArrayBuilder append ( String s )
	{
		return append ( s.getBytes() );
	}

	public ByteArrayBuilder append ( StringBuffer s )
	{
		return append ( s.toString() );
	}
}

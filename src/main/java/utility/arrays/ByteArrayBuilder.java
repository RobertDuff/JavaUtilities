package utility.arrays;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Adds data of various types to a single byte array.
 * 
 * This is primarily for use in encoding protocol messages prior to transmission.
 * 
 * @author Rob Duff
 *
 */
public class ByteArrayBuilder
{
	private ByteBuffer scratchBuffer = ByteBuffer.allocate ( Double.BYTES );
	private List<Byte> buffer = new ArrayList<>();

	/**
	 * Constructs a new {@code ByteArrayBuilder}
	 */
	public ByteArrayBuilder()
	{}

	/**
	 * Sets the Byte Ordering to use when adding numeric types.
	 * 
	 * @param order The byte ordering to use for numeric types.
	 * @return This Builder.
	 */
	public ByteArrayBuilder order ( ByteOrder order )
	{
		scratchBuffer.order ( order );
		
		return this;
	}
	
	/**
	 * Adds a boolean value as a single byte (0x00 for false, 0x01 for true)
	 * 
	 * @param b The boolean value to add.
	 * @return This Builder.
	 */
	public ByteArrayBuilder add ( boolean b )
	{
		return add ( ( byte ) ( b? 1 : 0 ) );
	}

	/**
	 * @param b A byte to add.
	 * @return This Builder.
	 */
	public ByteArrayBuilder add ( byte b )
	{
		buffer.add ( b );
		return this;
	}
	
	/**
	 * @param c A character to add.
	 * @return This Builder.
	 */
	public ByteArrayBuilder add ( char c )
	{
		return add ( String.valueOf ( c ) );
	}
	
	/**
	 * @param s A short to add.
	 * @return This Builder.
	 */
	public ByteArrayBuilder add ( short s )
	{
		scratchBuffer.clear();
		return add ( scratchBuffer.putShort ( s ).array(), 0, Short.BYTES );
	}

	/**
	 * @param i An integer to add.
	 * @return This Builder.
	 */
	public ByteArrayBuilder add ( int i )
	{
		scratchBuffer.clear();
		return add ( scratchBuffer.putInt ( i ).array(), 0, Integer.BYTES );
	}

	/**
	 * @param l A long to add.
	 * @return This Builder.
	 */
	public ByteArrayBuilder add ( long l )
	{
		scratchBuffer.clear();
		return add ( scratchBuffer.putLong ( l ).array(), 0, Long.BYTES );
	}

	/**
	 * @param f A float to add.
	 * @return This Builder.
	 */
	public ByteArrayBuilder add ( float f )
	{
		scratchBuffer.clear();
		return add ( scratchBuffer.putFloat ( f ).array(), 0, Float.BYTES );
	}

	/**
	 * @param d A double to add.
	 * @return This Builder.
	 */
	public ByteArrayBuilder add ( double d )
	{
		scratchBuffer.clear();
		return add ( scratchBuffer.putDouble ( d ).array(), 0, Double.BYTES );
	}
	
	/**
	 * @param a A byte array to add.
	 * @return This Builder.
	 */
	public ByteArrayBuilder add ( byte[] a )
	{
		buffer.addAll ( Arrays.asList ( ArrayConverter.byteArray ( a ) ) );
		return this;
	}

	/**
	 * @param a A byte array to add from.
	 * @param offset The offset of the starting point to add from.
	 * @param length The number of bytes to add.
	 * @return This Builder.
	 */
	public ByteArrayBuilder add ( byte[] a, int offset, int length )
	{
		buffer.addAll ( Arrays.asList ( ArrayConverter.byteArray ( a ) ).subList ( offset, offset+length ) );
		return this;
	}

	/**
	 * @param s A string to add, as a byte array.
	 * @return This Builder.
	 */
	public ByteArrayBuilder add ( String s )
	{
		return add ( s.getBytes() );
	}
	
	/**
	 * @return The current size of the byte array.
	 */
	public int size()
	{
		return buffer.size ();
	}
	
	/**
	 * @return The composed byte array.
	 */
	public byte[] build()
	{
		return ArrayConverter.byteArray ( buffer.toArray ( new Byte[0] ) );
	}
}

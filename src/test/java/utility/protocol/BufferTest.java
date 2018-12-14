package utility.protocol;

import static org.junit.Assert.*;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

import org.junit.BeforeClass;
import org.junit.Test;

public class BufferTest
{
	public static ByteBuffer b;
	
	@BeforeClass
	public static void beforeClass()
	{
		b = ByteBuffer.allocate ( 10 );
	}
	
	@Test
	public void test()
	{
		assertEquals ( 10, b.capacity() );
		
		assertEquals ( 0, b.position() );
		assertEquals ( 10, b.limit () );
		
		b.put ( ( byte )  1 );
		b.put ( ( byte )  2 );
		b.put ( ( byte )  3 );
		b.put ( ( byte )  4 );
		b.put ( ( byte )  5 );
		b.put ( ( byte )  6 );
		
		assertEquals ( 6, b.position() );
		assertEquals ( 10, b.limit() );
		
		b.put ( ( byte )  7 );
		
		assertEquals ( 7, b.position() );
		assertEquals ( 10, b.limit() );

		b.flip();
		
		assertEquals ( 0, b.position() );
		assertEquals ( 7, b.limit() );
		
		assertEquals (  1, b.get() );
		assertEquals (  2, b.get() );

		assertEquals ( 2, b.position() );
		assertEquals ( 7, b.limit() );
		
		assertEquals (  3, b.get() );
		assertEquals (  4, b.get() );
		assertEquals (  5, b.get() );

		assertEquals ( 5, b.position() );
		assertEquals ( 7, b.limit() );
		
		b.compact();
		
		assertEquals ( 2, b.position() );
		assertEquals ( 10, b.limit() );

		b.put ( ( byte )  8 );
		b.put ( ( byte )  9 );
		b.put ( ( byte ) 10 );
		b.put ( ( byte ) 11 );
		b.put ( ( byte ) 12 );
		b.put ( ( byte ) 13 );
		b.put ( ( byte ) 14 );
		b.put ( ( byte ) 15 );
		
		assertEquals ( 10, b.position() );
		assertEquals ( 10, b.limit() );
	
		try
		{
			b.put ( ( byte ) 0 );
			fail ( "Unexpected Success" );
		}
		catch ( BufferOverflowException e )
		{
			// Expected Exception
		}
		
		b.flip();
		
		assertEquals (  0, b.position() );
		assertEquals ( 10, b.limit() );
		
		assertEquals (  6, b.get() );
		assertEquals (  7, b.get() );
		assertEquals (  8, b.get() );
		assertEquals (  9, b.get() );
		assertEquals ( 10, b.get() );
		assertEquals ( 11, b.get() );
		assertEquals ( 12, b.get() );
		assertEquals ( 13, b.get() );
		assertEquals ( 14, b.get() );
		assertEquals ( 15, b.get() );		
		
		assertEquals ( 10, b.position() );
		assertEquals ( 10, b.limit() );
		
		try
		{
			b.get ();
			fail ( "Unexpected Success" );
		}
		catch ( BufferUnderflowException e )
		{
			// Expected Exception
		}
		
		b.clear();
		
		assertEquals (  0, b.position() );
		assertEquals ( 10, b.limit() );
		
		b.put ( ( byte ) 16 );
		b.put ( ( byte ) 17 );
		b.put ( ( byte ) 18 );
		b.put ( ( byte ) 19 );
		b.put ( ( byte ) 20 );
		
		assertEquals (  5, b.position() );
		assertEquals ( 10, b.limit() );
		
		b.flip();
		
		assertEquals (  0, b.position() );
		assertEquals (  5, b.limit() );
		
		b.compact();
		
		assertEquals (  5, b.position() );
		assertEquals ( 10, b.limit() );
		
		b.flip();
		
		assertEquals (  0, b.position() );
		assertEquals (  5, b.limit() );

		assertEquals ( 16, b.get() );
		assertEquals ( 17, b.get() );
		assertEquals ( 18, b.get() );
		
		assertEquals (  3, b.position() );
		assertEquals (  5, b.limit() );
		
		b.clear();
		
		assertEquals (  0, b.position() );
		assertEquals ( 10, b.limit() );
		
		b.put ( ( byte ) 21 );
		
		assertEquals (  1, b.position() );
		assertEquals ( 10, b.limit() );
		
		b.flip();
		
		assertEquals (  0, b.position() );
		assertEquals (  1, b.limit() );
		
		assertEquals ( 21, b.get() );
	}
	
	public void nothing()
	{
		//--
		
		b.put ( ( byte ) 22 );
		b.put ( ( byte ) 23 );
		b.put ( ( byte ) 24 );
		b.put ( ( byte ) 25 );
		b.put ( ( byte ) 26 );
		b.put ( ( byte ) 27 );
		b.put ( ( byte ) 28 );
		b.put ( ( byte ) 29 );
		b.put ( ( byte ) 30 );

		assertEquals ( 22, b.get() );
		assertEquals ( 23, b.get() );
		assertEquals ( 24, b.get() );
		assertEquals ( 25, b.get() );
		assertEquals ( 26, b.get() );
		assertEquals ( 27, b.get() );
		assertEquals ( 28, b.get() );
		assertEquals ( 29, b.get() );
		assertEquals ( 30, b.get() );
}
}

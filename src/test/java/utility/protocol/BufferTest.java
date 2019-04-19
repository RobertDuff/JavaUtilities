package utility.protocol;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BufferTest
{
	public static ByteBuffer b;
	
	@BeforeAll
	public static void beforeAll()
	{
		b = ByteBuffer.allocate ( 10 );
	}
	
	@Test
	public void test()
	{
		Assertions.assertEquals ( 10, b.capacity() );
		
		Assertions.assertEquals ( 0, b.position() );
		Assertions.assertEquals ( 10, b.limit () );
		
		b.put ( ( byte )  1 );
		b.put ( ( byte )  2 );
		b.put ( ( byte )  3 );
		b.put ( ( byte )  4 );
		b.put ( ( byte )  5 );
		b.put ( ( byte )  6 );
		
		Assertions.assertEquals ( 6, b.position() );
		Assertions.assertEquals ( 10, b.limit() );
		
		b.put ( ( byte )  7 );
		
		Assertions.assertEquals ( 7, b.position() );
		Assertions.assertEquals ( 10, b.limit() );

		b.flip();
		
		Assertions.assertEquals ( 0, b.position() );
		Assertions.assertEquals ( 7, b.limit() );
		
		Assertions.assertEquals (  1, b.get() );
		Assertions.assertEquals (  2, b.get() );

		Assertions.assertEquals ( 2, b.position() );
		Assertions.assertEquals ( 7, b.limit() );
		
		Assertions.assertEquals (  3, b.get() );
		Assertions.assertEquals (  4, b.get() );
		Assertions.assertEquals (  5, b.get() );

		Assertions.assertEquals ( 5, b.position() );
		Assertions.assertEquals ( 7, b.limit() );
		
		b.compact();
		
		Assertions.assertEquals ( 2, b.position() );
		Assertions.assertEquals ( 10, b.limit() );

		b.put ( ( byte )  8 );
		b.put ( ( byte )  9 );
		b.put ( ( byte ) 10 );
		b.put ( ( byte ) 11 );
		b.put ( ( byte ) 12 );
		b.put ( ( byte ) 13 );
		b.put ( ( byte ) 14 );
		b.put ( ( byte ) 15 );
		
		Assertions.assertEquals ( 10, b.position() );
		Assertions.assertEquals ( 10, b.limit() );
	
		Assertions.assertThrows ( BufferOverflowException.class, () -> b.put ( ( byte ) 0 ) );
		
		b.flip();
		
		Assertions.assertEquals (  0, b.position() );
		Assertions.assertEquals ( 10, b.limit() );
		
		Assertions.assertEquals (  6, b.get() );
		Assertions.assertEquals (  7, b.get() );
		Assertions.assertEquals (  8, b.get() );
		Assertions.assertEquals (  9, b.get() );
		Assertions.assertEquals ( 10, b.get() );
		Assertions.assertEquals ( 11, b.get() );
		Assertions.assertEquals ( 12, b.get() );
		Assertions.assertEquals ( 13, b.get() );
		Assertions.assertEquals ( 14, b.get() );
		Assertions.assertEquals ( 15, b.get() );		
		
		Assertions.assertEquals ( 10, b.position() );
		Assertions.assertEquals ( 10, b.limit() );
		
		Assertions.assertThrows ( BufferUnderflowException.class, () -> b.get () );
		
		b.clear();
		
		Assertions.assertEquals (  0, b.position() );
		Assertions.assertEquals ( 10, b.limit() );
		
		b.put ( ( byte ) 16 );
		b.put ( ( byte ) 17 );
		b.put ( ( byte ) 18 );
		b.put ( ( byte ) 19 );
		b.put ( ( byte ) 20 );
		
		Assertions.assertEquals (  5, b.position() );
		Assertions.assertEquals ( 10, b.limit() );
		
		b.flip();
		
		Assertions.assertEquals (  0, b.position() );
		Assertions.assertEquals (  5, b.limit() );
		
		b.compact();
		
		Assertions.assertEquals (  5, b.position() );
		Assertions.assertEquals ( 10, b.limit() );
		
		b.flip();
		
		Assertions.assertEquals (  0, b.position() );
		Assertions.assertEquals (  5, b.limit() );

		Assertions.assertEquals ( 16, b.get() );
		Assertions.assertEquals ( 17, b.get() );
		Assertions.assertEquals ( 18, b.get() );
		
		Assertions.assertEquals (  3, b.position() );
		Assertions.assertEquals (  5, b.limit() );
		
		b.clear();
		
		Assertions.assertEquals (  0, b.position() );
		Assertions.assertEquals ( 10, b.limit() );
		
		b.put ( ( byte ) 21 );
		
		Assertions.assertEquals (  1, b.position() );
		Assertions.assertEquals ( 10, b.limit() );
		
		b.flip();
		
		Assertions.assertEquals (  0, b.position() );
		Assertions.assertEquals (  1, b.limit() );
		
		Assertions.assertEquals ( 21, b.get() );
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

		Assertions.assertEquals ( 22, b.get() );
		Assertions.assertEquals ( 23, b.get() );
		Assertions.assertEquals ( 24, b.get() );
		Assertions.assertEquals ( 25, b.get() );
		Assertions.assertEquals ( 26, b.get() );
		Assertions.assertEquals ( 27, b.get() );
		Assertions.assertEquals ( 28, b.get() );
		Assertions.assertEquals ( 29, b.get() );
		Assertions.assertEquals ( 30, b.get() );
}
}

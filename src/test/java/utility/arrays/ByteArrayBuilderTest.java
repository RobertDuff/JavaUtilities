package utility.arrays;

import static org.junit.Assert.*;

import java.nio.ByteOrder;

import org.junit.Before;
import org.junit.Test;

public class ByteArrayBuilderTest
{	
	public ByteArrayBuilder b;
	
	@Before
	public void before()
	{
		b = new ByteArrayBuilder();
	}
	
	@Test
	public void testBigEndian ()
	{
		b.order ( ByteOrder.BIG_ENDIAN );
		b.add ( 0x12345678 );		

		assertEquals ( 4, b.size () );
		assertArrayEquals ( new byte[] { 0x12, 0x34, 0x56, 0x78 }, b.build () );
	}
	
	@Test
	public void testLittleEndian ()
	{
		b.order ( ByteOrder.LITTLE_ENDIAN );
		b.add ( 0x12345678 );		

		assertEquals ( 4, b.size () );
		assertArrayEquals ( new byte[] { 0x78, 0x56, 0x34, 0x12 }, b.build () );
	}

	@Test
	public void testAddBoolean ()
	{
		b.add ( false ).add ( true ).add ( true ).add ( false );

		assertEquals ( 4, b.size () );
		assertArrayEquals ( new byte[] { 0, 1, 1, 0 }, b.build() );
	}

	@Test
	public void testAddByte ()
	{
		b.add ( ( byte ) 34 ).add ( ( byte ) 76 ).add ( ( byte ) -77 );

		assertEquals ( 3, b.size () );
		assertArrayEquals ( new byte[] { 34, 76, -77 }, b.build() );
	}

	@Test
	public void testAddChar ()
	{
		b.add ( 'a' ).add ( 'b' ).add ( 'c' );

		assertEquals ( 3, b.size () );
		assertArrayEquals ( new byte[] { 97, 98, 99 }, b.build() );
	}

	@Test
	public void testAddShort ()
	{
		b.add ( ( short ) -20993 ).add ( ( short ) 9154 ).add ( ( short ) 22193 );

		assertEquals ( 6, b.size () );
		assertArrayEquals ( new byte[] { 
				( byte ) 0xAD, ( byte ) 0xFF, 
				( byte ) 0x23, ( byte ) 0xC2, 
				( byte ) 0x56, ( byte ) 0xB1 }, 
				b.build() );
	}

	@Test
	public void testAddInt ()
	{
		b.add ( -1122313631 ).add ( 2129873967 ).add ( 819892692 );

		assertEquals ( 12, b.size () );
		assertArrayEquals ( new byte[] { 
				( byte ) 0xBD, ( byte ) 0x1A, ( byte ) 0xDA, ( byte ) 0x61, 
				( byte ) 0x7E, ( byte ) 0xF3, ( byte ) 0x4C, ( byte ) 0x2F, 
				( byte ) 0x30, ( byte ) 0xDE, ( byte ) 0x91, ( byte ) 0xD4 }, 
				b.build() );
	}

	@Test
	public void testAddLong ()
	{
		b.add ( 8124088504366606916l ).add ( 1697017769961754950l ).add ( 4463426302046954370l );

		assertEquals ( 24, b.size () );
		assertArrayEquals ( new byte[] { 
				( byte ) 0x70, ( byte ) 0xBE, ( byte ) 0x8F, ( byte ) 0x73, ( byte ) 0x92, ( byte ) 0x9B, ( byte ) 0x2A, ( byte ) 0x44,
				( byte ) 0x17, ( byte ) 0x8D, ( byte ) 0x04, ( byte ) 0xAB, ( byte ) 0xAC, ( byte ) 0x18, ( byte ) 0x91, ( byte ) 0x46,
				( byte ) 0x3D, ( byte ) 0xF1, ( byte ) 0x46, ( byte ) 0x92, ( byte ) 0xCE, ( byte ) 0xA4, ( byte ) 0xE3, ( byte ) 0x82 },
				b.build() );
	}

	@Test
	public void testAddFloat ()
	{
		b.add ( 83.008636f ).add ( 92.827850f ).add ( 64.354210f );

		assertEquals ( 12, b.size () );
		assertArrayEquals ( new byte[] { 
				( byte ) 0x42,( byte ) 0xA6,( byte ) 0x04,( byte ) 0x6C,
				( byte ) 0x42,( byte ) 0xB9,( byte ) 0xA7,( byte ) 0xDC,
				( byte ) 0x42,( byte ) 0x80,( byte ) 0xB5,( byte ) 0x5B },
				b.build() );
	}

	@Test
	public void testAddDouble ()
	{
		b.add ( 95.66057188536202 ).add ( 9.400089375115206 ).add ( 78.41230737284762 );

		assertEquals ( 24, b.size () );
		assertArrayEquals ( new byte[] { 
				( byte ) 0x40,( byte ) 0x57,( byte ) 0xEA,( byte ) 0x46,( byte ) 0xCF,( byte ) 0x4D,( byte ) 0x12,( byte ) 0x5D,
				( byte ) 0x40,( byte ) 0x22,( byte ) 0xCC,( byte ) 0xD8,( byte ) 0x83,( byte ) 0xBB,( byte ) 0x31,( byte ) 0x9B,
				( byte ) 0x40,( byte ) 0x53,( byte ) 0x9A,( byte ) 0x63,( byte ) 0x3E,( byte ) 0x76,( byte ) 0x91,( byte ) 0xEF },
				b.build() );
	}

	@Test
	public void testAddByteArray ()
	{
		byte[] x = { 1, 2, 3 };
		byte[] y = { 10, 11, 12, 13, 14 };
		
		b.add ( x ).add ( y );

		assertEquals ( 8, b.size () );
		assertArrayEquals ( new byte[] { 1, 2, 3, 10, 11, 12, 13, 14 }, b.build() );
	}

	@Test
	public void testAddByteArrayIntInt ()
	{
		byte[] x = {  1,  2,  3,  4,  5,  6,  7,  8,  9 };
		byte[] y = { 11, 12, 13, 14, 15, 16, 17, 18, 19 };
		byte[] z = { 21, 22, 23, 24, 25, 26, 27, 28, 29 };
		
		b.add ( x, 0, 3 ).add ( y, 3, 3 ).add ( z, 6, 3 );

		assertEquals ( 9, b.size () );
		assertArrayEquals ( new byte[] { 1, 2, 3, 14, 15, 16, 27, 28, 29 }, b.build() );
	}

	@Test
	public void testAddString ()
	{
		b.add ( "abc" ).add ( "defg" ).add ( "hi" );

		assertEquals ( 9, b.size () );
		assertArrayEquals ( new byte[] { 97, 98, 99, 100, 101, 102, 103, 104, 105 }, b.build() );
	}
}

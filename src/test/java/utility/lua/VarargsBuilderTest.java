package utility.lua;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.luaj.vm2.Varargs;

public class VarargsBuilderTest
{
	@Test
	public void testEmpty() 
	{
		Varargs v = new VarargsBuilder().build();
		
		assertEquals ( 0, v.narg() );
	}
	
	@Test
	public void testSingles() 
	{
		Varargs v;
		
		v = new VarargsBuilder().add ( 35 ).build();
		
		assertEquals ( 1, v.narg() );
		assertTrue ( v.arg ( 1 ).isint() );
		assertEquals ( 35, v.arg ( 1 ).toint() );
		
		v = new VarargsBuilder().add ( ( long ) 45 ).build();
		
		assertEquals ( 1, v.narg() );
		assertTrue ( v.arg ( 1 ).islong() );
		assertEquals ( 45, v.arg ( 1 ).tolong() );
		
		v = new VarargsBuilder().add ( 7.7 ).build();
		
		assertEquals ( 1, v.narg() );
		v.arg ( 1 ).checkdouble();
		assertEquals ( 7.7, v.arg ( 1 ).todouble(), 0.1 );
		
		v = new VarargsBuilder().add ( true ).build();
		
		assertEquals ( 1, v.narg() );
		assertTrue ( v.arg ( 1 ).isboolean() );
		assertTrue ( v.arg ( 1 ).toboolean() );
		
		v = new VarargsBuilder().add ( "Jackie" ).build();
		
		assertEquals ( 1, v.narg() );
		assertTrue ( v.arg ( 1 ).isstring() );
		assertEquals ( "Jackie", v.arg ( 1 ).tojstring() );
		
		v = new VarargsBuilder().addNil().build();
		
		assertEquals ( 1, v.narg() );
		assertTrue ( v.arg ( 1 ).isnil() );
	}
}

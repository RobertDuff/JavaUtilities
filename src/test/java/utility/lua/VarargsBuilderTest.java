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
	
	@Test
	public void testMultiple()
	{
	    VarargsBuilder a = new VarargsBuilder ();
	    VarargsBuilder b = new VarargsBuilder ();
	    VarargsBuilder c = new VarargsBuilder ();
	    
	    a.add ( true ).add ( 10 );
	    b.add ( "Hello" ).add ( 7.3 );
	    
	    c.add ( a.build () ).add ( b.build () );
	    
	    Varargs v = c.build ();
	    
	    assertEquals ( 4, v.narg () );
	    assertTrue ( v.arg ( 1 ).toboolean () );
	    assertEquals ( 10, v.arg ( 2 ).toint () );
	    assertEquals ( "Hello", v.arg ( 3 ).tojstring () );
	    assertEquals ( 7.3, v.arg ( 4 ).todouble (), 0.01 );
	}
}

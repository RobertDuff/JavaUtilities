package utility.lua;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.luaj.vm2.Varargs;

public class VarargsBuilderTest
{
	@Test
	public void testEmpty() 
	{
		Varargs v = new VarargsBuilder().build();
		
		Assertions.assertEquals ( 0, v.narg() );
	}
	
	@Test
	public void testSingles() 
	{
		Varargs v;
		
		v = new VarargsBuilder().add ( 35 ).build();
		
		Assertions.assertEquals ( 1, v.narg() );
		Assertions.assertTrue ( v.arg ( 1 ).isint() );
		Assertions.assertEquals ( 35, v.arg ( 1 ).toint() );
		
		v = new VarargsBuilder().add ( ( long ) 45 ).build();
		
		Assertions.assertEquals ( 1, v.narg() );
		Assertions.assertTrue ( v.arg ( 1 ).islong() );
		Assertions.assertEquals ( 45, v.arg ( 1 ).tolong() );
		
		v = new VarargsBuilder().add ( 7.7 ).build();
		
		Assertions.assertEquals ( 1, v.narg() );
		v.arg ( 1 ).checkdouble();
		Assertions.assertEquals ( 7.7, v.arg ( 1 ).todouble(), 0.1 );
		
		v = new VarargsBuilder().add ( true ).build();
		
		Assertions.assertEquals ( 1, v.narg() );
		Assertions.assertTrue ( v.arg ( 1 ).isboolean() );
		Assertions.assertTrue ( v.arg ( 1 ).toboolean() );
		
		v = new VarargsBuilder().add ( "Jackie" ).build();
		
		Assertions.assertEquals ( 1, v.narg() );
		Assertions.assertTrue ( v.arg ( 1 ).isstring() );
		Assertions.assertEquals ( "Jackie", v.arg ( 1 ).tojstring() );
		
		v = new VarargsBuilder().addNil().build();
		
		Assertions.assertEquals ( 1, v.narg() );
		Assertions.assertTrue ( v.arg ( 1 ).isnil() );
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
	    
	    Assertions.assertEquals ( 4, v.narg () );
	    Assertions.assertTrue ( v.arg ( 1 ).toboolean () );
	    Assertions.assertEquals ( 10, v.arg ( 2 ).toint () );
	    Assertions.assertEquals ( "Hello", v.arg ( 3 ).tojstring () );
	    Assertions.assertEquals ( 7.3, v.arg ( 4 ).todouble (), 0.01 );
	}
}

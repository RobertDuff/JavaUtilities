package utility.lua;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.JsePlatform;

public class FunctionTest 
{
	@Test
	public void testWithObjects() throws IOException
	{
		Globals g = JsePlatform.standardGlobals();
		
		g.set ( "jackie", 3 );
		
		Function sum = new Function ( g,
				"local sum = jackie", 
				"for i,v in ipairs { ... }",
				"do",
				"  sum = sum + v",
				"end",
				"return sum" );
						
		Assertions.assertEquals ( 3, sum.call().toint() );
		Assertions.assertEquals ( 5, sum.call ( 2 ).toint ( 1 ) );
		Assertions.assertEquals ( 7, sum.call ( 1, -1, 4 ).toint ( 1 ) );
	}	

	@Test
	public void testWithVarargs() throws IOException
	{
		Globals g = JsePlatform.standardGlobals();
		
		g.set ( "jackie", 3 );
		
		Function sum = new Function ( g,
				"local sum = jackie", 
				"for i,v in ipairs { ... }",
				"do",
				"  sum = sum + v",
				"end",
				"return sum" );
						
		Assertions.assertEquals ( 3, sum.call().toint() );
		Assertions.assertEquals ( 5, sum.call ( new VarargsBuilder().add ( 2 ).build() ).toint() );
		Assertions.assertEquals ( 7, sum.call ( new VarargsBuilder().add ( 1 ).add ( ( long ) -1 ).add ( 4.0 ).build() ).toint() );
	}	
}

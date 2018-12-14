package utility.protocol;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FunctionTest
{
	@Test
	public void test() throws Exception
	{
		List<Appliance> l = new ArrayList<>();
						
		l.add ( FunctionTest::objFunc );
		l.add ( o -> FunctionTest.strFunc ( ( String ) o ) );
		
		l.get ( 0 ).apply ( "Fred" );
	}
	
	public static Object objFunc ( Object obj )
	{
		return null;
	}
	
	public static Integer strFunc ( String i )
	{
		return null;
	}
}

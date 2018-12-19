package utility.lua;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaBoolean;
import org.luaj.vm2.LuaDouble;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaInteger;
import org.luaj.vm2.LuaString;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;

public class Function
{
    private Globals globals;
	private String chunk;
	private LuaFunction func;
	
	// Used only by subclasses
	protected Function ( Globals globals )
	{
	    this.globals = globals;
	}
	
	protected void define ( List<String> body )
	{
        StringBuilder chunkBuilder = new StringBuilder();
        
        for ( String line : body )
            chunkBuilder.append ( line ).append( "\n" );
                    
        chunk = chunkBuilder.toString();
        
        func = globals.load ( chunk ).checkfunction();     

	}
	
	protected void define ( String... body )
	{
	    define ( Arrays.asList ( body ) );
	}
    
    public Function ( Globals globals, String... body )
    {       
        this ( globals );
        define ( body );
    }
    
    public Function ( Globals globals, List<String> body )
    {       
        this ( globals );
        define ( body );
    }
	
	public Function ( LuaFunction fn )
	{
		chunk = "<unknown>";
		func = fn;
	}
	
	public LuaValue call ( Varargs args )
	{
		return invoke ( args ).arg1();
	}

    public LuaValue call ( Object... args )
    {
        return invoke ( args ).arg1();
    }

	public Varargs invoke ( Varargs args )
	{
		return func.invoke ( args );
	}
	
    public Varargs invoke ( Object... args )
    {
        return func.invoke ( args ( args ) );
    }
	
	private LuaValue[] args ( Object... args )
	{
		if ( args.length == 0 )
			return LuaValue.NOVALS;
		
		List<LuaValue> list = new ArrayList<>();
		
		for ( Object arg : args )
		{
			if ( arg == null )
				list.add( LuaValue.NIL );
			else if ( arg instanceof Boolean )
				list.add ( LuaBoolean.valueOf ( ( Boolean ) arg ) );
			else if ( arg instanceof Integer )
				list.add ( LuaInteger.valueOf ( ( Integer ) arg ) );
			else if ( arg instanceof Double )
				list.add ( LuaDouble.valueOf ( ( Double ) arg ) );
			else
				list.add ( LuaString.valueOf ( args.toString() ) );
		}
		
		return list.toArray ( LuaValue.NOVALS );
	}

	@Override
	public String toString()
	{
		return "MyFunction [chunk=\n" + chunk + "]";
	}
}

package utility.lua;

import java.util.ArrayList;
import java.util.List;

import org.luaj.vm2.LuaBoolean;
import org.luaj.vm2.LuaDouble;
import org.luaj.vm2.LuaInteger;
import org.luaj.vm2.LuaNumber;
import org.luaj.vm2.LuaString;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;

public class VarargsBuilder 
{
	private List<LuaValue> argsList = new ArrayList<>();
	
	public VarargsBuilder add ( Varargs args )
	{
	    for ( int n=1; n<=args.narg (); n++ )
	        argsList.add ( args.arg ( n ) );
	    
	    return this;
	}
	
	public VarargsBuilder add ( int i )
	{
		argsList.add ( LuaNumber.valueOf ( i ) );
		return this;
	}
	
	public VarargsBuilder add ( long l )
	{
		argsList.add ( LuaNumber.valueOf ( l ) );
		return this;
	}
	
	public VarargsBuilder add ( double d )
	{
		argsList.add ( LuaNumber.valueOf ( d ) );
		return this;
	}
	
	public VarargsBuilder add ( boolean b )
	{
		argsList.add ( LuaBoolean.valueOf ( b ) );
		return this;
	}
	
	public VarargsBuilder add ( String s )
	{
		argsList.add ( LuaString.valueOf ( s ) );
		return this;
	}
	
	public VarargsBuilder add ( LuaValue v )
	{
	    argsList.add ( v );
	    return this;
	}
	
	public VarargsBuilder addNil ()
	{
		argsList.add ( LuaValue.NIL );
		return this;
	}
	
	public VarargsBuilder add ( Object o )
	{
		if ( o == null )
			argsList.add( LuaValue.NIL );
		else if ( o instanceof Boolean )
			argsList.add ( LuaBoolean.valueOf ( ( Boolean ) o ) );
		else if ( o instanceof Integer )
			argsList.add ( LuaInteger.valueOf ( ( Integer ) o ) );
		else if ( o instanceof Double )
			argsList.add ( LuaDouble.valueOf ( ( Double ) o ) );
		else
			argsList.add ( LuaString.valueOf ( o.toString() ) );

		return this;
	}
	
	public VarargsBuilder addAll ( Iterable<? extends Object> os )
	{
	    for ( Object o : os )
	        add ( o );
	    return this;
	}
	
	public Varargs build()
	{
		return LuaValue.varargsOf ( argsList.toArray ( LuaValue.NOVALS ) );
	}
}

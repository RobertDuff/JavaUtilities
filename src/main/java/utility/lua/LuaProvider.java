package utility.lua;

import java.util.HashMap;
import java.util.Map;

import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.JsePlatform;

public class LuaProvider 
{
	private static Map<String,Globals> luaMap = new HashMap<>();
	
	public static Globals lua()
	{
	    return lua ( null );
	}
	
	public static Globals lua ( String key )
	{
		if ( !luaMap.containsKey ( key ) )
			luaMap.put ( key, makeGlobals() );
		
		return luaMap.get ( key );
	}
	
	public static Globals newLua()
	{
	    return makeGlobals();
	}
	
	private static Globals makeGlobals()
	{
	    Globals globals = JsePlatform.debugGlobals ();
	    
	    // Create the Missing unpack() function
	    globals.load ( "function unpack (t,i) i=i or 1 if t[i]~=nil then return t[i],unpack(t,i+1) end end" ).call();
	    
	    return globals;
	}
}

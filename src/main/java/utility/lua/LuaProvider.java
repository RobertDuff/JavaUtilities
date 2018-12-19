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
			luaMap.put ( key, JsePlatform.debugGlobals() );
		
		return luaMap.get ( key );
	}
}

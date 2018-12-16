package utility.lua;

import java.util.HashMap;
import java.util.Map;

import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.JsePlatform;

public class LuaProvider 
{
	private static Globals luaSingleton;
	private static Map<String,Globals> luaMap = new HashMap<>();
	
	public static Globals lua()
	{
		if ( luaSingleton == null )
			luaSingleton = JsePlatform.standardGlobals();
		
		return luaSingleton;
	}
	
	public static Globals lua ( String key )
	{
		if ( !luaMap.containsKey ( key ) )
			luaMap.put ( key, JsePlatform.standardGlobals() );
		
		return luaMap.get ( key );
	}
}

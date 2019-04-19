package utility.lua;

import org.junit.jupiter.api.Test;
import org.luaj.vm2.Globals;

public class LuaProviderTest
{
    @Test
    public void testUnpack()
    {
        Globals lua = LuaProvider.newLua ();
        
        lua.load ( "function testUnpack() local x = { 1, 2, 3 } a,b,c = unpack(x) print ( a, b, c ) end" ).call ();
        lua.load ( "testUnpack()" ).call ();
    }
}

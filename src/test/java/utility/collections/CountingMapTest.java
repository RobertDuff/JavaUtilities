package utility.collections;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CountingMapTest
{
    @Test
    public void testPut ()
    {
        CountingMap<String> m = new CountingMap<>();
        
        m.put ( "One" );
        m.put ( "Two" );
        m.put ( "Two" );
        
        assertAll (
                "Put",
                () -> assertEquals ( 2, m.keySet ().size (), "Key Set Size" ),
                () -> assertEquals ( 1, m.get ( "One" ), "One" ),
                () -> assertEquals ( 2, m.get ( "Two" ), "Two" ),
                () -> assertNull ( m.get ( "Three" ), "Three-Null" ),
                () -> assertEquals ( 1, m.count ( "One" ) , "One-Count" ),
                () -> assertEquals ( 2, m.count ( "Two" ), "Two-Count" ),
                () -> assertEquals ( 0, m.count ( "Three" ), "Three-Count" )
                );
    }
    
    @Test
    public void testPutAll()
    {
        CountingMap<String> m = new CountingMap<>();
        CountingMap<String> a = new CountingMap<>();
        
        m.put ( "A" );
        m.put ( "B", 3 );
        
        a.put ( "B", 2 );
        a.put ( "C" );
        
        m.putAll ( a );
        
        
        assertAll (
                "PutAll",
                () -> assertEquals ( 3, m.keySet ().size (), "Key Set Size" ),
                () -> assertEquals ( 1, m.get ( "A" ), "A" ),
                () -> assertEquals ( 5, m.get ( "B" ), "B" ),
                () -> assertEquals ( 1, m.get ( "C" ), "C" )
                );
    }
}

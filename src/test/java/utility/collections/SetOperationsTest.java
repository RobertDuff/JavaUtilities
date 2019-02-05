package utility.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class SetOperationsTest
{
    @Test
    public void testFindOne ()
    {
        String a = "A";
        String b = "B";
        String c = "C";
        
        Set<String> set = new TreeSet<> ();
        
        set.add ( a );
        set.add ( b );
        set.add ( c );
        
        assertEquals ( a, SetOperations.findAny ( set, s -> s.equals ( "A" ) ) );
    }
    
    @Test
    public void testFindNone ()
    {
        String a = "A";
        String b = "B";
        String c = "C";
        
        Set<String> set = new TreeSet<> ();
        
        set.add ( a );
        set.add ( b );
        set.add ( c );
        
        assertNull ( SetOperations.findAny ( set, s -> s.equals ( "D" ) ) );
    }
    
    @Test
    public void testSubSetAll ()
    {
        String a = "A";
        String b = "B";
        String c = "C";
        
        Set<String> set = new TreeSet<> ();
        
        set.add ( a );
        set.add ( b );
        set.add ( c );
        
        Set<String> subset = SetOperations.subSet ( set, s -> true );
        
        assertEquals ( 3, subset.size () );
        
        assertTrue ( subset.contains ( a ) );
        assertTrue ( subset.contains ( b ) );
        assertTrue ( subset.contains ( c ) );
    }
    
    @Test
    public void testSubSetSome ()
    {
        String a = "A";
        String b = "B";
        String c = "C";
        
        Set<String> set = new TreeSet<> ();
        
        set.add ( a );
        set.add ( b );
        set.add ( c );
        
        Set<String> subset = SetOperations.subSet ( set, s -> s.equals ( "B" ) );
        
        assertEquals ( 1, subset.size () );
        
        assertFalse ( subset.contains ( a ) );
        assertTrue ( subset.contains ( b ) );
        assertFalse ( subset.contains ( c ) );
    }
    
    @Test
    public void testSubSetNone ()
    {
        String a = "A";
        String b = "B";
        String c = "C";
        
        Set<String> set = new TreeSet<> ();
        
        set.add ( a );
        set.add ( b );
        set.add ( c );
        
        Set<String> subset = SetOperations.subSet ( set, s -> s.equals ( "D" ) );
        
        assertEquals ( 0, subset.size () );
    }
}

package utility.collections;

import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        
        Assertions.assertEquals ( a, SetOperations.findAny ( set, s -> s.equals ( "A" ) ) );
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
        
        Assertions.assertNull ( SetOperations.findAny ( set, s -> s.equals ( "D" ) ) );
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
        
        Assertions.assertEquals ( 3, subset.size () );
        
        Assertions.assertTrue ( subset.contains ( a ) );
        Assertions.assertTrue ( subset.contains ( b ) );
        Assertions.assertTrue ( subset.contains ( c ) );
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
        
        Assertions.assertEquals ( 1, subset.size () );
        
        Assertions.assertFalse ( subset.contains ( a ) );
        Assertions.assertTrue ( subset.contains ( b ) );
        Assertions.assertFalse ( subset.contains ( c ) );
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
        
        Assertions.assertEquals ( 0, subset.size () );
    }
}

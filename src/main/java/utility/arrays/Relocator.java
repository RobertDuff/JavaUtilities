package utility.arrays;

import java.util.List;

/**
 * This Class contains static methods to move elements of Lists and Arrays.
 * @author Rob Duff
 *
 */
public final class Relocator
{
    private Relocator ()
    {}
    
    /**
     * This method will move an element of a {@code List} from one index to another
     * 
     * @param list The {@code List} to modify.
     * @param from The original index of the element to be moved.
     * @param to The destination index of the element to be moved.
     */
    public static <T> void relocate ( List<T> list, int from, int to )
    {
        // Down
        if ( from < to )
        {
            T x = list.get ( from );
            list.remove ( from );
            list.add ( to, x );
        }
        // Up
        else if ( from > to )
        {
            T x = list.get ( from );
            list.remove ( from );
            list.add ( to, x );
        }
    }

    /**
     * This method will move an element of a generic array from one index to another
     * 
     * @param array The generic array to modify.
     * @param from The original index of the element to be moved.
     * @param to The destination index of the element to be moved.
     */
    public static <T> void relocate ( T[] array, int from, int to )
    {
        // Down
        if ( from < to )
        {
            T x = array[ from ];
            
            for ( int i = from + 1; i <= to; i++ )
                array[ i-1 ] = array[ i ];
            
            array[ to ] = x;
        }
        // Up
        else if ( from > to )
        {
            T x = array[ from ];
            
            for ( int i = from-1; i >= to; i-- )
                array[ i+1 ] = array[ i ];
            
            array[ to ] = x;
        }
    }

    /**
     * This method will move an element of a boolean array from one index to another
     * 
     * @param array The boolean array to modify.
     * @param from The original index of the element to be moved.
     * @param to The destination index of the element to be moved.
     */
    public static void relocate ( boolean[] array, int from, int to )
    {
        // Down
        if ( from < to )
        {
            boolean x = array[ from ];
            
            for ( int i = from + 1; i <= to; i++ )
                array[ i-1 ] = array[ i ];
            
            array[ to ] = x;
        }
        // Up
        else if ( from > to )
        {
            boolean x = array[ from ];
            
            for ( int i = from-1; i >= to; i-- )
                array[ i+1 ] = array[ i ];
            
            array[ to ] = x;
        }
    }

    /**
     * This method will move an element of a char array from one index to another
     * 
     * @param array The char array to modify.
     * @param from The original index of the element to be moved.
     * @param to The destination index of the element to be moved.
     */
    public static void relocate ( char[] array, int from, int to )
    {
        // Down
        if ( from < to )
        {
            char x = array[ from ];
            
            for ( int i = from + 1; i <= to; i++ )
                array[ i-1 ] = array[ i ];
            
            array[ to ] = x;
        }
        // Up
        else if ( from > to )
        {
            char x = array[ from ];
            
            for ( int i = from-1; i >= to; i-- )
                array[ i+1 ] = array[ i ];
            
            array[ to ] = x;
        }
    }

    /**
     * This method will move an element of a byte array from one index to another
     * 
     * @param array The byte array to modify.
     * @param from The original index of the element to be moved.
     * @param to The destination index of the element to be moved.
     */
    public static void relocate ( byte[] array, int from, int to )
    {
        // Down
        if ( from < to )
        {
            byte x = array[ from ];
            
            for ( int i = from + 1; i <= to; i++ )
                array[ i-1 ] = array[ i ];
            
            array[ to ] = x;
        }
        // Up
        else if ( from > to )
        {
            byte x = array[ from ];
            
            for ( int i = from-1; i >= to; i-- )
                array[ i+1 ] = array[ i ];
            
            array[ to ] = x;
        }
    }

    /**
     * This method will move an element of a short array from one index to another
     * 
     * @param array The short array to modify.
     * @param from The original index of the element to be moved.
     * @param to The destination index of the element to be moved.
     */
    public static void relocate ( short[] array, int from, int to )
    {
        // Down
        if ( from < to )
        {
            short x = array[ from ];
            
            for ( int i = from + 1; i <= to; i++ )
                array[ i-1 ] = array[ i ];
            
            array[ to ] = x;
        }
        // Up
        else if ( from > to )
        {
            short x = array[ from ];
            
            for ( int i = from-1; i >= to; i-- )
                array[ i+1 ] = array[ i ];
            
            array[ to ] = x;
        }
    }

    /**
     * This method will move an element of a int array from one index to another
     * 
     * @param array The int array to modify.
     * @param from The original index of the element to be moved.
     * @param to The destination index of the element to be moved.
     */
    public static void relocate ( int[] array, int from, int to )
    {
        // Down
        if ( from < to )
        {
            int x = array[ from ];
            
            for ( int i = from + 1; i <= to; i++ )
                array[ i-1 ] = array[ i ];
            
            array[ to ] = x;
        }
        // Up
        else if ( from > to )
        {
            int x = array[ from ];
            
            for ( int i = from-1; i >= to; i-- )
                array[ i+1 ] = array[ i ];
            
            array[ to ] = x;
        }
    }

    /**
     * This method will move an element of a long array from one index to another
     * 
     * @param array The long array to modify.
     * @param from The original index of the element to be moved.
     * @param to The destination index of the element to be moved.
     */
    public static void relocate ( long[] array, int from, int to )
    {
        // Down
        if ( from < to )
        {
            long x = array[ from ];
            
            for ( int i = from + 1; i <= to; i++ )
                array[ i-1 ] = array[ i ];
            
            array[ to ] = x;
        }
        // Up
        else if ( from > to )
        {
            long x = array[ from ];
            
            for ( int i = from-1; i >= to; i-- )
                array[ i+1 ] = array[ i ];
            
            array[ to ] = x;
        }
    }

    /**
     * This method will move an element of a float array from one index to another
     * 
     * @param array The float array to modify.
     * @param from The original index of the element to be moved.
     * @param to The destination index of the element to be moved.
     */
    public static void relocate ( float[] array, int from, int to )
    {
        // Down
        if ( from < to )
        {
            float x = array[ from ];
            
            for ( int i = from + 1; i <= to; i++ )
                array[ i-1 ] = array[ i ];
            
            array[ to ] = x;
        }
        // Up
        else if ( from > to )
        {
            float x = array[ from ];
            
            for ( int i = from-1; i >= to; i-- )
                array[ i+1 ] = array[ i ];
            
            array[ to ] = x;
        }
    }

    /**
     * This method will move an element of a double array from one index to another
     * 
     * @param array The double array to modify.
     * @param from The original index of the element to be moved.
     * @param to The destination index of the element to be moved.
     */
    public static void relocate ( double[] array, int from, int to )
    {
        // Down
        if ( from < to )
        {
            double x = array[ from ];
            
            for ( int i = from + 1; i <= to; i++ )
                array[ i-1 ] = array[ i ];
            
            array[ to ] = x;
        }
        // Up
        else if ( from > to )
        {
            double x = array[ from ];
            
            for ( int i = from-1; i >= to; i-- )
                array[ i+1 ] = array[ i ];
            
            array[ to ] = x;
        }
    }
}

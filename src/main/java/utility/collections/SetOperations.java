package utility.collections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.function.Predicate;

public class SetOperations
{
    private SetOperations () {}

    public static <T> T findAny ( Set<T> set, Predicate<T> criteria )
    {
        for ( T t : set )
            if ( criteria.test ( t ) )
                return t;
        
        return null;
    }
    
    public static <T> Set<T> subSet ( Set<T> set, Predicate<T> criteria )
    {
        try
        {
            @SuppressWarnings ( "unchecked" )
            Set<T> subset = set.getClass ().getConstructor ().newInstance ();
            
            for ( T t : set )
                if ( criteria.test ( t ) )
                    subset.add ( t );
            
            return subset;
        }
        catch ( InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e )
        {
            e.printStackTrace();
            return null;
        }
    }
}

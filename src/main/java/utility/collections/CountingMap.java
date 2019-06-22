package utility.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CountingMap<K> implements Map<K,Integer>
{
    private Map<K,Integer> counts = new HashMap<>();
    
    public CountingMap ()
    {
    }

    @Override
    public int size ()
    {
        return counts.size ();
    }

    @Override
    public boolean isEmpty ()
    {
        return counts.isEmpty ();
    }

    @Override
    public boolean containsKey ( Object key )
    {
        return counts.containsKey ( key );
    }

    @Override
    public boolean containsValue ( Object value )
    {
        return counts.containsValue ( value );
    }

    @Override
    public Integer get ( Object key )
    {
        return counts.get ( key );
    }

    public Integer put ( K key )
    {
        return put ( key, 1 );
    }
    
    public Integer count ( K key )
    {
        if ( containsKey ( key ) )
            return get ( key );
        
        return 0;
    }
    
    @Override
    public Integer put ( K key, Integer value )
    {
        int count = counts.containsKey ( key )? counts.get ( key ) : 0;
        count += value;
        
        return counts.put ( key, count );
    }

    @Override
    public Integer remove ( Object key )
    {
        return counts.remove ( key );
    }

    @Override
    public void putAll ( Map<? extends K, ? extends Integer> m )
    {   
        for ( Map.Entry<? extends K,? extends Integer> entry : m.entrySet () )
            put ( entry.getKey (), entry.getValue () );
    }

    @Override
    public void clear ()
    {
        counts.clear ();
    }

    @Override
    public Set<K> keySet ()
    {
        return counts.keySet ();
    }

    @Override
    public Collection<Integer> values ()
    {
        return counts.values ();
    }

    @Override
    public Set<Entry<K, Integer>> entrySet ()
    {
        return counts.entrySet ();
    }
}

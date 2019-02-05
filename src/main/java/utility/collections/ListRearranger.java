package utility.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.xml.ws.Holder;

public class ListRearranger<T> implements Comparator<T>
{
    protected List<T> originalList;
    protected int[] order; 
    
    protected ListRearranger ( List<T> l )
    {
        originalList = new ArrayList<>( l );
    }
    
    public ListRearranger ( List<T> l, Iterable<? extends Number> o )
    {
        this ( l );
        order ( o );
    }
    
    public ListRearranger ( List<T> l, int... o )
    {
        this ( l );
        order ( o );
    }
    
    public int[] order()
    {
        return order;
    }
    
    public void order ( int... o )
    {
        int[] copyForVerification = Arrays.copyOf ( o, o.length );
        Arrays.sort ( copyForVerification );
        
        if ( o.length != originalList.size () )
            throw new IllegalArgumentException ( "Order Specification contains " + o.length + " Indecies, But Target List contains " + originalList.size () + " Items" );
        
        for ( int n=0; n<copyForVerification.length; n++ )
            if ( copyForVerification[ n ] != n )
                throw new IllegalArgumentException ( "Order does not contain every index exactly once: " + Arrays.toString ( o ) );
        
        order = o;
    }
    
    public void order ( Iterable<? extends Number> o )
    {
        order ( StreamSupport.stream ( o.spliterator (), false ).mapToInt ( i -> i.intValue () ).toArray () );
    }
    
    @Override
    public int compare ( T o1, T o2 )
    {
        int a = indexOf ( o1 );
        int b = indexOf ( o2 );
        
        return order[ a ] - order[ b ];
    }     
    
    protected int indexOf ( T t )
    {
        for ( int n=0; n < originalList.size (); n++ )
            if ( originalList.get ( n ) == t )
                return n;
        
        return -1;
    }
    
    //
    // Static Functions/Classes
    //
    
    public static <T> Comparator<T> reverse ( List<T> list )
    { 
        Holder<Integer> count = new Holder<>( list.size () );
        return new ListRearranger<> ( list, Stream.generate ( () -> --count.value ).limit ( list.size () ).mapToInt ( i -> i ).toArray () );
    }
    
    public static <T> Comparator<T> rotate ( List<T> list, int shift )
    { 
        Holder<Integer> pos = new Holder<>( shift >= 0? list.size () - shift - 1 : ( -shift ) - 1 );
        return new ListRearranger<> ( list, Stream.generate ( () -> pos.value = ( pos.value + 1 ) % list.size () ).limit ( list.size () ).mapToInt ( i -> i ).toArray () );
    }
    
    public static <T> Comparator<T> move ( List<T> list, int from, int to )
    {
        Holder<Integer> pos = new Holder<> ( -1 );
        Holder<Integer> idx = new Holder<> ( 0 );
        
        if ( from == to )
            return new ListRearranger<> ( list, Stream.generate ( () -> idx.value++ ).limit ( list.size () ).mapToInt ( i -> i ).toArray () );

        if ( to > from )
            return new ListRearranger<> ( list, Stream.generate ( () -> 
            { 
                pos.value++;
                
                if ( pos.value == from )
                    return to;
                
                if ( pos.value == to )
                {
                    int index = idx.value;
                    idx.value += 2;
                    return index;
                }
                
                return idx.value++;
            } ).limit ( list.size () ).mapToInt ( i -> i ).toArray () );
        
        return new ListRearranger<> ( list, Stream.generate ( () -> 
        {
            pos.value++;
            
            if ( pos.value == to )
                idx.value++;
            
            if ( pos.value == from )
                return to;
            
            return idx.value++;
        } ).limit ( list.size () ).mapToInt ( i -> i ).toArray() );
    }
    
    public static <T> Comparator<T> swap ( List<T> list, int a, int b )
    {
        Holder<Integer> pos = new Holder<> ( -1 );
        
        return new ListRearranger<> ( list, Stream.generate ( () -> 
        {
            pos.value++;
            
            if ( pos.value == a ) return b;
            if ( pos.value == b ) return a;
            
            return pos.value;
        } ).limit ( list.size () ).mapToInt ( i -> i ).toArray () );
    }
}
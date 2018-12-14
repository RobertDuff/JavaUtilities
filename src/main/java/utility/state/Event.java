package utility.state;

import java.util.HashMap;
import java.util.Map;

/**
 * A Class representing a State Machine Triggering Event.
 * @author Rob Duff
 *
 */
public class Event
{
    protected final String name;
    protected Map<Object,Object> data = new HashMap<>();
    
    /**
     * Construct a new {@code Event}
     * @param name The name of the new {@code Event}.
     */
    public Event ( String name )
    {
        this ( name, null );
    }
    
    /**
     * Construct a new {@code Event}
     * @param name The name of the new {@code Event}.
     * @param data A {@code Map} of data values to attach to this {@code Event}.
     */
    public Event ( String name, Map<Object,Object> data )
    {
        if ( name == null || name.isEmpty() )
            throw new IllegalArgumentException ( "Event Name must not be null or blank." );
        
        this.name = name;
        
        if ( data != null )
            this.data.putAll ( data );
    }
    
    /**
     * @return A {@code Map} of user defined data attached to this {@code Event}.
     */
    public final Map<Object,Object> data()
    {
        return data;
    }
    
    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        
        result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
        
        return result;
    }

    @Override
    public boolean equals ( Object obj )
    {
        if ( this == obj )
            return true;
        
        if ( obj == null )
            return false;
        
        if ( obj instanceof String )
            return name.equals ( obj );
        
        if ( getClass () != obj.getClass () )
            return false;
        
        Event other = ( Event ) obj;
        
        if ( name == null )
        {
            if ( other.name != null )
                return false;
        }
        else if ( !name.equals ( other.name ) )
            return false;
        
        return true;
    }
    
    /**
     * Predefined Event generated upon State Machine Initialization. (For Internal Use Only!)
     */
    static final Event INITIALIZE_EVENT  = new Event ( "STATE_MODEL_INITIALIZATION_EVENT" );
    
    /**
     * Predefined Event generated upon State Machine Initialization. (For Internal Use Only!)
     */
    static final Event TERMINATE_EVENT   = new Event ( "STATE_MODEL_TERMINATION_EVENT" );
    
    /**
     * Predefined Event generated upon a {@link Choice} {@link State}'s {@link Predicate} returning {@code true}. (For Internal Use Only!)
     */
    static final Event TRUE_EVENT        = new Event ( "STATE_MODEL_CHOICE_PREDICATE_TRUE_EVENT" );
    
    /**
     * Predefined Event generated upon a {@link Choice} {@link State}'s {@link Predicate} returning {@code false}. (For Internal Use Only!)
     */
    static final Event FALSE_EVENT       = new Event ( "STATE_MODEL_CHOICE_PREDICATE_FALSE_EVENT" );
}

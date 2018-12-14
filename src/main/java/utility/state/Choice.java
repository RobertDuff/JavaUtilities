package utility.state;

import java.util.Collections;
import java.util.function.Predicate;

public final class Choice extends State
{
    protected Predicate<Event> predicate;
    
    public Choice ( Predicate<Event> predicate, Reaction thenReaction, Reaction elseReaction )
    {
        this ( "STATE_MODE_CHOICE_STATE", predicate, thenReaction, elseReaction );
    }
    
    public Choice ( String name, Predicate<Event> predicate, Reaction thenReaction, Reaction elseReaction )
    {
        super ( name );  

        if ( predicate == null )
            throw new IllegalArgumentException ( "Predicate must not be null" );
        
        if ( thenReaction.guard != null )
            throw new IllegalArgumentException ( "thenReaction may not have a guard" );
        
        if ( thenReaction.destination == null )
            throw new IllegalArgumentException ( "thenReaction must specify a destination State" );
        
        if ( elseReaction.guard != null )
            throw new IllegalArgumentException ( "elseReaction may not have a guard" );
        
        if ( thenReaction.destination == null )
            throw new IllegalArgumentException ( "elseReaction must specify a destination State" );
        
        this.predicate = predicate;
        
        reactions.put ( Event.TRUE_EVENT,  thenReaction );
        reactions.put ( Event.FALSE_EVENT, elseReaction );
        
        setEntry ( event -> 
        {
            if ( predicate.test ( event ) )
                return Collections.singletonList ( Event.TRUE_EVENT );
            
            return Collections.singletonList ( Event.FALSE_EVENT );
        } );
    }
}
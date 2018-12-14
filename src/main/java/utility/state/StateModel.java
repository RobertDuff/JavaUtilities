package utility.state;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>
 * Class representing the currently existing {@link State} of a
 * {@link StateMachine}, or a sub {@link State} of a {@link State}.
 * </p>
 * <p>
 * {@code StateModel} objects may be either reentrant, or non-reentrant. If the
 * {@code StateModel} is non-reentrant, then it will always return to its initial
 * {@code State} when it is re-entered (such as when a parent {@code State} is
 * entered).
 * </p>
 * <p>
 * If the {@code StateModel} is reentrant, then it will re-enter the {@code State} it was in when it was last exited.
 * </p>
 * 
 * @author Rob Duff
 *
 */
public class StateModel
{
    private static final Logger logger = LogManager.getLogger ( StateModel.class );
    
    private State initialState;
    private State reentrantState;
    private State currentState;
    private boolean reentrant;
    
    /**
     * Constructs a Non-Reentrant {@code StateModel}.
     * @param initialState The initial {@link State}.  This will be the {@link State} that is returned to whenever this {@code StateModel} is re-entered.
     */
    public StateModel ( State initialState )
    {
        this ( initialState, false );
    }
    
    /**
     * Constructs a {@code StateModel}.
     * @param initialState  The initial {@link State}.  if this {@code StateModel} is non-reentrant, this will be the {@link State} that is returned to whenever this {@code StateModel} is re-entered.
     * @param reentrant TRUE if the {@code StateModel} may be re-entered
     */
    public StateModel ( State initialState, boolean reentrant )
    {
        this.initialState = initialState;
        this.reentrantState = this.initialState;
        this.reentrant = reentrant;
    }

    /**
     * @return The currently active {@link State}.
     */
    public State currentState()
    {
        return currentState;
    }
    
    /**
     * Enters the {@code StateModel}.  If this {@code StateModel} is reentrant, then it will enter the {@link State} it was in when it was last exited.  Otherwise, it will enter its initial {@link State}.
     * @param event The triggering {@link Event}.
     * @return A list of additional {@link Event}s triggered by the triggering {@link Event} 
     */
    public List<Event> enter ( Event event )
    {
        logger.debug ( "StateModel: Enter per Event " + event );
        
        currentState = reentrantState;
        return currentState.enter ( event );
    }
    
    /**
     * Exits the {@code StateModel}.  If this {@code StateModel} is reentrant, then it will record the current {@link State} for re-entry later.
     * @param event The triggering {@link Event}.
     * @return A list of additional {@link Event}s triggered by the triggering {@link Event} 
     */
    public List<Event> exit ( Event event )
    {
        logger.debug ( "StateModel: Exit Per Event " + event );
        
        if ( reentrant )
            reentrantState = currentState;
        
        return currentState.exit ( event );
    }
    
    public List<Event> react ( Event event )
    {
        logger.debug ( "StateModel: React to Event " + event );
        
        if ( event.equals ( Event.INITIALIZE_EVENT ) )
        {
            // In the case of an initialization event, do not process the exit of an existing event, or any reactions.
        	reentrantState = initialState;
            return enter ( event );
        }
        
        List<Event> newEvents = new ArrayList<>();
        
        // Allow Sub-States to react to the Event first.
        newEvents.addAll ( currentState.react ( event ) );
        
        Reaction reaction;
        
        if ( event.equals ( Event.TERMINATE_EVENT ) )
            reaction = Reaction.TERMINATE_REACTION;
        else
            reaction = currentState.reactions.get ( event );
        
        if ( reaction != null )
        {
            if ( reaction.isAllowed ( event ) )
            {
                if ( reaction.destination() != null )
                    newEvents.addAll ( currentState.exit ( event ) );
                
                newEvents.addAll ( reaction.react ( event ) );
                
                if ( reaction.destination() != null )
                {
                    currentState = reaction.destination();
                    newEvents.addAll ( currentState.enter ( event ) );
                }
            }
        }
        
        return newEvents;
    }
    
    @Override
    public String toString()
    {
    	return currentState.toString();
    }
}

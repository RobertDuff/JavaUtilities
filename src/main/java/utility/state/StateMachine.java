package utility.state;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utility.state.context.StateMachineExecutionContext;
import utility.state.context.StaticStateMachineExecutionContext;

/**
 * Class representing a top-level Finite State Machine.
 * @author Rob Duff
 *
 */
public class StateMachine
{
    private static final Logger logger = LogManager.getLogger ( StateMachine.class );
    
    private StateModel model;
    private StateMachineExecutionContext executionContext;
    
    /**
     * Construct a {@code StateMachine}.
     * @param model The top-level {@code StateModel}, which will containt the 
     */
    public StateMachine ( StateModel model )
    {
        this ( model, new StaticStateMachineExecutionContext() );
    }
    
    public StateMachine ( StateModel model, StateMachineExecutionContext context )
    {
    	executionContext = context;
    	executionContext.setModel ( model );
        this.model = model;
        init();
    }
    
    /**
     * Initialize the Top-Level {@link StateModel}, as well as all sub {@link StateModel}s.
     */
    public void init()
    {
        logger.debug ( "StateMachine: Initialize" );
        react ( Event.INITIALIZE_EVENT );
    }
    
    /**
     * Allow the {@link StateModel} to react to an {@link Event}.
     * @param event The trigger {@link Event}.
     */
    public void react ( Event event )
    {
        logger.debug ( "StateMachine: Reacting to Event " + event );
        executionContext.process ( event );
    }
    
    /**
     * Cause the {@link StateModel} to enter a terminated state, from which there are no reactions.
     */
    public void terminate()
    {        
        logger.debug ( "StateMachine: Terminating" );
        react ( Event.TERMINATE_EVENT );
    }

    /**
     * @return The current {@link State} of the {@link StateModel}.
     */
    public State currentState()
    {
        return model.currentState();
    }
}

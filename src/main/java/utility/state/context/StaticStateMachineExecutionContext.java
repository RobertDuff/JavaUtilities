package utility.state.context;

import java.util.ArrayDeque;
import java.util.Queue;

import utility.state.Event;


public class StaticStateMachineExecutionContext extends StateMachineExecutionContext
{
    private Queue<Event> eventQueue = new ArrayDeque<>();
        
    @Override
    public void process ( Event triggerEvent )
    {
        eventQueue.add ( triggerEvent );
        
        while ( !eventQueue.isEmpty() )
        {
            Event event = eventQueue.remove();
            
            eventQueue.addAll ( model.react ( event ) );
        }            
    }
}

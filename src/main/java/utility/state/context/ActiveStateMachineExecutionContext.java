package utility.state.context;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import utility.state.Event;

public class ActiveStateMachineExecutionContext extends StateMachineExecutionContext
{
	private Queue<Event> eventQueue = new LinkedBlockingQueue<Event>();
	
	public ActiveStateMachineExecutionContext()
	{
		Thread stateThread = new Thread()
		{
			@Override
			public void run()
			{
		        while ( true )
		        {
		            Event event = eventQueue.remove();
		            
		            eventQueue.addAll ( model.react ( event ) );
		        }            
			}
		};
		
		stateThread.setDaemon ( true );
		stateThread.start();
	}

	@Override
	public void process ( Event event )
	{
		eventQueue.add ( event );
	}
}

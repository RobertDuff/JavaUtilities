package utility.state.context;

import utility.state.Event;
import utility.state.StateModel;


public abstract class StateMachineExecutionContext
{
	protected StateModel model;

	public void setModel ( StateModel model )
	{
		this.model = model;
	}
	
    public abstract void process ( Event event );
}

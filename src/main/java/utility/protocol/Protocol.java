package utility.protocol;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * A Generic Communication Protocol Stack
 * <p>
 * A {@code Protocol} consists of a Data Transmission Chain (TX Chain) and a
 * Data Receive Chain (RX Chain).
 * </p>
 * <p>
 * The TX chain consists of a sequence of {@link Function}s, each of which
 * is responsible for a step in the data transformation/transmission process.
 * The final {@code Function} in the TX chain is responsible for completing
 * the data transformation/transmission process.
 * </p>
 * <p>
 * The RX chain consists of a sequence of {@code Function}s, (most often
 * the inverse operations as those in the TX chain, and in reverse order).
 * </p>
 * 
 * @author Rob
 *
 */
public class Protocol
{
	private List<Appliance>					txChain					= new LinkedList<> ();
	private List<Appliance>					rxChain					= new LinkedList<> ();
	private ObjectProperty<Object>			receivedDataProperty	= new SimpleObjectProperty<> ();
	private List<ProtocolExceptionListener>	exceptionListeners		= new LinkedList<> ();

	/**
	 * Constructs a new {@code Protocol}
	 */
	public Protocol()
	{}

	/**
	 * @param listener The listener to register to receive Exceptions which occur during the processing of the {@code Protocol}'s RX Chain.
	 */
	public void registerExceptionListener ( ProtocolExceptionListener listener )
	{
		exceptionListeners.add ( listener );
	}

	/**
	 * @param listener The listener to remove.
	 */
	public void unregisterExceptionListener ( ProtocolExceptionListener listener )
	{
		exceptionListeners.remove ( listener );
	}

	public List<Appliance> getTxChain()
	{
		return txChain;
	}

	public List<Appliance> getRxChain()
	{
		return rxChain;
	}

	/**
	 * @return A {@code Runnable} which can be executed by an {@code Executor} or {@code Thread} to process the RX chain.
	 */
	public Runnable getRxChainProcessor()
	{
		return new Runnable()
		{
			@Override
			public void run()
			{
				while ( true )
				{
					Object data = null;

					try
					{
						for ( Appliance operator : rxChain )
							if ( ( data = operator.apply ( data ) ) == null )
								break;

						if ( data != null )
						{
							synchronized ( receivedDataProperty )
							{
								receivedDataProperty.setValue ( data );
								receivedDataProperty.notifyAll();
							}
						}
					}				

					catch ( Exception e )
					{
						for ( ProtocolExceptionListener listener : exceptionListeners )
							listener.handleProtocolException ( e );
					}
				}
			}
		};
	}

	/**
	 * Sends Data via the TX chain.
	 * @param data The data to transmit/process.
	 * @throws Exception Allows throw of application exceptions.
	 */
	public synchronized void sendAsync ( Object data ) throws Exception
	{
		for ( Appliance operator : txChain )
			if ( ( data = operator.apply ( data ) ) == null )
				break;
	}

	/**
	 * Sends Data via the TX chain, and then wait's for a response.
	 * @param data The data to transmit/process
	 * @return The response.
	 * @throws Exception Allows throw of application exceptions.
	 */
	public synchronized Object send ( Object data ) throws Exception
	{
		sendAsync ( data );

		boolean done = false;

		synchronized ( receivedDataProperty )
		{
			while ( !done )
			{
				try
				{
					receivedDataProperty.wait();
					done = true;
				}
				catch ( InterruptedException e )
				{
					// Try Again.
				}
			}
		}

		return receivedDataProperty.getValue();
	}

	/**
	 * Protocol Exceptions
	 * @author Rob
	 */
	public static class ProtocolException extends Exception
	{
		/**
		 * Default Serializable ID
		 */
		private static final long serialVersionUID = 1L;

		public ProtocolException()
		{}
	
		public ProtocolException ( String message )
		{
			super ( message );
		}
	
		public ProtocolException ( Throwable cause )
		{
			super ( cause );
		}
	
		public ProtocolException ( String message, Throwable cause )
		{
			super ( message, cause );
		}
	
		public ProtocolException ( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace )
		{
			super ( message, cause, enableSuppression, writableStackTrace );
		}
	
	}

	/**
	 *  A Listener for Exceptions thrown during the processing the {@code Protocol}'s RX chain.
	 * @author Rob
	 *
	 */
	public static interface ProtocolExceptionListener
	{ 
		/**
		 * Handles Exceptions thrown during the processing of the {@code Protocol}'s RX chain.
		 * @param e The exception that occurred.
		 */
		void handleProtocolException ( Exception e );
	}
	
	
}

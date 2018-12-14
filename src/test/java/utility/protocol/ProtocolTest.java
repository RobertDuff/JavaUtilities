package utility.protocol;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.junit.Test;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProtocolTest
{
	public static Executor executor = Executors.newCachedThreadPool();;
	
	@Test
	public void testAsync() throws Exception
	{
		StringProperty sp = new SimpleStringProperty ();
		
		Protocol protocol = new Protocol();

		protocol.getTxChain().add ( s -> ( ( String ) s ).toUpperCase() );
		protocol.getTxChain().add ( s -> { sp.setValue ( ( String ) s ); return null; } );
		
		protocol.sendAsync ( "hello" );
		assertEquals ( "HELLO", sp.getValue() );
	}
	
	@Test
	public void testSync() throws Exception
	{
		Protocol protocol = new Protocol();
		Bouncer bouncer = new Bouncer();
		
		protocol.getRxChain().add ( bouncer::produce );
		protocol.getRxChain().add ( s -> s + ".Response" );
		
		executor.execute ( protocol.getRxChainProcessor() );
				
		Thread.sleep ( 100 );
		
		protocol.getTxChain().add ( s -> ( ( String ) s ).toUpperCase() );
		protocol.getTxChain().add ( bouncer::consume );
		
		String data = "hello";
		
		String response = ( String ) protocol.send ( data );
		
		assertEquals ( "HELLO.Response", response );
	}
	
	@Test
	public void testUnsolicited() throws InterruptedException
	{
		StringProperty sp = new SimpleStringProperty();
		Protocol protocol = new Protocol();
		
		protocol.getRxChain().add ( o -> "Incoming" );
		protocol.getRxChain().add ( s -> s + ".received" );
		protocol.getRxChain().add ( s -> { sp.setValue ( ( String ) s ); return s; } );
		
		executor.execute ( protocol.getRxChainProcessor() );
		
		Thread.sleep ( 100 );
		
		assertEquals ( "Incoming.received", sp.getValue() );
	}
	
	private static class Bouncer
	{
		private Object data;
		
		public Object consume ( Object data )
		{
			synchronized ( this )
			{
				this.data = data;
				notifyAll();
			}
			
			return null;
		}
		
		public Object produce ( Object shouldBeNull )
		{
			synchronized ( this )
			{
				boolean done = false;
				
				while ( !done )
				{
					try
					{
						wait();
						done = true;
					}
					catch ( InterruptedException e )
					{
						// Try Again
					}
				}
			}
			
			return data;
		}
	}
}

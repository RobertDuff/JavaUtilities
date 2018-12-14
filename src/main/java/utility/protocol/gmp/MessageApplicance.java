package utility.protocol.gmp;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import utility.protocol.Protocol.ProtocolException;
import utility.protocol.gmp.messages.Header;
import utility.protocol.gmp.messages.Message;

public class MessageApplicance
{
	//TODO: Use this
	@SuppressWarnings ("unused" )
	private MessageFactory factory;
	private final HeaderAppliance headerAppliance = new HeaderAppliance();
	
	public MessageApplicance ( MessageFactory factory )
	{
		if ( factory == null )
			throw new IllegalArgumentException ( "Factory may not be null" );
		
		this.factory = factory;
	}

	public Object encodeMessage ( Object obj ) throws ProtocolException
	{
		if ( ! ( obj instanceof Message ) )
			throw new IllegalArgumentException ( "Argument must extend Message" );
		
		Message msg = ( Message ) obj;
		
		byte[] header = ( byte[] ) headerAppliance.encodeHeader ( msg.getHeader () );		
		msg.encodePayload();
		
		ByteBuffer buffer = ByteBuffer.allocate ( header.length + msg.getPayload().length );
		buffer.order ( ByteOrder.BIG_ENDIAN );
		
		buffer.put ( header );
		buffer.put ( msg.getPayload() );
		
		return buffer.array();
	}

	//TODO: Remove This suppression when function is completed
	@SuppressWarnings ("unused" )
	public Message decodeMessage ( Object obj ) throws ProtocolException
	{
		if ( ! ( obj instanceof byte[] ) )
			throw new IllegalArgumentException ( "Argument must be a byte[]" );
		
		byte[] data = ( byte[] ) obj;
		ByteBuffer buffer = ByteBuffer.wrap ( data );
		buffer.order ( ByteOrder.BIG_ENDIAN );
		
		//TODO: Finish this method
		Header header = headerAppliance.decodeHeader ( buffer );
		return null;
	}
}

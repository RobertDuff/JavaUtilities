package utility.protocol.gmp;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import utility.protocol.Protocol.ProtocolException;
import utility.protocol.gmp.messages.Header;
import utility.protocol.gmp.messages.MessageCategory;
import utility.protocol.gmp.messages.MessageType;

public class HeaderAppliance
{
	public HeaderAppliance()
	{}

	public byte[] encodeHeader ( Header header ) throws ProtocolException
	{		
		ByteBuffer buffer = ByteBuffer.allocate ( 7 );
		buffer.order ( ByteOrder.BIG_ENDIAN );
		
		buffer.put ( ( byte ) ( header.isHighPriority()? 1 : 0 ) );
		
		switch ( header.getType() )
		{
			case REQUEST: buffer.put ( ( byte ) 0 ); break;
			case COMMAND: buffer.put ( ( byte ) 1 ); break;
				
			default:
				throw new ProtocolException ( "UnHandled Message Type: " + header.getType() );
		}
		
		switch ( header.getCategory() )
		{
			case CAPABILITIES:  buffer.put ( ( byte ) 0 ); break;
			case CONFIGURATION: buffer.put ( ( byte ) 1 ); break;
			case OPERATION:     buffer.put ( ( byte ) 2 ); break;
			case STATUS:        buffer.put ( ( byte ) 3 ); break;
			case NOTIFICATION:  buffer.put ( ( byte ) 4 ); break;
			case DEBUG:         buffer.put ( ( byte ) 5 ); break;
				
			default:
				throw new ProtocolException ( "UnHandled Message Category: " + header.getType() );			
		}
		
		buffer.putInt ( header.getId() );
		
		return buffer.array();
	}
	

	public Header decodeHeader ( Object data ) throws ProtocolException
	{
		if ( ! ( data instanceof ByteBuffer ) )
			throw new RuntimeException ( "Argument must be a byte[]" );
		
		Header header = new Header();
		
		ByteBuffer buffer = ( ByteBuffer ) data;
		
		header.setHighPriority ( buffer.get() == 1 );
		
		int type = buffer.get() & 0x0FF;
		
		switch ( type )
		{
			case 0: header.setType ( MessageType.REQUEST ); break;
			case 1: header.setType ( MessageType.COMMAND ); break;
				
			default:
				throw new ProtocolException ( "Unknown Message Type Value: " + type );
		}
		
		int category = buffer.get() & 0x0FF;
		
		switch ( category )
		{
			case 0: header.setCategory ( MessageCategory.CAPABILITIES  ); break;
			case 1: header.setCategory ( MessageCategory.CONFIGURATION ); break;
			case 2: header.setCategory ( MessageCategory.OPERATION     ); break;
			case 3: header.setCategory ( MessageCategory.STATUS        ); break;
			case 4: header.setCategory ( MessageCategory.NOTIFICATION  ); break;
			case 5: header.setCategory ( MessageCategory.DEBUG         ); break;
				
			default:
				throw new ProtocolException ( "Unknown Message Category Value: " + category );			
		}
		
		header.setId ( buffer.getInt() );
		
		return header;
	}
}

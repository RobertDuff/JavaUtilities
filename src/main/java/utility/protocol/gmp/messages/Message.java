package utility.protocol.gmp.messages;

import java.util.Arrays;

public abstract class Message
{
	private Header header;
	private byte[] payload;

	public Message ( Header header )
	{
		this.header = header;
	}

	public Message ( Header header, byte[] payload )
	{
		this.header  = header;
		this.payload = payload;
	}

	public Message()
	{}
	
	public abstract void encodePayload();
	public abstract void decodePayload();
	public abstract String payloadString();
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		if ( header != null )
			builder.append ( header.toString() );
		else
			builder.append ( "NULLHEADER" );
		
		builder.append ( "(" );
		builder.append ( payloadString() );
		builder.append ( ")" );
		
		return builder.toString();
	}

	public Header getHeader()
	{
		return header;
	}

	public void setHeader ( Header header )
	{
		this.header = header;
	}

	public byte[] getPayload()
	{
		return payload;
	}

	public void setPayload ( byte[] payload )
	{
		this.payload = payload;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( header == null ) ? 0 : header.hashCode () );
		result = prime * result + Arrays.hashCode ( payload );
		return result;
	}

	@Override
	public boolean equals ( Object obj )
	{
		if ( this == obj )
			return true;
		
		if ( obj == null )
			return false;
		
		if ( getClass () != obj.getClass () )
			return false;
		
		Message other = ( Message ) obj;
		
		if ( header == null )
		{
			if ( other.header != null )
				return false;
		}
		else if ( !header.equals ( other.header ) )
			return false;
		
		if ( !Arrays.equals ( payload, other.payload ) )
			return false;
		
		return true;
	}
}

package utility.protocol.gmp.messages;

public class Response extends Message
{
	private Result result;
	
	public Response()
	{}
	
	public Response ( Result result )
	{
		this.result = result;
	}
	
	public Response ( Header header )
	{
		super ( header );
	}
	
	public Response ( Header header, Result result )
	{
		super ( header );
		this.result = result;
	}
	
	public Response ( Header header, Object payload )
	{
		super ( header, (byte[]) payload );
	}
	
	public Response ( Header header, Object payload, Result result )
	{
		super ( header, (byte[]) payload );
		this.result = result;
	}

	public Result getResult()
	{
		return result;
	}

	public void setResult ( Result result )
	{
		this.result = result;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append ( "Response:" );
		builder.append ( super.toString() );
		builder.append ( "->" );
		builder.append ( result );
		
		return builder.toString();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( this.result == null ) ? 0 : this.result.hashCode () );
		return result;
	}

	@Override
	public boolean equals ( Object obj )
	{
		if ( this == obj )
			return true;
		
		if ( !super.equals ( obj ) )
			return false;
		
		if ( getClass () != obj.getClass () )
			return false;
		
		Response other = ( Response ) obj;
		
		if ( result != other.result )
			return false;
		
		return true;
	}

	@Override
	public void encodePayload ()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decodePayload ()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String payloadString ()
	{
		// TODO Auto-generated method stub
		return null;
	}
}

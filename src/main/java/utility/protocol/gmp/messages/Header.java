package utility.protocol.gmp.messages;

public class Header
{
	private boolean			highPriority;
	private MessageType		type;
	private MessageCategory	category;
	private int				id;

	public Header ( boolean highPriority, MessageType type, MessageCategory category, int id )
	{
		this.highPriority = highPriority;
		this.type         = type;
		this.category     = category;
		this.id           = id;
	}
	
	public Header()
	{}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( category == null ) ? 0 : category.hashCode () );
		result = prime * result + ( highPriority ? 1231 : 1237 );
		result = prime * result + id;
		result = prime * result + ( ( type == null ) ? 0 : type.hashCode () );
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
		
		Header other = ( Header ) obj;
		
		if ( category != other.category )
			return false;
		
		if ( highPriority != other.highPriority )
			return false;
		
		if ( id != other.id )
			return false;
		
		if ( type != other.type )
			return false;
		
		return true;
	}

	public boolean isHighPriority()
	{
		return highPriority;
	}

	public void setHighPriority ( boolean highPriority )
	{
		this.highPriority = highPriority;
	}

	public MessageType getType()
	{
		return type;
	}

	public void setType ( MessageType type )
	{
		this.type = type;
	}

	public MessageCategory getCategory()
	{
		return category;
	}

	public void setCategory ( MessageCategory category )
	{
		this.category = category;
	}

	public int getId()
	{
		return id;
	}

	public void setId ( int id )
	{
		this.id = id;
	}

	protected String idString()
	{
		return Integer.valueOf ( id ).toString();
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append ( "[" );
		
		if ( highPriority )
			builder.append ( "HI," );
		
		builder.append ( type );
		builder.append ( "," );
		
		builder.append ( category );
		builder.append ( "," );
		
		builder.append ( idString() );
		
		return builder.toString();
	}
}

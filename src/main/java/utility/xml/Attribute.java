package utility.xml;

public class Attribute
{	
	private org.w3c.dom.Attr attribute;
	
	public Attribute ( org.w3c.dom.Node attribute )
	{
		this.attribute = (org.w3c.dom.Attr) attribute;
	}
	
	public Attribute ( org.w3c.dom.Attr attribute )
	{
		this.attribute = attribute;
	}
	
	public String tag()
	{
		return attribute.getNodeName();
	}
	
	public String value()
	{
		return attribute.getNodeValue();
	}
}

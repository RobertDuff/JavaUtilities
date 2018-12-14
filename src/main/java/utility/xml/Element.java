package utility.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Element
{
	private org.w3c.dom.Element element;
	
	public Element ( org.w3c.dom.Node element )
	{
		this.element = (org.w3c.dom.Element) element;
	}
	
	public Element ( org.w3c.dom.Element element )
	{
		this.element = element;
	}
	
	public org.w3c.dom.Element element()
	{
		return element;
	}
	
	public String tag()
	{
		return element.getNodeName();
	}
	
	public void text ( String text )
	{
		element.setTextContent ( text );
	}
	
	public String text()
	{
		StringBuilder builder = new StringBuilder();
		
		for ( org.w3c.dom.Node n : utility.xml.Node.nodeArray ( element.getChildNodes() ) )
			if ( n.getNodeType () == org.w3c.dom.Node.TEXT_NODE )
				builder.append ( n.getNodeValue() );

		// Remove Leading and Trailing White Space
		
		Matcher m = Pattern.compile ( "^\\s+" ).matcher ( builder );
		
		if ( m.find() )
			builder.delete ( m.start(), m.end() );
		
		m = Pattern.compile ( "\\s+$" ).matcher ( builder );
		
		if ( m.find() )
			builder.delete ( m.start(), m.end() );

		// Shrink white space to single spaces
				
		for ( m = Pattern.compile ( "\\s{2,}" ).matcher ( builder ); 
			  m.find();
			  m = Pattern.compile ( "\\s{2,}" ).matcher ( builder ) )
			builder.replace ( m.start(), m.end(), " " );
		
		return builder.toString();
	}
	
	public boolean hasChild ( String tag )
	{
		org.w3c.dom.Node[] nodeArray = Node.nodeArray ( element.getElementsByTagName ( tag ) );
		
		return nodeArray.length != 0;
	}
	
	public utility.xml.Element newChild ( String tag )
	{
		org.w3c.dom.Node child = element.getOwnerDocument().createElement ( tag );
		element.appendChild ( child );
		return new utility.xml.Element ( child );
	}
	
	public utility.xml.Element child ( String tag )
	{
		org.w3c.dom.Node[] nodeArray = Node.nodeArray ( element.getElementsByTagName ( tag ) );
		
		if ( nodeArray.length == 0 )
			return null;
		
		return new utility.xml.Element ( nodeArray[ 0 ] );
	}
	
	public List<utility.xml.Element> children()
	{
		org.w3c.dom.Node[] nodeArray = Node.nodeArray ( element.getChildNodes() );
		List<utility.xml.Element> elementList = new ArrayList<utility.xml.Element> ( nodeArray.length );

		for ( org.w3c.dom.Node node : nodeArray )
			if ( node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE )
				elementList.add ( new utility.xml.Element ( node ) );
	
		return elementList;
	}
	
	public List<utility.xml.Element> children ( String tag )
	{
		org.w3c.dom.Node[] nodeArray = Node.nodeArray ( element.getElementsByTagName ( tag ) );
		List<utility.xml.Element> elementList = new ArrayList<utility.xml.Element> ( nodeArray.length );
		
		for ( int n = 0; n < nodeArray.length; n++ )
			elementList.add ( new utility.xml.Element ( nodeArray[ n ] ) );
		
		return elementList;
	}
	
	public utility.xml.Attribute attribute ( String name )
	{
		if ( element.hasAttribute ( name ) )
			return new utility.xml.Attribute ( element.getAttributeNode ( name ) );
		
		return null;
	}
	
	public List<utility.xml.Attribute> attributes()
	{
		List<utility.xml.Attribute> attributes = new ArrayList<utility.xml.Attribute>();
		
		if ( element.hasAttributes() )
		{
			org.w3c.dom.NamedNodeMap map = element.getAttributes();
			
			for ( int n = 0; n < map.getLength(); n++ )
				attributes.add ( new utility.xml.Attribute ( map.item ( n ) ) );
		}
		
		return attributes;
	}
	
	public void attribute ( String name, String value )
	{
		element.setAttribute ( name, value );
	}
	
	public void dump ( String indent )
	{
		System.out.printf ( "%s<%s>: Start\n", indent, tag() );
		
		String text = text();
		
		if ( text != null && text.length() > 0 )
			System.out.printf ( "%s  \"%s\"\n", indent, text );
		
		for ( utility.xml.Attribute attr : attributes() )
			System.out.printf ( "%s  Attr: '%s' -> '%s'\n", indent, attr.tag(), attr.value() );
		
		for ( utility.xml.Element elem : children() )
			elem.dump ( indent + "    " );
		
		System.out.printf ( "%s<%s>: End\n", indent, tag() );
	}
}

package utility.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlNode
{
	private static XPath xpath = XPathFactory.newInstance().newXPath();

	private Node node;

	public XmlNode ( Node node )
	{
		this.node = node;
	}

	public Node DOMNode()
	{
		return node;
	}

	public String name()
	{
		return node.getNodeName();
	}
	
	public boolean isA ( String name )
	{
		return node.getNodeName().equals ( name );
	}

	public XmlNode node ( String pattern )
	{		
		Node foundNode = internalGetNode ( node, pattern );

		if ( foundNode == null )
			return null;

		return new XmlNode ( foundNode );
	}

	public List<XmlNode> nodeList ( String pattern )
	{
		List<XmlNode> nodeList = new ArrayList<XmlNode>();

		NodeList nodeSet = internalGetNodeList ( node, pattern );

		for ( int i=0; i < nodeSet.getLength(); i++ )
			nodeList.add ( new XmlNode ( nodeSet.item ( i ) ) );

		return nodeList;
	}

	public String rawText()
	{
		return internalGetString ( node, "text()" );
	}

	public String rawText ( String pattern )
	{
		return internalGetString ( internalGetNode ( node, pattern ), "text()" );
	}

	public List<String> rawTextList ( String pattern )
	{
		List<String> strings = new ArrayList<String>();
		
		NodeList nodeSet = internalGetNodeList ( node, pattern );
		
		for ( int i=0; i < nodeSet.getLength(); i++ )
			strings.add ( internalGetString ( nodeSet.item ( i ), "text()" ) );
		
		return strings;
	}

	public String[] rawTextArray ( String pattern )
	{
		NodeList nodeList = internalGetNodeList ( node, pattern );
		
		String[] stringArray = new String[ nodeList.getLength() ];
		
		for ( int i=0; i < nodeList.getLength(); i++ )
			stringArray[ i ] = internalGetString ( nodeList.item ( i ), "text()" );

		return stringArray;
	}
	
	public String text()
	{
		return internalGetString ( node, "normalize-space()" );
	}

	public String text ( String pattern )
	{
		return internalGetString ( internalGetNode ( node, pattern ), "normalize-space()" );
	}

	public List<String> textList ( String pattern )
	{
		List<String> strings = new ArrayList<String>();
		
		NodeList nodeSet = internalGetNodeList ( node, pattern );
		
		for ( int i=0; i < nodeSet.getLength(); i++ )
			strings.add ( internalGetString ( nodeSet.item ( i ), "normalize-space()" ) );
		
		return strings;
	}
	
	public String[] textArray ( String pattern )
	{
		NodeList nodeList = internalGetNodeList ( node, pattern );
		
		String[] stringArray = new String[ nodeList.getLength() ];
		
		for ( int i=0; i < nodeList.getLength(); i++ )
			stringArray[ i ] = internalGetString ( nodeList.item ( i ), "normalize-space()" );

		return stringArray;
	}

	public int asInt ( String pattern )
	{
		return internalGetDouble ( node, pattern ).intValue();
	}

	public List<Integer> integerList ( String pattern )
	{
		List<Integer> integerList = new ArrayList<Integer>();

		NodeList nodeList = internalGetNodeList ( node, pattern );
		
		for ( int i=0; i < nodeList.getLength(); i++ )
			integerList.add ( internalGetDouble ( nodeList.item ( i ), "." ).intValue() );

		return integerList;
	}

	public int[] intArray ( String pattern )
	{
		NodeList nodeList = internalGetNodeList ( node, pattern );
		
		int[] intArray = new int[ nodeList.getLength() ];
		
		for ( int i=0; i < nodeList.getLength(); i++ )
			intArray[ i ] = internalGetDouble ( nodeList.item ( i ), "." ).intValue();

		return intArray;
	}

	public double asDouble ( String pattern )
	{
		return internalGetDouble ( node, pattern );
	}

	public List<Double> doubleList ( String pattern )
	{
		List<Double> doubleList = new ArrayList<Double>();

		NodeList nodeList = internalGetNodeList ( node, pattern );
		
		for ( int i=0; i < nodeList.getLength(); i++ )
			doubleList.add ( internalGetDouble ( nodeList.item ( i ), "." ) );

		return doubleList;
	}

	public double[] doubleArray ( String pattern )
	{
		NodeList nodeList = internalGetNodeList ( node, pattern );
		
		double[] doubleArray = new double[ nodeList.getLength() ];
		
		for ( int i=0; i < nodeList.getLength(); i++ )
			doubleArray[ i ] = internalGetDouble ( nodeList.item ( i ), "." );

		return doubleArray;
	}

	public boolean test ( String pattern )
	{
		return internalGetBoolean ( node, pattern );
	}
	
	private Node internalGetNode ( Node startNode, String pattern )
	{
		Node foundNode = null;
	
		try 
		{
			foundNode = ( Node ) xpath.evaluate ( pattern, startNode, XPathConstants.NODE );
		}
		catch ( XPathExpressionException e )
		{
			e.printStackTrace();
		}
	
		return foundNode;
	}

	private NodeList internalGetNodeList ( Node startNode, String pattern )
	{
		NodeList nodeList = null;

		try 
		{
			nodeList = ( NodeList ) xpath.evaluate ( pattern, startNode, XPathConstants.NODESET );
		}
		catch ( XPathExpressionException e )
		{
			e.printStackTrace();
		}

		return nodeList;
	}

	private String internalGetString ( Node startNode, String pattern )
	{
		String string = null;

		try 
		{
			string = ( String ) xpath.evaluate ( pattern, startNode, XPathConstants.STRING );
		}
		catch ( XPathExpressionException e )
		{
			e.printStackTrace();
		}

		return string;
	}

	private Double internalGetDouble ( Node startNode, String pattern )
	{
		Double value = null;

		try 
		{
			value = ( Double ) xpath.evaluate ( pattern, startNode, XPathConstants.NUMBER );
		}
		catch ( XPathExpressionException e )
		{
			e.printStackTrace();
		}

		return value;
	}

	private boolean internalGetBoolean ( Node startNode, String pattern )
	{
		Boolean value = null;

		try
		{
			value = ( Boolean ) xpath.evaluate ( pattern, startNode, XPathConstants.BOOLEAN );
		}
		catch ( XPathExpressionException e )
		{
			e.printStackTrace();
		}

		return value;
	}
}

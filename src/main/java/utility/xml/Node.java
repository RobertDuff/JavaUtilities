package utility.xml;

import java.util.Arrays;
import java.util.List;

public class Node
{
	public static org.w3c.dom.Node[] nodeArray ( org.w3c.dom.NodeList list )
	{
		org.w3c.dom.Node[] array = new org.w3c.dom.Node[ list.getLength () ];
		
		for ( int i=0; i<list.getLength(); i++ )
			array[ i ] = list.item ( i );

		return array;
	}

	public static List<org.w3c.dom.Node> nodeList ( org.w3c.dom.NodeList nodeList )
	{
		return Arrays.asList ( nodeArray ( nodeList  ) );
	}
}

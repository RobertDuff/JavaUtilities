package utility.xml;



import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class XmlNodeTest
{
	private Document doc;
	
	@BeforeEach
	public void setUp () throws Exception
	{
		InputStream xml = ClassLoader.getSystemResourceAsStream ( "family.xml" );
		
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
		doc = documentBuilder.parse ( xml );
		Assertions.assertNotNull ( doc );
	}

	@AfterEach
	public void tearDown () throws Exception
	{
	}

	@Test
	public final void testDOMNode ()
	{
		XmlNode xml = new XmlNode ( doc );
		Assertions.assertNotNull ( xml );
		
		Node node = xml.DOMNode();
		Assertions.assertNotNull ( node );
		
		Assertions.assertEquals ( Node.DOCUMENT_NODE, node.getNodeType() );
		Assertions.assertEquals ( "#document", node.getNodeName() );
	}

	@Test
	public final void testName()
	{
		XmlNode xml = new XmlNode ( doc );
		String name = xml.node ( "/family" ).name();
		Assertions.assertEquals ( "family", name );
	}

	@Test
	public final void testIsA()
	{
		XmlNode xml = new XmlNode ( doc );
		boolean bool = xml.node ( "/family" ).isA ( "family" );
		Assertions.assertTrue ( bool );
	}
	
	@Test
	public final void testNode () 
	{
		XmlNode xml = new XmlNode ( doc );
		
		XmlNode node;
		
		node = xml.node ( "family" );
		Assertions.assertNotNull ( node );
		
		node = xml.node ( "/family" );
		Assertions.assertNotNull ( node );
		
		node = xml.node ( "father" );
		Assertions.assertNull ( node );
		
		node = xml.node ( "//father" );
		Assertions.assertNotNull ( node );
	}

	@Test
	public final void testNodeList ()
	{
		
	}

	@Test
	public final void testRawText ()
	{
		XmlNode xml = new XmlNode ( doc );
		String text = xml.node ( "/family/name" ).rawText();
		Assertions.assertEquals ( "		Duff       Family  ", text );
	}

	@Test
	public final void testRawTextString () 
	{
		XmlNode xml = new XmlNode ( doc );
		
		String text = xml.rawText ( "/family/name" );
		Assertions.assertEquals ( "		Duff       Family  ", text );
	}

	@Test
	public final void testRawTextList ()
	{
		XmlNode xml = new XmlNode ( doc );
		
		List<String> names = xml.rawTextList ( "//name" );
		
		Assertions.assertEquals ( 7, names.size() );
		
		Assertions.assertEquals ( "		Duff       Family  ", names.get ( 0 ) );
		Assertions.assertEquals ( "Robert", names.get ( 1 ) );
		Assertions.assertEquals ( "Jackeline", names.get ( 2 ) );
		Assertions.assertEquals ( "Madeline", names.get ( 3 ) );
		Assertions.assertEquals ( "Ethan", names.get ( 4 ) );
		Assertions.assertEquals ( "Lauren", names.get ( 5 ) );
		Assertions.assertEquals ( "Kristen", names.get ( 6 ) );
	}

	@Test
	public final void testRawTextArray ()
	{
		XmlNode xml = new XmlNode ( doc );
		
		String[] names = xml.rawTextArray ( "//name" );
		
		Assertions.assertEquals ( 7, names.length );
		
		Assertions.assertEquals ( "		Duff       Family  ", names[ 0 ] );
		Assertions.assertEquals ( "Robert", names[ 1 ] );
		Assertions.assertEquals ( "Jackeline", names[ 2 ] );
		Assertions.assertEquals ( "Madeline", names[ 3 ] );
		Assertions.assertEquals ( "Ethan", names[ 4 ] );
		Assertions.assertEquals ( "Lauren", names[ 5 ] );
		Assertions.assertEquals ( "Kristen", names[ 6 ] );
	}

	@Test
	public final void testText () 
	{
		XmlNode xml = new XmlNode ( doc );
		XmlNode node = xml.node ( "/family/name" );
		
		String text = node.text();
		Assertions.assertEquals ( "Duff Family", text );
		
	}

	@Test
	public final void testTextString ()
	{
		XmlNode xml = new XmlNode ( doc );
		
		String text = xml.text ( "/family/name" );
		Assertions.assertEquals ( "Duff Family", text );
	}

	@Test
	public final void testTextList () 
	{
		XmlNode xml = new XmlNode ( doc );
		
		List<String> names = xml.textList ( "//name" );
		
		Assertions.assertEquals ( 7, names.size() );
		
		Assertions.assertEquals ( "Duff Family", names.get ( 0 ) );
		Assertions.assertEquals ( "Robert", names.get ( 1 ) );
		Assertions.assertEquals ( "Jackeline", names.get ( 2 ) );
		Assertions.assertEquals ( "Madeline", names.get ( 3 ) );
		Assertions.assertEquals ( "Ethan", names.get ( 4 ) );
		Assertions.assertEquals ( "Lauren", names.get ( 5 ) );
		Assertions.assertEquals ( "Kristen", names.get ( 6 ) );
	}

	@Test
	public final void testTextArray ()
	{
		XmlNode xml = new XmlNode ( doc );
		
		String[] names = xml.textArray ( "//name" );
		
		Assertions.assertEquals ( 7, names.length );
		
		Assertions.assertEquals ( "Duff Family", names[ 0 ] );
		Assertions.assertEquals ( "Robert", names[ 1 ] );
		Assertions.assertEquals ( "Jackeline", names[ 2 ] );
		Assertions.assertEquals ( "Madeline", names[ 3 ] );
		Assertions.assertEquals ( "Ethan", names[ 4 ] );
		Assertions.assertEquals ( "Lauren", names[ 5 ] );
		Assertions.assertEquals ( "Kristen", names[ 6 ] );
		
		names = xml.textArray ( "//child[female]/name" );
		
		Assertions.assertEquals ( 3, names.length );
		
		Assertions.assertEquals ( "Madeline", names[ 0 ] );
		Assertions.assertEquals ( "Lauren", names[ 1 ] );
		Assertions.assertEquals ( "Kristen", names[ 2 ] );
	}

	@Test
	public final void testAsIntString () 
	{
		XmlNode xml = new XmlNode ( doc );
		
		int age = xml.asInt ( "/family/father/age" );
		Assertions.assertEquals ( 48, age );
		
		int count = xml.asInt ( "count(//children/child)");
		Assertions.assertEquals ( 4, count );
	}

	@Test
	public final void testIntegerList ()
	{
		XmlNode xml = new XmlNode ( doc );
		
		List<Integer> ages = xml.integerList ( "//age" );
		
		Assertions.assertEquals ( 6, ages.size() );
		
		Assertions.assertEquals ( 48, ages.get ( 0 ).intValue () );
		Assertions.assertEquals ( 46, ages.get ( 1 ).intValue () );
		Assertions.assertEquals ( 19, ages.get ( 2 ).intValue () );
		Assertions.assertEquals ( 17, ages.get ( 3 ).intValue () );
		Assertions.assertEquals ( 15, ages.get ( 4 ).intValue () );
		Assertions.assertEquals ( 12, ages.get ( 5 ).intValue () );
	}

	@Test
	public final void testIntArray () 
	{
		XmlNode xml = new XmlNode ( doc );
		
		int[] ages = xml.intArray ( "//age" );
		
		Assertions.assertEquals ( 6, ages.length );
		
		Assertions.assertEquals ( 48, ages[ 0 ] );
		Assertions.assertEquals ( 46, ages[ 1 ] );
		Assertions.assertEquals ( 19, ages[ 2 ] );
		Assertions.assertEquals ( 17, ages[ 3 ] );
		Assertions.assertEquals ( 15, ages[ 4 ] );
		Assertions.assertEquals ( 12, ages[ 5 ] );
	}

	@Test
	public final void testAsDoubleString () 
	{
		XmlNode xml = new XmlNode ( doc );
		
		double age = xml.asDouble ( "/family/father/age" );
		Assertions.assertEquals ( 48, age, 0.00001 );
	}

	@Test
	public final void testDoubleList ()
	{
		XmlNode xml = new XmlNode ( doc );
		
		List<Double> ages = xml.doubleList ( "//age" );
		
		Assertions.assertEquals ( 6, ages.size() );
		
		Assertions.assertEquals ( 48, ages.get ( 0 ).doubleValue (), 0.00001 );
		Assertions.assertEquals ( 46, ages.get ( 1 ).doubleValue (), 0.00001 );
		Assertions.assertEquals ( 19, ages.get ( 2 ).doubleValue (), 0.00001 );
		Assertions.assertEquals ( 17, ages.get ( 3 ).doubleValue (), 0.00001 );
		Assertions.assertEquals ( 15, ages.get ( 4 ).doubleValue (), 0.00001 );
		Assertions.assertEquals ( 12, ages.get ( 5 ).doubleValue (), 0.00001 );
	}

	@Test
	public final void testDoubleArray () 
	{
		XmlNode xml = new XmlNode ( doc );
		
		double[] ages = xml.doubleArray ( "//age" );
		
		Assertions.assertEquals ( 6, ages.length );
		
		Assertions.assertEquals ( 48, ages[ 0 ], 0.00001 );
		Assertions.assertEquals ( 46, ages[ 1 ], 0.00001 );
		Assertions.assertEquals ( 19, ages[ 2 ], 0.00001 );
		Assertions.assertEquals ( 17, ages[ 3 ], 0.00001 );
		Assertions.assertEquals ( 15, ages[ 4 ], 0.00001 );
		Assertions.assertEquals ( 12, ages[ 5 ], 0.00001 );
	}

	@Test
	public final void testTest ()
	{
		XmlNode xml = new XmlNode ( doc );
		
		boolean bool;
		
		bool = xml.test ( "family" );
		Assertions.assertTrue ( bool );
		
		bool = xml.test ( "fred" );
		Assertions.assertFalse ( bool );
		
		bool = xml.test ( "/family/father[age=48]" );
		Assertions.assertTrue ( bool );
		
		bool = xml.test ( "/family/father[age=46]" );
		Assertions.assertFalse ( bool );
	}
}

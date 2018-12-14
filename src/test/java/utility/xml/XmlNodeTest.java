package utility.xml;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class XmlNodeTest
{
	private Document doc;
	
	@Before
	public void setUp () throws Exception
	{
		InputStream xml = ClassLoader.getSystemResourceAsStream ( "family.xml" );
		
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
		doc = documentBuilder.parse ( xml );
		assertNotNull ( doc );
	}

	@After
	public void tearDown () throws Exception
	{
	}

	@Test
	public final void testDOMNode ()
	{
		XmlNode xml = new XmlNode ( doc );
		assertNotNull ( xml );
		
		Node node = xml.DOMNode();
		assertNotNull ( node );
		
		assertEquals ( Node.DOCUMENT_NODE, node.getNodeType() );
		assertEquals ( "#document", node.getNodeName() );
	}

	@Test
	public final void testName()
	{
		XmlNode xml = new XmlNode ( doc );
		String name = xml.node ( "/family" ).name();
		assertEquals ( "family", name );
	}

	@Test
	public final void testIsA()
	{
		XmlNode xml = new XmlNode ( doc );
		boolean bool = xml.node ( "/family" ).isA ( "family" );
		assertTrue ( bool );
	}
	
	@Test
	public final void testNode () 
	{
		XmlNode xml = new XmlNode ( doc );
		
		XmlNode node;
		
		node = xml.node ( "family" );
		assertNotNull ( node );
		
		node = xml.node ( "/family" );
		assertNotNull ( node );
		
		node = xml.node ( "father" );
		assertNull ( node );
		
		node = xml.node ( "//father" );
		assertNotNull ( node );
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
		assertEquals ( "		Duff       Family  ", text );
	}

	@Test
	public final void testRawTextString () 
	{
		XmlNode xml = new XmlNode ( doc );
		
		String text = xml.rawText ( "/family/name" );
		assertEquals ( "		Duff       Family  ", text );
	}

	@Test
	public final void testRawTextList ()
	{
		XmlNode xml = new XmlNode ( doc );
		
		List<String> names = xml.rawTextList ( "//name" );
		
		assertEquals ( 7, names.size() );
		
		assertEquals ( "		Duff       Family  ", names.get ( 0 ) );
		assertEquals ( "Robert", names.get ( 1 ) );
		assertEquals ( "Jackeline", names.get ( 2 ) );
		assertEquals ( "Madeline", names.get ( 3 ) );
		assertEquals ( "Ethan", names.get ( 4 ) );
		assertEquals ( "Lauren", names.get ( 5 ) );
		assertEquals ( "Kristen", names.get ( 6 ) );
	}

	@Test
	public final void testRawTextArray ()
	{
		XmlNode xml = new XmlNode ( doc );
		
		String[] names = xml.rawTextArray ( "//name" );
		
		assertEquals ( 7, names.length );
		
		assertEquals ( "		Duff       Family  ", names[ 0 ] );
		assertEquals ( "Robert", names[ 1 ] );
		assertEquals ( "Jackeline", names[ 2 ] );
		assertEquals ( "Madeline", names[ 3 ] );
		assertEquals ( "Ethan", names[ 4 ] );
		assertEquals ( "Lauren", names[ 5 ] );
		assertEquals ( "Kristen", names[ 6 ] );
	}

	@Test
	public final void testText () 
	{
		XmlNode xml = new XmlNode ( doc );
		XmlNode node = xml.node ( "/family/name" );
		
		String text = node.text();
		assertEquals ( "Duff Family", text );
		
	}

	@Test
	public final void testTextString ()
	{
		XmlNode xml = new XmlNode ( doc );
		
		String text = xml.text ( "/family/name" );
		assertEquals ( "Duff Family", text );
	}

	@Test
	public final void testTextList () 
	{
		XmlNode xml = new XmlNode ( doc );
		
		List<String> names = xml.textList ( "//name" );
		
		assertEquals ( 7, names.size() );
		
		assertEquals ( "Duff Family", names.get ( 0 ) );
		assertEquals ( "Robert", names.get ( 1 ) );
		assertEquals ( "Jackeline", names.get ( 2 ) );
		assertEquals ( "Madeline", names.get ( 3 ) );
		assertEquals ( "Ethan", names.get ( 4 ) );
		assertEquals ( "Lauren", names.get ( 5 ) );
		assertEquals ( "Kristen", names.get ( 6 ) );
	}

	@Test
	public final void testTextArray ()
	{
		XmlNode xml = new XmlNode ( doc );
		
		String[] names = xml.textArray ( "//name" );
		
		assertEquals ( 7, names.length );
		
		assertEquals ( "Duff Family", names[ 0 ] );
		assertEquals ( "Robert", names[ 1 ] );
		assertEquals ( "Jackeline", names[ 2 ] );
		assertEquals ( "Madeline", names[ 3 ] );
		assertEquals ( "Ethan", names[ 4 ] );
		assertEquals ( "Lauren", names[ 5 ] );
		assertEquals ( "Kristen", names[ 6 ] );
		
		names = xml.textArray ( "//child[female]/name" );
		
		assertEquals ( 3, names.length );
		
		assertEquals ( "Madeline", names[ 0 ] );
		assertEquals ( "Lauren", names[ 1 ] );
		assertEquals ( "Kristen", names[ 2 ] );
	}

	@Test
	public final void testAsIntString () 
	{
		XmlNode xml = new XmlNode ( doc );
		
		int age = xml.asInt ( "/family/father/age" );
		assertEquals ( 48, age );
		
		int count = xml.asInt ( "count(//children/child)");
		assertEquals ( 4, count );
	}

	@Test
	public final void testIntegerList ()
	{
		XmlNode xml = new XmlNode ( doc );
		
		List<Integer> ages = xml.integerList ( "//age" );
		
		assertEquals ( 6, ages.size() );
		
		assertEquals ( 48, ages.get ( 0 ).intValue () );
		assertEquals ( 46, ages.get ( 1 ).intValue () );
		assertEquals ( 19, ages.get ( 2 ).intValue () );
		assertEquals ( 17, ages.get ( 3 ).intValue () );
		assertEquals ( 15, ages.get ( 4 ).intValue () );
		assertEquals ( 12, ages.get ( 5 ).intValue () );
	}

	@Test
	public final void testIntArray () 
	{
		XmlNode xml = new XmlNode ( doc );
		
		int[] ages = xml.intArray ( "//age" );
		
		assertEquals ( 6, ages.length );
		
		assertEquals ( 48, ages[ 0 ] );
		assertEquals ( 46, ages[ 1 ] );
		assertEquals ( 19, ages[ 2 ] );
		assertEquals ( 17, ages[ 3 ] );
		assertEquals ( 15, ages[ 4 ] );
		assertEquals ( 12, ages[ 5 ] );
	}

	@Test
	public final void testAsDoubleString () 
	{
		XmlNode xml = new XmlNode ( doc );
		
		double age = xml.asDouble ( "/family/father/age" );
		assertEquals ( 48, age, 0.00001 );
	}

	@Test
	public final void testDoubleList ()
	{
		XmlNode xml = new XmlNode ( doc );
		
		List<Double> ages = xml.doubleList ( "//age" );
		
		assertEquals ( 6, ages.size() );
		
		assertEquals ( 48, ages.get ( 0 ).doubleValue (), 0.00001 );
		assertEquals ( 46, ages.get ( 1 ).doubleValue (), 0.00001 );
		assertEquals ( 19, ages.get ( 2 ).doubleValue (), 0.00001 );
		assertEquals ( 17, ages.get ( 3 ).doubleValue (), 0.00001 );
		assertEquals ( 15, ages.get ( 4 ).doubleValue (), 0.00001 );
		assertEquals ( 12, ages.get ( 5 ).doubleValue (), 0.00001 );
	}

	@Test
	public final void testDoubleArray () 
	{
		XmlNode xml = new XmlNode ( doc );
		
		double[] ages = xml.doubleArray ( "//age" );
		
		assertEquals ( 6, ages.length );
		
		assertEquals ( 48, ages[ 0 ], 0.00001 );
		assertEquals ( 46, ages[ 1 ], 0.00001 );
		assertEquals ( 19, ages[ 2 ], 0.00001 );
		assertEquals ( 17, ages[ 3 ], 0.00001 );
		assertEquals ( 15, ages[ 4 ], 0.00001 );
		assertEquals ( 12, ages[ 5 ], 0.00001 );
	}

	@Test
	public final void testTest ()
	{
		XmlNode xml = new XmlNode ( doc );
		
		boolean bool;
		
		bool = xml.test ( "family" );
		assertTrue ( bool );
		
		bool = xml.test ( "fred" );
		assertFalse ( bool );
		
		bool = xml.test ( "/family/father[age=48]" );
		assertTrue ( bool );
		
		bool = xml.test ( "/family/father[age=46]" );
		assertFalse ( bool );
	}
}

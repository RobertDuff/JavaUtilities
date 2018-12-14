package utility.xml;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class XmlLoaderTest
{
	@Test
	public void testResource() 
	{
		XmlNode xmlNode = XmlLoader.load ( "family.xml" );
		assertNotNull ( xmlNode );
	}

	@Test
	public void testResourceWithSchema() 
	{
		XmlNode xmlNode = XmlLoader.load ( "family.xml", "family.xsd" );
		assertNotNull ( xmlNode );
	}
}

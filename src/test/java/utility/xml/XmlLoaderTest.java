package utility.xml;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class XmlLoaderTest
{
	@Test
	public void testResource() 
	{
		XmlNode xmlNode = XmlLoader.load ( "family.xml" );
		Assertions.assertNotNull ( xmlNode );
	}

	@Test
	public void testResourceWithSchema() 
	{
		XmlNode xmlNode = XmlLoader.load ( "family.xml", "family.xsd" );
		Assertions.assertNotNull ( xmlNode );
	}
}

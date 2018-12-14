package utility.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.helpers.DefaultHandler;

public class XmlLoader
{
	public static XmlNode load ( String xmlResource )
	{
		return load ( xmlResource, null );
	}

	public static XmlNode load ( String xmlResource, String schemaResource )
	{
		InputStream xmlStream    = ClassLoader.getSystemResourceAsStream ( xmlResource );
		InputStream schemaStream = null;

		if ( schemaResource != null )
			schemaStream = ClassLoader.getSystemResourceAsStream ( schemaResource );

		return load ( xmlStream, schemaStream );
	}

	public static XmlNode load ( File xmlFile )
	{
		return load ( xmlFile, null );
	}

	public static XmlNode load ( File xmlFile, File schemaFile )
	{
		XmlNode xmlNode = null;

		try 
		{
			InputStream xmlStream = new FileInputStream ( xmlFile );
			InputStream schemaStream = null;

			if ( schemaFile != null )
				schemaStream = new FileInputStream ( schemaFile );

			xmlNode = load ( xmlStream, schemaStream );
		} 
		catch ( FileNotFoundException e ) 
		{
			e.printStackTrace();
		}

		return xmlNode;
	}

	public static XmlNode load ( InputStream xmlStream )
	{
		return load ( xmlStream, null );
	}

	public static XmlNode load ( InputStream xmlStream, InputStream schemaStream )
	{
		XmlNode xmlNode = null;

		try
		{
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			Schema schema = null;
			
			if ( schemaStream != null )
			{
				SchemaFactory schemaFactory = SchemaFactory.newInstance ( XMLConstants.W3C_XML_SCHEMA_NS_URI );
				schemaFactory.setErrorHandler ( new DefaultHandler() );
				Source schemaSource = new StreamSource ( schemaStream );
				schema = schemaFactory.newSchema ( schemaSource );

				builderFactory.setSchema ( schema );
				builderFactory.setValidating ( true );
			}

			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			builder.setErrorHandler ( new DefaultHandler() );

			Document document = builder.parse ( xmlStream );

			if ( schemaStream != null )
			{
				DOMSource validationSource = new DOMSource ( document );
				DOMResult validationResult = new DOMResult();

				Validator v = schema.newValidator();

				v.validate ( validationSource, validationResult );
			}
			
			xmlNode = new XmlNode ( document );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}

		return xmlNode;
	}
}

package utility.xml;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Document
{
	private static DocumentBuilderFactory builderFactory;
	private static DocumentBuilder        builder;
	
	private static TransformerFactory     transformerFactory;
	private static Transformer            transformer;
	
	static
	{
		try
		{
			builderFactory = DocumentBuilderFactory.newInstance();
			builder = builderFactory.newDocumentBuilder();
			
			transformerFactory = TransformerFactory.newInstance();
			transformer = transformerFactory.newTransformer();
		}
		catch ( ParserConfigurationException e )
		{
			e.printStackTrace();
		}
		catch ( TransformerConfigurationException e )
		{
			e.printStackTrace();
		}
	}
	
	private org.w3c.dom.Document document;

	public Document ( String xml )
	{
		try 
		{
				StringReader reader = new StringReader ( xml );
				InputSource source  = new InputSource ( reader );
				document            = builder.parse ( source );
		} 
		catch ( SAXException e )
		{
			e.printStackTrace();
		} 
		catch ( IOException e ) 
		{
			e.printStackTrace();
		}
	}

	public Document ( File file )
	{
		try 
		{
			document  = builder.parse ( file );
		} 
		catch ( SAXException e )
		{
			e.printStackTrace();
		} 
		catch ( IOException e ) 
		{
			e.printStackTrace();
		}
	}

	public Document()
	{
		document = builder.newDocument();
	}
	
	public utility.xml.Element top()
	{
		return new utility.xml.Element ( document.getDocumentElement() );
	}
	
	public utility.xml.Element top ( String tag )
	{
		org.w3c.dom.Element top = document.createElement ( tag );
		document.appendChild ( top );
		return new utility.xml.Element ( top );
	}
	
	public void dump()
	{
		utility.xml.Element top = top();
		top.dump ( "" );
	}
	
	public void write ( OutputStream stream )
	{
		Source source = new DOMSource ( document );
		Result result = new StreamResult ( stream );
		
		try
		{
			transformer.transform ( source, result );
		}
		catch ( TransformerException e )
		{
			e.printStackTrace();
		}
	}
	
	public void write ( File file )
	{
		Source source = new DOMSource ( document );
		Result result = new StreamResult ( file );
		
		try
		{
			transformer.transform ( source, result );
		}
		catch ( TransformerException e )
		{
			e.printStackTrace();
		}
	}
}

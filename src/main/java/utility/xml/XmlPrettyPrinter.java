package utility.xml;

import java.io.StringWriter;
import java.io.Writer;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;

import org.dom4j.Document;
import org.dom4j.io.DocumentSource;

public class XmlPrettyPrinter
{
    protected Transformer transformer;
    
    public XmlPrettyPrinter () throws TransformerConfigurationException, TransformerFactoryConfigurationError
    {
        transformer = TransformerFactory.newInstance().newTransformer();
        
        transformer.setOutputProperty ( OutputKeys.ENCODING, "UTF-8" );
        transformer.setOutputProperty ( OutputKeys.INDENT, "yes" );
        transformer.setOutputProperty ( "{http://xml.apache.org/xslt}indent-amount", "2" );
    }
    
    public String pretty ( Document document ) throws TransformerException
    {
        Writer out = new StringWriter();
        
        transformer.transform ( new DocumentSource ( document ), new StreamResult ( out ) );
        
        return out.toString ();
    }
}

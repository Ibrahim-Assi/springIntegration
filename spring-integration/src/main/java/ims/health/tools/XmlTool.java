package ims.health.tools;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

import ims.health.entities.RssItem;

public class XmlTool {

	// TODO: replace RssItem With generic type
	public static String marshal(RssItem rssItem) throws JAXBException {
	    StringWriter stringWriter = new StringWriter();

	    JAXBContext jaxbContext    = JAXBContext.newInstance(RssItem.class);
	    Marshaller  jaxbMarshaller = jaxbContext.createMarshaller();

	    // format the XML output
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

	    QName qName = new QName("ims.health.entities", "rssItem");
	    JAXBElement<RssItem> root = new JAXBElement<>(qName, RssItem.class, rssItem);

	    jaxbMarshaller.marshal(root, stringWriter);

	    String result = stringWriter.toString();
	    return result;
	  }
	
	
	// TODO: replace RssItem With generic type
    public static RssItem unmarshal(String xmlReq)
    {
	   RssItem instances = null;
           try {
            JAXBContext jc = JAXBContext.newInstance( RssItem.class );
               Unmarshaller u = jc.createUnmarshaller();
               StringBuffer xmlStr = new StringBuffer( xmlReq );
               StringReader strReader = new StringReader( xmlStr.toString() );
               StreamSource strSource = new StreamSource(strReader);
               Object o = u.unmarshal( strSource );
               instances = (RssItem)o;
        } catch (JAXBException e) {

            e.printStackTrace();
        }
        return instances;
    }
	
}

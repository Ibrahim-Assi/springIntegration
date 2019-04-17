package ims.health.files;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.file.dsl.FileWritingMessageHandlerSpec;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.handler.LoggingHandler;

import com.rometools.rome.feed.synd.SyndEntry;

import ims.health.rss.RssItem;

@Configuration
public class FileWriterIntegrationConfig {  
	

	@Configuration
	@ImportResource("classpath:/integration-config.xml")// TODO: should be replaced with DSL style
	public static class XmlConfiguration {}
	  
	 
	@Bean
	public IntegrationFlow fileWriterFlow() {
	 return IntegrationFlows
	 .from(MessageChannels.direct("aljazeeraChannel"))  
	 .<SyndEntry,String >transform(t -> {
		 RssItem rssItem= new RssItem(t.getUri(),t.getTitle(), t.getDescription()+"", t.getLink(), t.getPublishedDate(),t.getComments());
		 String xmlStr="";
	        try {
	        	xmlStr=marshal(rssItem);
	        }
	        catch(Exception e) {
	        	System.out.println("Error: \n"+e);
	        }

	        return xmlStr;
	 }
	 )
	  
	 .channel(MessageChannels.direct("fileWriterChannel"))
	 .handle(
			 Files
			 .outboundAdapter(new File("rssFiles-root")) //TODO: create subfolders   
			 .autoCreateDirectory(true)
			 .fileNameGenerator(message -> {
				// SyndEntry mo = (SyndEntry)message.getPayload();
                // String head = message.getHeaders().getId().toString();
//				 RssItem tmp = null;
//				 try {
//					 tmp =unmarshal(message.getPayload().toString());
//				} catch (Exception e) {
//				 System.out.println("Error: \n"+e);
//				}
//                 System.out.println("Info:"+tmp.getTitle());
				 
                 return "tmp/rss-data.xml";
             })
			 .fileExistsMode(FileExistsMode.APPEND)
			 .appendNewLine(true)
			 )   

     //.log(LoggingHandler.Level.WARN, "*headers.id + ': ' + payload")
	 .get();
	}
	
	
	 
	
	public static String marshal(RssItem rssItem) throws JAXBException {
	    StringWriter stringWriter = new StringWriter();

	    JAXBContext jaxbContext    = JAXBContext.newInstance(RssItem.class);
	    Marshaller  jaxbMarshaller = jaxbContext.createMarshaller();

	    // format the XML output
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

	    QName qName = new QName("ims.health.rss", "rssItem");
	    JAXBElement<RssItem> root = new JAXBElement<>(qName, RssItem.class, rssItem);

	    jaxbMarshaller.marshal(root, stringWriter);

	    String result = stringWriter.toString();
	    return result;
	  }
	
	
	
	
	public static RssItem unmarshal(String xml) {

		  JAXBContext jc;
		  RssItem rssItem = null;

		  try {

		    JAXBContext jaxbContext = JAXBContext.newInstance(RssItem.class);
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		    rssItem = (RssItem) jaxbUnmarshaller.unmarshal(new StringReader(xml));
		     

		 } catch (JAXBException e) {
		    e.printStackTrace();
		  }

		  return rssItem;
		}
	
 
	
}
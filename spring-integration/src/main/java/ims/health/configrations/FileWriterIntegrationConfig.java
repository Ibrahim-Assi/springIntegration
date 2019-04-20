package ims.health.configrations;

import java.io.File; 
import javax.xml.bind.JAXBException;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels; 
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import com.rometools.rome.feed.synd.SyndEntry;
import ims.health.entities.RssItem;
import ims.health.tools.DateTool;
import ims.health.tools.XmlTool;


@Configuration
public class FileWriterIntegrationConfig {  
	

	@Configuration
	@ImportResource("classpath:/integration-config.xml")// TODO: should be replaced with DSL style
	public static class XmlConfiguration {}
	 
	
	@Value("${rss.rootFolder.dir}")
	private String RSS_FOLDER_DIR;
	  
 
	@Bean
	public IntegrationFlow fileWriterFlow() {
	 return IntegrationFlows
	 .from(MessageChannels.direct("aljazeeraChannel"))  
	 .log(LoggingHandler.Level.INFO,l -> "Link:  "+((SyndEntry)l.getPayload()).getLink())// TODO: should be set as final step on the flow
	 .<SyndEntry,String >transform(t -> generateXml(t))
	 .channel(MessageChannels.executor(threadPoolTaskExecutor()))
	 .channel(MessageChannels.direct("fileWriterChannel"))
	 .handle(
		 Files
		 .outboundAdapter(new File(RSS_FOLDER_DIR))  
		 .autoCreateDirectory(true)
		 .fileNameGenerator(message -> generateFileName(message.getPayload().toString()))
		 .fileExistsMode(FileExistsMode.APPEND)
		 .appendNewLine(true)
		 )    
	 .get();
	}
	
	 
	 
	@Bean
	  public TaskExecutor threadPoolTaskExecutor() {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setThreadNamePrefix("Rss_Thread_");
	    executor.setMaxPoolSize(5);
	    executor.setCorePoolSize(5);
	    executor.setQueueCapacity(22);
	    return executor;
	  }
	

	private String generateFileName(String xmlStr)  {
		 RssItem rssItem = null;
		 try {
			 rssItem =XmlTool.unmarshal(xmlStr);
		  } 
		 catch (Exception e) {
				System.err.println("Error in FileWriterIntegrationConfig.generateFileName: ");
				e.printStackTrace();
		 } 
		 String publishedDate= (rssItem.getPubDate()==null?"NULL": DateTool.dateFormate(rssItem.getPubDate(), DateTool.PATTERN_YYYY_MM_DD));
		 String fileName     =  publishedDate+"/"+rssItem.getCategory()+"/"+rssItem.getUri()+".xml";
		
		return fileName;
	}
	
	
	private String generateXml(SyndEntry entry)  {
		String xmlStr="";
		try {
		    RssItem rssItem= new RssItem(entry.getUri(),entry.getTitle(), entry.getDescription().getValue(),
		    		                     entry.getLink(), entry.getPublishedDate() ,entry.getComments(),
		    		                     entry.getCategories().get(0).getName());
	       xmlStr= XmlTool.marshal(rssItem);
		}
		catch(JAXBException e) {
			System.err.println("Error in FileWriterIntegrationConfig.generateXml: ");
			e.printStackTrace();
		} 
		
		return xmlStr;
	}
	
	
	
	
	
	
	
	
}
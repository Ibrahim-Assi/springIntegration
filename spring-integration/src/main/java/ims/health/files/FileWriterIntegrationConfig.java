package ims.health.files;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.rometools.rome.feed.synd.SyndEntry;

import ims.health.rss.RssItem;

@Configuration
public class FileWriterIntegrationConfig {  
	

	@Configuration
	@ImportResource("classpath:/integration-config.xml")
	public static class XmlConfiguration {}
	  
	 
	@Bean
	public IntegrationFlow fileWriterFlow() {
	 return IntegrationFlows
	 .from(MessageChannels.direct("aljazeeraChannel")) 
	 //TODO: should be convert object to xml tag
	 .<SyndEntry,String >transform(t ->  new RssItem(t.getTitle(), t.getDescription()+"", t.getLink(), t.getPublishedDate(),t.getCategories(),t.getComments()).toString())
	 .channel(MessageChannels.direct("fileWriterChannel"))
	 .handle(
			 Files
			 .outboundAdapter(new File("rssFiles-root")) //TODO: create subfolders   
			 .fileExistsMode(FileExistsMode.APPEND)
			 .appendNewLine(true)
			 )         
	 .get();
	}
	
	
}
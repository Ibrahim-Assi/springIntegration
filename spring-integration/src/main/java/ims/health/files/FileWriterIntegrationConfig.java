package ims.health.files;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;

import com.rometools.rome.feed.synd.SyndEntry;

@Configuration
public class FileWriterIntegrationConfig {  
	

	  @Configuration
	  @ImportResource("classpath:/integration-config.xml")
	  public static class XmlConfiguration {}
	  
	
	//@Profile("integration-config.xml")
	@Bean
	public IntegrationFlow fileWriterFlow() {
	 return IntegrationFlows
	 .from(MessageChannels.direct("aljazeeraChannel"))  
	 .handle(
	    rssItem -> {
					  
					 SyndEntry entry = (SyndEntry) rssItem.getPayload();
					 LocalDateTime ldt = LocalDateTime.ofInstant(entry.getPublishedDate().toInstant(),ZoneId.systemDefault());
					 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					 System.out.println(" create folder for date:"+entry.getPublishedDate());
					 
					 Files
					 .outboundAdapter(new File("/rssFiles-root/"+ldt.format(formatter)))
					 .fileExistsMode(FileExistsMode.APPEND)
					 .appendNewLine(true);
					 
			        }
	     
//			 Files
//			 .outboundAdapter(new File("rssFiles"))
//			 .fileExistsMode(FileExistsMode.APPEND)
//			 .appendNewLine(true)
			 )
			        
	 .get();
	}
	
	
}
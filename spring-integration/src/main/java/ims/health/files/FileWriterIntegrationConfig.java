package ims.health.files;

import java.io.File;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;

@Configuration
public class FileWriterIntegrationConfig {  
	

	  @Configuration
	  @ImportResource("classpath:/integration-config.xml")
	  public static class XmlConfiguration {}
	
	 
	@Bean
	public IntegrationFlow fileWriterFlow() {
	 return IntegrationFlows
	 .from(MessageChannels.direct("aljazeeraChannel")) 
	 .handle(m -> System.out.println("*** RssItem:"+m.getPayload()))
//	 .channel(MessageChannels.direct("fileWriterChannel"))
//	 .handle(Files
//	 .outboundAdapter(new File("rssFiles"))
//	 .fileExistsMode(FileExistsMode.APPEND)
//	 .appendNewLine(true))
	 .get();
	}
	
}
package ims.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.support.AbstractApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.integration.channel.DirectChannel;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageHandler;
//import org.springframework.messaging.MessagingException;
//import com.rometools.rome.feed.synd.SyndEntry;
//import ims.health.configrations.AppConfig;
import ims.health.files.FileWriterIntegrationConfig;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@IntegrationComponentScan
public class Appliaction 
{
	
	
    public static void main( String[] args ) throws  Exception
    {
    	  new SpringApplication(FileWriterIntegrationConfig.class).run(args);
    }
}

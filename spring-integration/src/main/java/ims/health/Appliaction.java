package ims.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;

import ims.health.configrations.FileWriterIntegrationConfig;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class Appliaction 
{
	
	
    public static void main( String[] args ) throws  Exception
    {
    	  new SpringApplication(FileWriterIntegrationConfig.class).run(args);
    	 
    }
}

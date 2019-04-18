package ims.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

import ims.health.configrations.FileWriterIntegrationConfig;

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

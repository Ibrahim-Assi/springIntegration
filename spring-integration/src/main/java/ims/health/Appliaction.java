package ims.health;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ims.health.configrations.AppConfig;

/**
 * Hello world!
 *
 */
public class Appliaction 
{
	
	
    public static void main( String[] args )
    {
    	@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("integration.xml");
		AppConfig appConfig = (AppConfig) context.getBean("appConf");
		
		
		
		System.out.println("Rss link:"+ appConfig.getRssLink());
		System.out.println("rootPath:"+appConfig.getRootFolderPath());
    }
}

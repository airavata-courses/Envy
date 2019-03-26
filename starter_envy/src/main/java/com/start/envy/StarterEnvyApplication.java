	package com.start.envy;
	
	import java.util.Collections;
	import org.springframework.boot.autoconfigure.*;	
	import org.springframework.boot.SpringApplication;
	import org.springframework.boot.autoconfigure.SpringBootApplication;
	
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
	
	@EnableConfigurationProperties
        
	@EnableAutoConfiguration
	@SpringBootApplication
	public class StarterEnvyApplication {
	
		public static void main(String[] args) throws UnknownHostException {
			SpringApplication.run(StarterEnvyApplication.class, args);
			InetAddress inetAddress = InetAddress.getLocalHost();
			
			
			
//			ConsulClient client = new ConsulClient("http://149.165.169.49:8500");
////			
////			client.setKVValue("com.my.app.foo", "foo");
////			client.setKVValue("com.my.app.bar", "bar");
////			client.setKVValue("com.your.app.foo", "hello");
////			client.setKVValue("com.your.app.bar", "world");
//
//			NewService newService = new NewService();
//			//newService.setId("myapp_02");
//			//newService.setTags(Collections.singletonList("EU-East"));
//			newService.setName("JavaService");
//			newService.setPort(9200);
//
//			NewService.Check serviceCheck = new NewService.Check();
//			//serviceCheck.setScript("/usr/bin/some-check-script");
//			serviceCheck.setInterval("10s");
//			newService.setCheck(serviceCheck);
//
//			client.agentServiceRegister(newService);
//	
		}
		
	}
	
	
	

